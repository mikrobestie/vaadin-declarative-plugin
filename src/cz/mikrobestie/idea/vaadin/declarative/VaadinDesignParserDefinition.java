package cz.mikrobestie.idea.vaadin.declarative;

import com.intellij.lang.ASTNode;
import com.intellij.lang.Language;
import com.intellij.lang.ParserDefinition;
import com.intellij.lang.PsiParser;
import com.intellij.lexer.FlexAdapter;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;
import cz.mikrobestie.idea.vaadin.declarative.lang.VaadinDesignParser;
import cz.mikrobestie.idea.vaadin.declarative.psi.VDTypes;
import cz.mikrobestie.idea.vaadin.declarative.psi.VaadinDesignFile;
import org.jetbrains.annotations.NotNull;

/**
 * Created by Michal on 18.11.2015.
 */
public class VaadinDesignParserDefinition implements ParserDefinition {

    public static final TokenSet WHITE_SPACES = TokenSet.create(TokenType.WHITE_SPACE);
    public static final TokenSet STRING_LITERALS = TokenSet.create(VDTypes.ATTR_VALUE);
//    public static final TokenSet COMMENTS = TokenSet.create(SimpleTypes.COMMENT);

    public static final IFileElementType FILE = new IFileElementType(Language.<VaadinDeclarativeLanguage>findInstance(VaadinDeclarativeLanguage.class));


    @NotNull
    @Override
    public Lexer createLexer(Project project) {
        return new FlexAdapter(new VaadinDesignLexer(null));
    }

    @Override
    public PsiParser createParser(Project project) {
        return new VaadinDesignParser();
    }

    @Override
    public IFileElementType getFileNodeType() {
        return FILE;
    }

    @NotNull
    @Override
    public TokenSet getWhitespaceTokens() {
        return WHITE_SPACES;
    }

    @NotNull
    @Override
    public TokenSet getCommentTokens() {
        return TokenSet.EMPTY;
    }

    @NotNull
    @Override
    public TokenSet getStringLiteralElements() {
        return STRING_LITERALS;
    }

    @NotNull
    @Override
    public PsiElement createElement(ASTNode node) {
        return VDTypes.Factory.createElement(node);
    }

    @Override
    public PsiFile createFile(FileViewProvider viewProvider) {
        return new VaadinDesignFile(viewProvider);
    }

    @Override
    public SpaceRequirements spaceExistanceTypeBetweenTokens(ASTNode left, ASTNode right) {
        return SpaceRequirements.MAY;
    }
}
