package cz.mikrobestie.idea.vaadin.declarative;

import com.intellij.psi.PsiClass;

/**
 * Created by Michal on 18.11.2015.
 */
public class VaadinUtils {

    public static String getClassDesignName(PsiClass psiClass) {

        if (psiClass.getQualifiedName() == null || !psiClass.getQualifiedName().startsWith("com.vaadin.ui.")) {
            throw new IllegalArgumentException("Cannot find html element name of class " + psiClass.getQualifiedName());
        }

        String element = "v";
        String[] split = psiClass.getName().split("(?=\\p{Upper})");
        for (String part : split) {
            element += "-" + part.toLowerCase();
        }
        return element;
    }
}
