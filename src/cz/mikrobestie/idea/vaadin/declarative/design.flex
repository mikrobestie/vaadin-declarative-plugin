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

WHITE_SPACE=[\ \t\f\r\n]
NAME=[a-z]+(-[a-z]+)*
ATTR_VALUE=\"[^\"]*\"|'[^']*'
EL_LEFT=<
EL_RIGHT=>
EL_CLOSE_LEFT=<\/
EL_CLOSE_RIGHT=\/>


%%

{WHITE_SPACE}+                      { return TokenType.WHITE_SPACE; }

{NAME}                              { return VDTypes.NAME; }

{EL_LEFT}                           { return VDTypes.EL_LEFT; }

{EL_RIGHT}                          { return VDTypes.EL_RIGHT; }

{EL_CLOSE_LEFT}                     { return VDTypes.EL_CLOSE_LEFT; }

{EL_CLOSE_RIGHT}                    { return VDTypes.EL_CLOSE_RIGHT; }

{ATTR_VALUE}                        { return VDTypes.ATTR_VALUE; }

[^]                                 { return TokenType.BAD_CHARACTER; }