package cz.mikrobestie.idea.vaadin.declarative;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiParameter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Michal on 18.11.2015.
 */
public class VaadinUtils {

    private static final Map<String, Map<String, String>> componentAttrMap = new HashMap<>();


    static {

        // AbstractComponent
        Map<String, String> attrs = new HashMap<>();
        attrs.put("_id", "java.lang.String");
        attrs.put("error", "java.lang.String");
        attrs.put("width-auto", "void");
        attrs.put("width-full", "void");
        attrs.put("height-auto", "void");
        attrs.put("height-full", "void");
        attrs.put("size-auto", "void");
        attrs.put("size-full", "void");
        componentAttrMap.put("com.vaadin.ui.AbstractComponent", attrs);

        // Button
        attrs = new HashMap<>();
        attrs.put("icon-alt", "java.lang.String");
        attrs.put("click-shortcut", "com.vaadin.event.ShortcutAction");
        attrs.put("plain-text", "java.lang.Boolean");
        componentAttrMap.put("com.vaadin.ui.Button", attrs);

        // AbstractOrderedLayout
        attrs = new HashMap<>();
        attrs.put("margin-top", "void");
        attrs.put("margin-right", "void");
        attrs.put("margin-bottom", "void");
        attrs.put("margin-left", "void");
        componentAttrMap.put("com.vaadin.ui.AbstractOrderedLayout", attrs);
    }


    /**
     * Returns maps of custom attributes for given class.
     *
     * @param psiClass Class
     * @return Custom attributes
     */
    public static Map<String, String> getCustomAttributes(PsiClass psiClass) {

        Map<String, String> attrs = new HashMap<>();
        while (psiClass != null) {
            Map<String, String> add = componentAttrMap.get(psiClass.getQualifiedName());
            if (add != null) {
                attrs.putAll(add);
            }
            psiClass = psiClass.getSuperClass();
        }
        return attrs;
    }


    public static String decapitalize(String name) {
        String[] split = name.split("(?=\\p{Upper})");
        String val = "";
        for (String part : split) {
            val += "-" + part.toLowerCase();
        }
        return val.substring(1);
    }

    public static String getClassDesignName(PsiClass psiClass, String prefix) {
        return prefix + "-" + decapitalize(psiClass.getName());
    }

    /**
     * Turns some-class-name string into SomeClassName.
     *
     * @param designName Name in design
     * @return Class name
     */
    public static String capitalizeClass(String designName) {

        String result = "";
        for (String part : designName.split("-")) {
            result += part.substring(0, 1).toUpperCase() + part.substring(1);
        }
        return result;
    }

    /**
     * Turns some-setter-name string into setSomeSetterName.
     *
     * @param designName Name in design
     * @return Class name
     */
    public static String capitalizeSetter(String designName) {
        return "set" + capitalizeClass(designName);
    }

    public static Map<String, PsiMethod> getClassUsableSetters(Project project, String qualifiedName) {

        List<PsiMethod> setters = PluginUtils.findClassSetters(project, qualifiedName);
        if (!setters.isEmpty()) {

            Map<String, PsiMethod> map = new HashMap<>();
            for (PsiMethod setter : setters) {
                PsiParameter psiParameter = setter.getParameterList().getParameters()[0];
                String canonicalText = PluginUtils.isEnum(psiParameter.getType()) ? "java.lang.Enum" : psiParameter.getType().getCanonicalText();
                switch (canonicalText) {

                    case "java.lang.String":
                    case "java.lang.Character":
                    case "java.lang.Byte":
                    case "java.lang.Short":
                    case "java.lang.Integer":
                    case "java.lang.Long":
                    case "java.lang.Float":
                    case "java.lang.Boolean":
                    case "java.lang.Double":
                    case "java.math.BigDecimal":
                    case "java.util.Date":

                    case "char":
                    case "boolean":
                    case "int":
                    case "byte":
                    case "short":
                    case "long":
                    case "float":
                    case "double":

                    case "com.vaadin.server.Resource":

                    case "com.vaadin.event.ShortcutAction":

                    case "java.lang.Enum":

                        map.put(decapitalize(setter.getName().substring(3)), setter);
                        break;

                    default:
                        canonicalText.toLowerCase();
                }
            }
            return map;
        }

        return new HashMap<>();
    }
}
