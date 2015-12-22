package cz.mikrobestie.idea.vaadin.declarative;

import com.intellij.openapi.project.Project;
import com.intellij.psi.*;
import cz.mikrobestie.idea.vaadin.declarative.psi.VaadinDesignFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Michal on 18.11.2015.
 */
public class VaadinUtils {

    public static final String TYPE_STRING = String.class.getName();
    public static final String TYPE_VOID = "void";
    public static final String TYPE_BOOLEAN = "boolean";
    public static final String TYPE_RESOURCE = "com.vaadin.server.Resource";
    public static final String TYPE_FLOAT = "float";

    private static final Map<String, Map<String, String>> componentAttrMap = new HashMap<>();
    private static final Map<String, Map<String, String>> parentAttrMap = new HashMap<>();


    static {

        // Component base
        componentAttrMap.put("com.vaadin.ui.Component", new HashMap<>());

        // AbstractComponent
        Map<String, String> attrs = new HashMap<>();
        attrs.put("_id", TYPE_STRING);
        attrs.put("error", TYPE_STRING);
        attrs.put("width-auto", TYPE_VOID);
        attrs.put("width-full", TYPE_VOID);
        attrs.put("height-auto", TYPE_VOID);
        attrs.put("height-full", TYPE_VOID);
        attrs.put("size-auto", TYPE_VOID);
        attrs.put("size-full", TYPE_VOID);
        componentAttrMap.put("com.vaadin.ui.AbstractComponent", attrs);

        // Button
        attrs = new HashMap<>();
        attrs.put("icon-alt", TYPE_STRING);
        attrs.put("click-shortcut", "com.vaadin.event.ShortcutAction");
        attrs.put("plain-text", TYPE_VOID);
        componentAttrMap.put("com.vaadin.ui.Button", attrs);

        // CheckBox
        attrs = new HashMap<>();
        attrs.put("checked", TYPE_BOOLEAN);
        componentAttrMap.put("com.vaadin.ui.CheckBox", attrs);

        // Link
        attrs = new HashMap<>();
        attrs.put("target", TYPE_STRING);
        attrs.put("ref", TYPE_RESOURCE);
        componentAttrMap.put("com.vaadin.ui.Link", attrs);

        // AbstractOrderedLayout
        attrs = new HashMap<>();
        attrs.put("margin-top", TYPE_VOID);
        attrs.put("margin-right", TYPE_VOID);
        attrs.put("margin-bottom", TYPE_VOID);
        attrs.put("margin-left", TYPE_VOID);
        componentAttrMap.put("com.vaadin.ui.AbstractOrderedLayout", attrs);


        // Parent attrs
        attrs = new HashMap<>();
        attrs.put(":middle", TYPE_VOID);
        attrs.put(":center", TYPE_VOID);
        attrs.put(":bottom", TYPE_VOID);
        attrs.put(":right", TYPE_VOID);
        attrs.put(":expand", TYPE_FLOAT);
        parentAttrMap.put("com.vaadin.ui.AbstractOrderedLayout", attrs);
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

    /**
     * Returns maps of parent attributes for given component parent class.
     *
     * @param psiParent Parent class
     * @return Parent attributes
     */
    public static Map<String, String> getParentAttributes(PsiClass psiParent) {

        Map<String, String> attrs = new HashMap<>();
        while (psiParent != null) {
            Map<String, String> add = parentAttrMap.get(psiParent.getQualifiedName());
            if (add != null) {
                attrs.putAll(add);
            }
            psiParent = psiParent.getSuperClass();
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

    /**
     * Returns name of corresponding class used in design files. Package prefix must
     * be given as a parameter.
     *
     * @param psiClass Class
     * @param prefix Class package prefix
     * @return Resulting class name
     */
    public static String getClassDesignName(PsiClass psiClass, String prefix) {
        if (psiClass == null) {
            throw new IllegalArgumentException("Class must not be null");
        }
        if (prefix == null) {
            throw new IllegalArgumentException("Prefix must not be null");
        }
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

    /**
     * Returns true if given type implements Vaadin component.
     *
     * @param type Type
     * @return true if component
     */
    public static boolean isComponent(PsiType type) {
        Map<String, String> map = componentAttrMap.get(type.getCanonicalText());
        if (map != null) {
            return true;
        }
        // Try
        if (type instanceof PsiClassType) {
            PsiClassType classType = (PsiClassType) type;
            PsiClass cls = classType.resolve();
            while (cls != null) {
                if (componentAttrMap.containsKey(cls.getQualifiedName())) {
                    return true;
                }
                cls = cls.getSuperClass();
            }
        }
        return false;
    }

    public static VaadinDesignFile getFileByDesignRootAnnotation(PsiAnnotation annotation) {

        PsiJavaFile javaFile = (PsiJavaFile) annotation.getContainingFile();

        // Find file name from annotation
        PsiNameValuePair[] attributes = annotation.getParameterList().getAttributes();
        String designName = null;
        if (attributes.length > 0) {
            PsiNameValuePair pair = attributes[0];
            designName = pair.getLiteralValue();
        } else {

            PsiElement nextSibling = annotation.getParent().getNextSibling();
            while (nextSibling != null) {
                if (nextSibling instanceof PsiIdentifier) {
                    designName = nextSibling.getText() + ".html";
                    break;
                }
                nextSibling = nextSibling.getNextSibling();
            }
            if (designName == null) {
                designName = javaFile.getName() + ".html";
            }
        }

        // Find file
        PsiFile designFile = PluginUtils.findResourcePsiFile(annotation.getProject(), javaFile.getPackageName(), designName);
        return designFile != null && designFile instanceof VaadinDesignFile ? (VaadinDesignFile) designFile : null;
    }
}
