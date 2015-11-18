package cz.mikrobestie.idea.vaadin.declarative;

import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.psi.PsiClass;
import com.intellij.psi.TokenType;
import com.intellij.util.ProcessingContext;
import cz.mikrobestie.idea.vaadin.declarative.psi.VDTypes;
import org.jetbrains.annotations.NotNull;

/**
 * Created by Michal on 18.11.2015.
 */
public class VaadinDesignCompletionContributor extends CompletionContributor {

    public VaadinDesignCompletionContributor() {
        extend(CompletionType.BASIC,
                PlatformPatterns.psiElement(VDTypes.NAME).withLanguage(VaadinDeclarativeLanguage.INSTANCE),
                new CompletionProvider<CompletionParameters>() {
                    public void addCompletions(@NotNull CompletionParameters parameters, ProcessingContext context, @NotNull CompletionResultSet resultSet) {

                        PsiClass[] classes = PluginUtils.findClasses(parameters.getOriginalFile().getProject(), "com.vaadin.ui");

                        resultSet.addElement(LookupElementBuilder.create("v-label", "Label"));
                        resultSet.addElement(LookupElementBuilder.create("v-text-field", "TextField.."));
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
                PlatformPatterns.psiElement(VDTypes.WHITE_SPACE).withLanguage(VaadinDeclarativeLanguage.INSTANCE),
                new CompletionProvider<CompletionParameters>() {
                    public void addCompletions(@NotNull CompletionParameters parameters, ProcessingContext context, @NotNull CompletionResultSet resultSet) {



                        resultSet.addElement(LookupElementBuilder.create("WHITESPACE"));
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
}
