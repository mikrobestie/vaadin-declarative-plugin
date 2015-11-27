package cz.mikrobestie.idea.vaadin.declarative;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.TokenType;
import cz.mikrobestie.idea.vaadin.declarative.psi.VDTypes;

%%

%{
  public VaadinDesignLexer() {
    this((java.io.Reader)null);
  }
%}

%public
%class VaadinDesignLexer
%implements FlexLexer
%function advance
%type IElementType
%unicode

COMMENT = "<!--" (.|\n])* "-->"
WHITE_SPACE = [\ \t\f\r\n]
ELEM_NAME = [a-z]+(-[a-z]+)*
ATTR_NAME = [_:a-z]?[a-z-]*
ATTR_VALUE = \"[^\"]*\"?|'[^']*'?
EL_LEFT = "<"
EL_RIGHT = ">"
EL_CLOSE_LEFT = "</"
EL_CLOSE_RIGHT = "/>"
EQ = "="

/* Fixed tags */
DOCTYPE_DECL = "<!DOCTYPE html>"
TAG_HTML_OPEN = "<html"
TAG_HTML_CLOSE = "</html>"
TAG_HEAD_OPEN = "<head>"
TAG_HEAD_CLOSE = "</head>"
TAG_META_OPEN = "<meta"
TAG_BODY_OPEN = "<body>"
TAG_BODY_CLOSE = "</body>"

%state IN_TAG

%%

{WHITE_SPACE}+                      { return TokenType.WHITE_SPACE; }

<YYINITIAL> {

    {COMMENT}                       { return VDTypes.COMMENT; }

    // Fixed element names
    {DOCTYPE_DECL}                  { return VDTypes.DOCTYPE_DECL; }

    {TAG_HTML_OPEN}                 { yybegin(IN_TAG); return VDTypes.TAG_HTML_OPEN; }

    {TAG_HTML_CLOSE}                { return VDTypes.TAG_HTML_CLOSE; }

    {TAG_HEAD_OPEN}                 { return VDTypes.TAG_HEAD_OPEN; }

    {TAG_HEAD_CLOSE}                { return VDTypes.TAG_HEAD_CLOSE; }

    {TAG_META_OPEN}                 { yybegin(IN_TAG); return VDTypes.TAG_META_OPEN; }

    {TAG_BODY_OPEN}                 { return VDTypes.TAG_BODY_OPEN; }

    {TAG_BODY_CLOSE}                { return VDTypes.TAG_BODY_CLOSE; }

    {EL_CLOSE_LEFT}                 { return VDTypes.EL_CLOSE_LEFT; }

    {EL_LEFT}                       { return VDTypes.EL_LEFT; }

    // Last
    {ELEM_NAME}                     { yybegin(IN_TAG); return VDTypes.ELEM_NAME; }
}

<IN_TAG> {

    {ATTR_NAME}                     { return VDTypes.ATTR_NAME; }

    {EL_RIGHT}                      { yybegin(YYINITIAL); return VDTypes.EL_RIGHT; }

    {EL_CLOSE_RIGHT}                { yybegin(YYINITIAL); return VDTypes.EL_CLOSE_RIGHT; }

    {ATTR_VALUE}                    { return VDTypes.ATTR_VALUE; }

    {EQ}                            { return VDTypes.EQ; }

    {EL_CLOSE_LEFT}                 { yybegin(YYINITIAL); return VDTypes.EL_CLOSE_LEFT; }

    {EL_LEFT}                       { yybegin(YYINITIAL); return VDTypes.EL_RIGHT; }
}

[^]                                 { return TokenType.BAD_CHARACTER; }