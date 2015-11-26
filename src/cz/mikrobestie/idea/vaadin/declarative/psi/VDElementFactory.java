package cz.mikrobestie.idea.vaadin.declarative.psi;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFileFactory;
import cz.mikrobestie.idea.vaadin.declarative.VaadinDesignFileType;

/**
 * Created by Michal on 24.11.2015.
 */
public class VDElementFactory {

    public static VDComponent createComponent(Project project, String name) {
        final VaadinDesignFile file = createFile(project, "<" + name + " />");
        return (VDComponent) file.getFirstChild();
    }

    public static VaadinDesignFile createFile(Project project, String text) {
        String name = "dummy.html";
        return (VaadinDesignFile) PsiFileFactory.getInstance(project).
                createFileFromText(name, VaadinDesignFileType.INSTANCE, text);
    }
}
