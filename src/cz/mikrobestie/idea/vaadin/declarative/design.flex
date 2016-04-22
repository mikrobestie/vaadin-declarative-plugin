package cz.mikrobestie.idea.vaadin.declarative;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.TokenType;
import cz.mikrobestie.idea.vaadin.declarative.psi.VDTypes;

%%

%{

  private int commentStart = -1;

  public VaadinDesignLexer() {
    this(null);
  }
%}

%public
%class VaadinDesignLexer
%implements FlexLexer
%function advance
%type IElementType
%unicode

WHITE_SPACE = [\ \t\f\r\n]
COMPONENT_NAME = [a-z0-9]+(-[a-z][a-z0-9]*)*
ATTR_NAME = [_:a-z]?[a-z-]*
ATTR_VALUE = \"[^\"]*\"?|'[^']*'?
EL_LEFT = "<"
EL_RIGHT = ">"
EL_CLOSE_LEFT = "</"
EL_CLOSE_RIGHT = "/>"
EQ = "="

/* Fixed tags */
TAG_HTML = "html"
TAG_HEAD = "head"
TAG_META = "meta"
TAG_BODY = "body"

DOCTYPE_DECL = "<!DOCTYPE html>"

/* Others */
TEXT = [^<>]+

%state IN_TAG_NAME
%state IN_TAG_NAME_CLOSE
%state IN_TAG_ATTRS
%xstate IN_COMMENT

%%

{WHITE_SPACE}+                      { return TokenType.WHITE_SPACE; }

<YYINITIAL> {

    "<!--"                          { yybegin(IN_COMMENT); commentStart = zzStartRead;}

    // Fixed element names
    {DOCTYPE_DECL}                  { return VDTypes.DOCTYPE_DECL; }

    {EL_CLOSE_LEFT}                 { yybegin(IN_TAG_NAME_CLOSE); return VDTypes.EL_CLOSE_LEFT; }

    {EL_LEFT}                       { yybegin(IN_TAG_NAME); return VDTypes.EL_LEFT; }

    // Text
    {TEXT}                          { return VDTypes.TEXT; }
}

<IN_TAG_NAME> {

    {TAG_HTML}                      { yybegin(IN_TAG_ATTRS); return VDTypes.TAG_HTML; }

    {TAG_HEAD}                      { yybegin(IN_TAG_ATTRS); return VDTypes.TAG_HEAD; }

    {TAG_META}                      { yybegin(IN_TAG_ATTRS); return VDTypes.TAG_META; }

    {TAG_BODY}                      { yybegin(IN_TAG_ATTRS); return VDTypes.TAG_BODY; }

    // Last
    {COMPONENT_NAME}                { yybegin(IN_TAG_ATTRS); return VDTypes.COMPONENT_NAME; }

    {EL_RIGHT}                      { yybegin(YYINITIAL); return VDTypes.EL_RIGHT; }
}

<IN_TAG_NAME_CLOSE> {

    {TAG_HTML}                      { yybegin(IN_TAG_ATTRS); return VDTypes.TAG_HTML; }

    {TAG_HEAD}                      { yybegin(IN_TAG_ATTRS); return VDTypes.TAG_HEAD; }

    {TAG_META}                      { yybegin(IN_TAG_ATTRS); return VDTypes.TAG_META; }

    {TAG_BODY}                      { yybegin(IN_TAG_ATTRS); return VDTypes.TAG_BODY; }

    // Last
    {COMPONENT_NAME}                { yybegin(IN_TAG_ATTRS); return VDTypes.COMPONENT_NAME; }
}

<IN_COMMENT> {

    "-->"                           { yybegin(YYINITIAL); zzStartRead = commentStart; return VDTypes.COMMENT; }

    [^-\n]+                         { }

    "-"                             { }

    \n                              { }
}

<IN_TAG_ATTRS> {

    {ATTR_NAME}                     { return VDTypes.ATTR_NAME; }

    {EL_RIGHT}                      { yybegin(YYINITIAL); return VDTypes.EL_RIGHT; }

    {EL_CLOSE_RIGHT}                { yybegin(YYINITIAL); return VDTypes.EL_CLOSE_RIGHT; }

    {ATTR_VALUE}                    { return VDTypes.ATTR_VALUE; }

    {EQ}                            { return VDTypes.EQ; }

    {EL_CLOSE_LEFT}                 { yybegin(YYINITIAL); return VDTypes.EL_CLOSE_LEFT; }

    {EL_LEFT}                       { yybegin(YYINITIAL); return VDTypes.EL_RIGHT; }
}

[^]                                 { return TokenType.BAD_CHARACTER; }