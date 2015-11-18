package cz.mikrobestie.idea.vaadin.declarative;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.PackageIndex;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.*;

/**
 * Created by Michal on 15.11.2015.
 */
public class PluginUtils {


    public static VirtualFile findResourceFile(Project project, String packageName, String fileName) {
        VirtualFile[] dirs = PackageIndex.getInstance(project).getDirectoriesByPackageName(packageName, true);
        for (VirtualFile dir : dirs) {
            VirtualFile file = dir.findFileByRelativePath(fileName);
            if (file != null && file.exists()) {
                return file;
            }
        }
        return null;
    }

    public static PsiFile findResourcePsiFile(Project project, String packageName, String fileName) {
        VirtualFile resourceFile = findResourceFile(project, packageName, fileName);
        return resourceFile == null ? null : PsiManager.getInstance(project).findFile(resourceFile);
    }

    public static PsiClass[] findClasses(Project project, String packageName) {
        PsiPackage psiPackage = JavaPsiFacade.getInstance(project).findPackage(packageName);
        if (psiPackage != null) {
            return psiPackage.getClasses();
        }
        return new PsiClass[0];
    }
}
