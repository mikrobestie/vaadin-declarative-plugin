package cz.mikrobestie.idea.vaadin.declarative;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiMethod;
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
            if (attr.getComponent() != null) {

                PsiClass psiClass = attr.getComponent().getComponentClass();
                if (psiClass != null) {

                    // Parent attributes - not validated by Vaadin
                    String name = attr.getName();
                    if (name != null) {

                        // Name check
                        if (name.startsWith(":")) {
                            // Parent component attributes
                        } else if (VaadinUtils.getCustomAttributes(psiClass).get(name) != null) {
                            // Custom attributes
                        } else if (attr.getSetter() == null)  {
                            // Unknown attr
                            TextRange range = attr.getNameIdentifier().getTextRange();
                            holder.createErrorAnnotation(range, "Unknown attribute '" + name + "'");
                        }

                        // Value check
                        PsiMethod setter = attr.getSetter();
                        if (setter != null) {

                            String value = attr.getValue();
                            String type = attr.getType().getCanonicalText();

                            // Boolean handling first
                            if (value == null) {
                                if (!"boolean".equals(type) && !"java.lang.Boolean".equals(type) && !"void".equals(type)) {
                                    holder.createErrorAnnotation(attr.getNameIdentifier(), "Value of type " + type + " expected");
                                }
                            } else {

                                String error = null;

                                // Type check
                                switch (type) {

                                    case "boolean":
                                    case "java.lang.Boolean":

                                        // Does not have to have a value
                                        if (!"true".equals(value) && !"false".equals(value)) {
                                            holder.createWarningAnnotation(attr.getLastChild(), "Resolves to boolean 'true'");
                                        }
                                        break;
                                }
                                if (error != null) {
                                    holder.createErrorAnnotation(attr.getLastChild(), error);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
