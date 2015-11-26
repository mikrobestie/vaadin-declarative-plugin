package cz.mikrobestie.idea.vaadin.declarative;

import com.intellij.psi.PsiClass;

/**
 * Created by Michal on 18.11.2015.
 */
public class VaadinUtils {

    public static String getClassDesignName(PsiClass psiClass, String prefix) {

        String element = prefix;
        String[] split = psiClass.getName().split("(?=\\p{Upper})");
        for (String part : split) {
            element += "-" + part.toLowerCase();
        }
        return element;
    }

    public static String getDesignClassName(String designNameWithoutPrefix) {

        String result = "";
        for (String part : designNameWithoutPrefix.split("-")) {
            result += part.substring(0, 1).toUpperCase() + part.substring(1);
        }
        return result;
    }
}
