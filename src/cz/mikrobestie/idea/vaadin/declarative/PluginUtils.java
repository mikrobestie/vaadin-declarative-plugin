package cz.mikrobestie.idea.vaadin.declarative;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.PackageIndex;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.*;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.search.searches.ClassInheritorsSearch;
import com.intellij.util.Query;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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

    /**
     * Tries to find class by given qualified name (anywhere in the project or dependencies). Returns null if
     * not found.
     *
     * @param project Project
     * @param className Class name
     * @return Class
     */
    public static PsiClass findClass(Project project, String className) {
        return JavaPsiFacade.getInstance(project).findClass(className, GlobalSearchScope.allScope(project));
    }

    public static List<PsiMethod> findClassSetters(Project project, String qualifiedName) {
        GlobalSearchScope scope = GlobalSearchScope.allScope(project);
        PsiClass aClass = JavaPsiFacade.getInstance(project).findClass(qualifiedName, scope);
        if (aClass != null) {
            List<PsiMethod> list = new ArrayList<>();
            for (PsiMethod method : aClass.getAllMethods()) {
                if (method.getName().startsWith("set") && method.getParameterList().getParametersCount() == 1) {
                    list.add(method);
                }
            }
            return list;
        }
        return Collections.emptyList();
    }

    /**
     * Searches for all classes implementing / extending given class name, placed in the given package.
     *
     * @param project Project
     * @param packageName Package name
     * @param inheritsClass Superclass / Interface class name
     * @return Array of found classes
     */
    public static List<PsiClass> findClasses(Project project, String packageName, String inheritsClass) {

        GlobalSearchScope scope = GlobalSearchScope.allScope(project);

        PsiClass psiClass = JavaPsiFacade.getInstance(project).findClass(inheritsClass, scope);
        if (psiClass != null) {
            Query<PsiClass> query = ClassInheritorsSearch.search(psiClass, scope, true);
            return query.findAll().stream()
                    .filter(c -> c.getQualifiedName() != null
                            && c.getQualifiedName().equals(packageName + "." + c.getName())
                            && !c.isInterface()
                            && !c.getModifierList().hasExplicitModifier("abstract"))
                    .collect(Collectors.toList());
        }
        return Collections.EMPTY_LIST;
    }

    /**
     * Finds setter of the class.
     *
     * @param project Project
     * @param className Class name
     * @param setterName Setter name
     * @return Method
     */
    public static PsiMethod findClassSetter(Project project, String className, String setterName) {

        List<PsiMethod> setters = PluginUtils.findClassSetters(project, className);
        if (!setters.isEmpty()) {
            for (PsiMethod setter : setters) {
                if (setter.getName().equals(setterName)) {
                    return setter;
                }
            }
        }
        return null;
    }

    public static boolean isEnum(PsiType type) {
        return type instanceof PsiClassType
                && (((PsiClassType) type).resolve()).isEnum();
    }

    public static boolean isAssignableFrom(String fqn, PsiClass type) {

        // Dont count in generics
        int i = fqn.indexOf('<');
        if (i > -1) {
            fqn = fqn.substring(0, i);
        }

        while (type != null) {
            if (fqn.equals(type.getQualifiedName())) {
                return true;
            }
            type = type.getSuperClass();
        }
        return false;
    }
}
