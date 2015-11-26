package cz.mikrobestie.idea.vaadin.declarative.ref;

import com.intellij.patterns.PlatformPatterns;
import com.intellij.psi.*;
import com.intellij.util.ProcessingContext;
import cz.mikrobestie.idea.vaadin.declarative.psi.VDComponent;
import org.jetbrains.annotations.NotNull;

/**
 * Created by Michal on 24.11.2015.
 */
public class VDReferenceContributor extends PsiReferenceContributor {

    @Override
    public void registerReferenceProviders(PsiReferenceRegistrar registrar) {

        registrar.registerReferenceProvider(PlatformPatterns.psiElement(VDComponent.class),
                new PsiReferenceProvider() {

                    @NotNull
                    @Override
                    public PsiReference[] getReferencesByElement(@NotNull PsiElement element, @NotNull ProcessingContext context) {

                        VDComponent component = (VDComponent) element;
                        String className = component.getComponentClassName();
                        if (className != null) {

                            // TODO
//                            JavaClassReference javaClassReference = JavaClassReferenceCompletionContributor.findJavaClassReference(element.getContainingFile(), 0);
//                            return new PsiReference[] {javaClassReference};
                        }
                        return new PsiReference[0];
                    }
                });
    }
}
