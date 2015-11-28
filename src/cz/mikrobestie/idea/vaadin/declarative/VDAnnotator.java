package cz.mikrobestie.idea.vaadin.declarative;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import cz.mikrobestie.idea.vaadin.declarative.psi.VDComponent;
import org.jetbrains.annotations.NotNull;

/**
 * Created by Michal on 28.11.2015.
 */
public class VDAnnotator implements Annotator {

    @Override
    public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
        if (element instanceof VDComponent) {
            String componentClassName = ((VDComponent) element).getComponentClassName();
            PsiClass psiClass = PluginUtils.findClass(element.getProject(), componentClassName);
            if (psiClass == null) {
                TextRange range = ((VDComponent) element).getNameIdentifier().getTextRange();
                holder.createErrorAnnotation(range, "Compponent class " + componentClassName + " not found");
            }
        }
    }
}
