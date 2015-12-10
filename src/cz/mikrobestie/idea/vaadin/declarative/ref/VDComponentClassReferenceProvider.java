package cz.mikrobestie.idea.vaadin.declarative.ref;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import com.intellij.psi.PsiReferenceProvider;
import com.intellij.util.ProcessingContext;
import cz.mikrobestie.idea.vaadin.declarative.psi.VDComponent;
import org.jetbrains.annotations.NotNull;

/**
 * Created by Michal on 10.12.2015.
 */
public class VDComponentClassReferenceProvider extends PsiReferenceProvider {

    @NotNull
    @Override
    public PsiReference[] getReferencesByElement(@NotNull PsiElement element, @NotNull ProcessingContext context) {
        VDComponent component = (VDComponent) element;
        return new PsiReference[] {new VDComponentReference(component, component.getNameIdentifier().getTextRange())};
    }
}
