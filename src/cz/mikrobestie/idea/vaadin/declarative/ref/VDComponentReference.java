package cz.mikrobestie.idea.vaadin.declarative.ref;

import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;
import cz.mikrobestie.idea.vaadin.declarative.icons.VaadinIcons;
import cz.mikrobestie.idea.vaadin.declarative.psi.VDComponent;
import cz.mikrobestie.idea.vaadin.declarative.psi.VDUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michal on 24.11.2015.
 */
public class VDComponentReference extends PsiReferenceBase<VDComponent> implements PsiPolyVariantReference {

    public VDComponentReference(@NotNull VDComponent element, TextRange textRange) {
        super(element, textRange);
    }

    @NotNull
    @Override
    public ResolveResult[] multiResolve(boolean incompleteCode) {
        PsiClass componentClass = getElement().getComponentClass();
        if (componentClass != null) {
            return new ResolveResult[] {new PsiElementResolveResult(componentClass)};
        }
        return new ResolveResult[0];
    }

    @Nullable
    @Override
    public PsiElement resolve() {
        ResolveResult[] resolveResults = multiResolve(false);
        return resolveResults.length == 1 ? resolveResults[0].getElement() : null;
    }

    @NotNull
    @Override
    public Object[] getVariants() {
        Project project = myElement.getProject();
        List<VDComponent> properties = VDUtils.findComponents(project);
        List<LookupElement> variants = new ArrayList<LookupElement>();
        for (final VDComponent component : properties) {
            if (component.getName() != null && component.getName().length() > 0) {
                variants.add(LookupElementBuilder.create(component).
                        withIcon(VaadinIcons.VAADIN_16).
                        withTypeText(component.getContainingFile().getName())
                );
            }
        }
        return variants.toArray();
    }
}
