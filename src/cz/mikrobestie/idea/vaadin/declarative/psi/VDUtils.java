package cz.mikrobestie.idea.vaadin.declarative.psi;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.search.FileTypeIndex;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.indexing.FileBasedIndex;
import cz.mikrobestie.idea.vaadin.declarative.VaadinDesignFileType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by Michal on 24.11.2015.
 */
public class VDUtils {

    public static List<VDComponent> findComponents(Project project, String key) {
        List<VDComponent> result = null;
        Collection<VirtualFile> virtualFiles = FileBasedIndex.getInstance().getContainingFiles(FileTypeIndex.NAME, VaadinDesignFileType.INSTANCE,
                GlobalSearchScope.allScope(project));
        for (VirtualFile virtualFile : virtualFiles) {
            VaadinDesignFile simpleFile = (VaadinDesignFile) PsiManager.getInstance(project).findFile(virtualFile);
            if (simpleFile != null) {
                VDComponent[] properties = PsiTreeUtil.getChildrenOfType(simpleFile, VDComponent.class);
                if (properties != null) {
                    for (VDComponent component : properties) {
                        if (key.equals(component.getName())) {
                            if (result == null) {
                                result = new ArrayList<>();
                            }
                            result.add(component);
                        }
                    }
                }
            }
        }
        return result != null ? result : Collections.<VDComponent>emptyList();
    }

    public static List<VDComponent> findComponents(Project project) {
        List<VDComponent> result = null;
        Collection<VirtualFile> virtualFiles = FileBasedIndex.getInstance().getContainingFiles(FileTypeIndex.NAME, VaadinDesignFileType.INSTANCE,
                GlobalSearchScope.allScope(project));
        for (VirtualFile virtualFile : virtualFiles) {
            VaadinDesignFile simpleFile = (VaadinDesignFile) PsiManager.getInstance(project).findFile(virtualFile);
            if (simpleFile != null) {
                VDComponent[] properties = PsiTreeUtil.getChildrenOfType(simpleFile, VDComponent.class);
                if (properties != null) {
                    for (VDComponent component : properties) {
                        if (result == null) {
                            result = new ArrayList<>();
                        }
                    }
                }
            }
        }
        return result != null ? result : Collections.<VDComponent>emptyList();
    }
}
