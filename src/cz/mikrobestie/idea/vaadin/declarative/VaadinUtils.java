package cz.mikrobestie.idea.vaadin.declarative;

import com.intellij.openapi.project.Project;
import com.intellij.psi.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Michal on 18.11.2015.
 */
public class VaadinUtils {

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
