package cz.mikrobestie.idea.vaadin.declarative;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import cz.mikrobestie.idea.vaadin.declarative.psi.VDAttr;
import cz.mikrobestie.idea.vaadin.declarative.psi.VDComponent;
import org.jetbrains.annotations.NotNull;

/**
 * Created by Michal on 28.11.2015.
 */
public class VDAnnotator implements Annotator {

    @Override
    public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
        if (element instanceof VDComponent) {
            PsiClass psiClass = ((VDComponent) element).getComponentClass();
            if (psiClass == null) {
                TextRange range = ((VDComponent) element).getNameIdentifier().getTextRange();
                holder.createErrorAnnotation(range, "Compponent class " + ((VDComponent) element).getComponentClassName() + " not found");
            }
        } else if (element instanceof VDAttr) {

            VDAttr attr = (VDAttr) element;

            // Components
            if (attr.getComponent() != null && attr.getComponent().getComponentClass() != null) {

                // Parent attributes - not validated by Vaadin
                String name = attr.getName();
                if (name != null) {

                    // Parent component attributes
                    if (name.startsWith(":")) {
                        return;
                    }

                    // Common attributes
                    if (VaadinDesignCompletionContributor.getAttrs().containsKey(name)
                            || VaadinDesignCompletionContributor.getNoValueAttrs().containsKey(name)) {
                        return;
                    }

                    // Setters
                    if (attr.getSetter() == null)  {
                        TextRange range = ((VDAttr) element).getNameIdentifier().getTextRange();
                        holder.createErrorAnnotation(range, "Unknown attribute '" + name + "'");
                    }
                }
            }
        }
    }
}
