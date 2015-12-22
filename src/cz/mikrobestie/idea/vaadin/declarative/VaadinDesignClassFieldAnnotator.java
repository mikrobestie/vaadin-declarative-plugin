package cz.mikrobestie.idea.vaadin.declarative;

import com.intellij.codeInsight.AnnotationUtil;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.psi.*;
import cz.mikrobestie.idea.vaadin.declarative.psi.VDComponent;
import cz.mikrobestie.idea.vaadin.declarative.psi.VaadinDesignFile;
import org.jetbrains.annotations.NotNull;

/**
 * Annotator for java classes annotated with @DesignRoot.
 */
public class VaadinDesignClassFieldAnnotator implements Annotator {

    @Override
    public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {

        if (element instanceof PsiField) {

            // It IS a Component
            PsiField field = (PsiField) element;
            if (VaadinUtils.isComponent(field.getType())) {

                PsiClass aClass = field.getContainingClass();
                PsiAnnotation aDesignRoot = AnnotationUtil.findAnnotation(aClass, "com.vaadin.annotations.DesignRoot");
                if (aDesignRoot != null) {
                    VaadinDesignFile designFile = VaadinUtils.getFileByDesignRootAnnotation(aDesignRoot);
                    VDComponent boundComponent = designFile.getComponentById(field.getName());
                    if (boundComponent == null) {
                        holder.createErrorAnnotation(field.getNameIdentifier(), "Field is not bound to component in design file " + designFile.getName());
                    } else if (!PluginUtils.isAssignableFrom(field.getType().getCanonicalText(), boundComponent.getComponentClass())) {
                        holder.createErrorAnnotation(field.getTypeElement(), "Cannot be assigned from " + boundComponent.getComponentClassName() + " defined in design file");
                    }
                }
            }
        }
    }
}
