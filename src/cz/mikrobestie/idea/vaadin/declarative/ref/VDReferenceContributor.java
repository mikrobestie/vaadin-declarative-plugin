package cz.mikrobestie.idea.vaadin.declarative.ref;

import com.intellij.psi.PsiReferenceContributor;
import com.intellij.psi.PsiReferenceRegistrar;
import cz.mikrobestie.idea.vaadin.declarative.VaadinDeclarativeLanguage;
import cz.mikrobestie.idea.vaadin.declarative.psi.VDComponent;
import org.jetbrains.annotations.NotNull;

import static com.intellij.patterns.PlatformPatterns.psiElement;

/**
 * Created by Michal on 24.11.2015.
 */
public class VDReferenceContributor extends PsiReferenceContributor {

    @Override
    public void registerReferenceProviders(@NotNull PsiReferenceRegistrar registrar) {

        registrar.registerReferenceProvider(
                psiElement(VDComponent.class)
                        .withLanguage(VaadinDeclarativeLanguage.INSTANCE)
                , new VDComponentClassReferenceProvider());
    }
}
