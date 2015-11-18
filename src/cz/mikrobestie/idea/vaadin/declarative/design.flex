package cz.mikrobestie.idea.vaadin.declarative;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.TokenType;
import cz.mikrobestie.idea.vaadin.declarative.psi.VDTypes;

%%

%class VaadinDesignLexer
%implements FlexLexer
%unicode
%function advance
%type IElementType
%eof{  return;
%eof}

WHITE_SPACE=[\ \t\f]
CRLF=\r|\n|\r\n
VOID_TAG_NAME=br
PAIR_TAG_NAME=p|b|i
NAME=[a-z]+(-[a-z]+)+
ATTR_VALUE=\"[^\"]*\"|'[^']*'
EL_OPEN=<

%state WAITING_VALUE

%%

<YYINITIAL> {WHITE_SPACE}+                                  { yybegin(YYINITIAL); return TokenType.WHITE_SPACE; }

<YYINITIAL> {CRLF}                                          { yybegin(YYINITIAL); return VDTypes.CRLF; }

<YYINITIAL> {VOID_TAG_NAME}                                 { yybegin(YYINITIAL); return VDTypes.VOID_TAG_NAME; }

<YYINITIAL> {PAIR_TAG_NAME}                                 { yybegin(YYINITIAL); return VDTypes.PAIR_TAG_NAME; }

<YYINITIAL> {NAME}                                          { yybegin(YYINITIAL); return VDTypes.NAME; }

<YYINITIAL> {EL_OPEN}                                       { yybegin(YYINITIAL); return VDTypes.EL_OPEN; }

<YYINITIAL> {ATTR_VALUE}                                    { yybegin(YYINITIAL); return VDTypes.ATTR_VALUE; }

.                                                           { return TokenType.BAD_CHARACTER; }