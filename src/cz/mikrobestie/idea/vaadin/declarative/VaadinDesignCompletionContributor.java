package cz.mikrobestie.idea.vaadin.declarative;

import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.psi.PsiClass;
import com.intellij.psi.TokenType;
import com.intellij.util.ProcessingContext;
import cz.mikrobestie.idea.vaadin.declarative.icons.VaadinIcons;
import cz.mikrobestie.idea.vaadin.declarative.psi.VDTypes;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Created by Michal on 18.11.2015.
 */
public class VaadinDesignCompletionContributor extends CompletionContributor {

    public VaadinDesignCompletionContributor() {
        extend(CompletionType.BASIC,
                PlatformPatterns.psiElement(VDTypes.NAME).withLanguage(VaadinDeclarativeLanguage.INSTANCE),
                new CompletionProvider<CompletionParameters>() {
                    public void addCompletions(@NotNull CompletionParameters parameters, ProcessingContext context, @NotNull CompletionResultSet resultSet) {

                        // Find default Vaadin components
                        List<PsiClass> vaadinComponents = PluginUtils.findClasses(parameters.getOriginalFile().getProject(), "com.vaadin.ui", "com.vaadin.ui.Component");
                        for (PsiClass component : vaadinComponents) {

                            LookupElementBuilder offer = LookupElementBuilder.create(VaadinUtils.getClassDesignName(component))
                                    .appendTailText(" " + component.getQualifiedName(), true)
                                    .withIcon(VaadinIcons.VAADIN_16);
                            resultSet.addElement(offer);
                        }
                    }
                }
        );
        extend(CompletionType.BASIC,
                PlatformPatterns.psiElement(VDTypes.ATTR).withLanguage(VaadinDeclarativeLanguage.INSTANCE),
                new CompletionProvider<CompletionParameters>() {
                    public void addCompletions(@NotNull CompletionParameters parameters, ProcessingContext context, @NotNull CompletionResultSet resultSet) {



                        resultSet.addElement(LookupElementBuilder.create("Hello"));
                    }
                });
        extend(CompletionType.BASIC,
                PlatformPatterns.psiElement(VDTypes.ATTR_VALUE).withLanguage(VaadinDeclarativeLanguage.INSTANCE),
                new CompletionProvider<CompletionParameters>() {
                    public void addCompletions(@NotNull CompletionParameters parameters, ProcessingContext context, @NotNull CompletionResultSet resultSet) {



                        resultSet.addElement(LookupElementBuilder.create("Hello"));
                    }
                });
        extend(CompletionType.BASIC,
                PlatformPatterns.psiElement(TokenType.WHITE_SPACE).withLanguage(VaadinDeclarativeLanguage.INSTANCE),
                new CompletionProvider<CompletionParameters>() {
                    public void addCompletions(@NotNull CompletionParameters parameters, ProcessingContext context, @NotNull CompletionResultSet resultSet) {



                        resultSet.addElement(LookupElementBuilder.create("WHITESPACE"));
                    }
                });
    }

    /** appears as space but I don't flip to a dot like real space char in parse tree */
    public static final char DUMMY_IDENTIFIER = 'x';

    @Override
    public void beforeCompletion(@NotNull CompletionInitializationContext context) {
        context.setDummyIdentifier(String.valueOf(DUMMY_IDENTIFIER));
    }
}
