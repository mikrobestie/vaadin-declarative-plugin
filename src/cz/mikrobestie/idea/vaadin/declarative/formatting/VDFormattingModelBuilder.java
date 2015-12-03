package cz.mikrobestie.idea.vaadin.declarative.formatting;

import com.intellij.formatting.*;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import cz.mikrobestie.idea.vaadin.declarative.VaadinDeclarativeLanguage;
import cz.mikrobestie.idea.vaadin.declarative.psi.VDTypes;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Created by Michal on 29.11.2015.
 */
public class VDFormattingModelBuilder implements FormattingModelBuilder {

    @NotNull
    @Override
    public FormattingModel createModel(PsiElement element, CodeStyleSettings settings) {
        PsiFile containingFile = element.getContainingFile().getViewProvider().getPsi(VaadinDeclarativeLanguage.INSTANCE);
        return FormattingModelProvider.createFormattingModelForPsiFile(element.getContainingFile(),
                new VDBlock(element.getNode(), Wrap.createWrap(WrapType.NONE, false),
                        Alignment.createAlignment(), createSpaceBuilder(settings)), settings);
    }

    private static SpacingBuilder createSpaceBuilder(CodeStyleSettings settings) {
        return new SpacingBuilder(settings, VaadinDeclarativeLanguage.INSTANCE)
                .before(VDTypes.COMPONENT).lineBreakInCode();
    }

    @Nullable
    @Override
    public TextRange getRangeAffectingIndent(PsiFile file, int offset, ASTNode elementAtOffset) {
        return null;
    }
}
