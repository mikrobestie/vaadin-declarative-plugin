// This is a generated file. Not intended for manual editing.
package cz.mikrobestie.idea.vaadin.declarative.lang;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import static cz.mikrobestie.idea.vaadin.declarative.psi.VDTypes.*;
import static cz.mikrobestie.idea.vaadin.declarative.lang.VaadinDesignParserUtil.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.PsiParser;
import com.intellij.lang.LightPsiParser;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class VaadinDesignParser implements PsiParser, LightPsiParser {

  public ASTNode parse(IElementType t, PsiBuilder b) {
    parseLight(t, b);
    return b.getTreeBuilt();
  }

  public void parseLight(IElementType t, PsiBuilder b) {
    boolean r;
    b = adapt_builder_(t, b, this, null);
    Marker m = enter_section_(b, 0, _COLLAPSE_, null);
    if (t == ATTR) {
      r = Attr(b, 0);
    }
    else if (t == ATTR_WITH_VALUE) {
      r = AttrWithValue(b, 0);
    }
    else if (t == ATTRS) {
      r = Attrs(b, 0);
    }
    else if (t == BODY_TAG) {
      r = BodyTag(b, 0);
    }
    else if (t == COMPONENT) {
      r = Component(b, 0);
    }
    else if (t == DOCTYPE) {
      r = Doctype(b, 0);
    }
    else if (t == HEAD_TAG) {
      r = HeadTag(b, 0);
    }
    else if (t == HTML_CONTENT) {
      r = HtmlContent(b, 0);
    }
    else if (t == HTML_TAG) {
      r = HtmlTag(b, 0);
    }
    else if (t == LOCAL_ID_ATTR) {
      r = LocalIdAttr(b, 0);
    }
    else if (t == META_TAG) {
      r = MetaTag(b, 0);
    }
    else if (t == PARENT_ATTR) {
      r = ParentAttr(b, 0);
    }
    else if (t == WHITESPACES) {
      r = Whitespaces(b, 0);
    }
    else {
      r = parse_root_(t, b, 0);
    }
    exit_section_(b, 0, m, t, r, true, TRUE_CONDITION);
  }

  protected boolean parse_root_(IElementType t, PsiBuilder b, int l) {
    return Document(b, l + 1);
  }

  /* ********************************************************** */
  // LocalIdAttr | AttrWithValue | ParentAttr
  public static boolean Attr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Attr")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, "<attr>");
    r = LocalIdAttr(b, l + 1);
    if (!r) r = AttrWithValue(b, l + 1);
    if (!r) r = ParentAttr(b, l + 1);
    exit_section_(b, l, m, ATTR, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // NAME ('=' ATTR_VALUE)?
  public static boolean AttrWithValue(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AttrWithValue")) return false;
    if (!nextTokenIs(b, NAME)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, NAME);
    r = r && AttrWithValue_1(b, l + 1);
    exit_section_(b, m, ATTR_WITH_VALUE, r);
    return r;
  }

  // ('=' ATTR_VALUE)?
  private static boolean AttrWithValue_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AttrWithValue_1")) return false;
    AttrWithValue_1_0(b, l + 1);
    return true;
  }

  // '=' ATTR_VALUE
  private static boolean AttrWithValue_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AttrWithValue_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, "=");
    r = r && consumeToken(b, ATTR_VALUE);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // (Whitespaces Attr)+
  public static boolean Attrs(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Attrs")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, "<attrs>");
    r = Attrs_0(b, l + 1);
    int c = current_position_(b);
    while (r) {
      if (!Attrs_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "Attrs", c)) break;
      c = current_position_(b);
    }
    exit_section_(b, l, m, ATTRS, r, false, null);
    return r;
  }

  // Whitespaces Attr
  private static boolean Attrs_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Attrs_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Whitespaces(b, l + 1);
    r = r && Attr(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // '<body>' Whitespaces Component Whitespaces '</body>'
  public static boolean BodyTag(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "BodyTag")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, "<body tag>");
    r = consumeToken(b, "<body>");
    r = r && Whitespaces(b, l + 1);
    r = r && Component(b, l + 1);
    r = r && Whitespaces(b, l + 1);
    r = r && consumeToken(b, "</body>");
    exit_section_(b, l, m, BODY_TAG, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // EL_OPEN NAME Attrs? ('/>' | '>' HtmlContent '</' NAME '>')
  public static boolean Component(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Component")) return false;
    if (!nextTokenIs(b, EL_OPEN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, EL_OPEN, NAME);
    r = r && Component_2(b, l + 1);
    r = r && Component_3(b, l + 1);
    exit_section_(b, m, COMPONENT, r);
    return r;
  }

  // Attrs?
  private static boolean Component_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Component_2")) return false;
    Attrs(b, l + 1);
    return true;
  }

  // '/>' | '>' HtmlContent '</' NAME '>'
  private static boolean Component_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Component_3")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, "/>");
    if (!r) r = Component_3_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // '>' HtmlContent '</' NAME '>'
  private static boolean Component_3_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Component_3_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ">");
    r = r && HtmlContent(b, l + 1);
    r = r && consumeToken(b, "</");
    r = r && consumeToken(b, NAME);
    r = r && consumeToken(b, ">");
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // '<!DOCTYPE html>'
  public static boolean Doctype(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Doctype")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, "<doctype>");
    r = consumeToken(b, "<!DOCTYPE html>");
    exit_section_(b, l, m, DOCTYPE, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // (Doctype Whitespaces)? (HtmlTag | Component)
  static boolean Document(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Document")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Document_0(b, l + 1);
    r = r && Document_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (Doctype Whitespaces)?
  private static boolean Document_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Document_0")) return false;
    Document_0_0(b, l + 1);
    return true;
  }

  // Doctype Whitespaces
  private static boolean Document_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Document_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Doctype(b, l + 1);
    r = r && Whitespaces(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // HtmlTag | Component
  private static boolean Document_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Document_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = HtmlTag(b, l + 1);
    if (!r) r = Component(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // '<head>' MetaTag* '</head>'
  public static boolean HeadTag(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "HeadTag")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, "<head tag>");
    r = consumeToken(b, "<head>");
    r = r && HeadTag_1(b, l + 1);
    r = r && consumeToken(b, "</head>");
    exit_section_(b, l, m, HEAD_TAG, r, false, null);
    return r;
  }

  // MetaTag*
  private static boolean HeadTag_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "HeadTag_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!MetaTag(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "HeadTag_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // (EL_OPEN VOID_TAG_NAME '/>') | (EL_OPEN PAIR_TAG_NAME '>' HtmlContent* '</' PAIR_TAG_NAME '>')
  public static boolean HtmlContent(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "HtmlContent")) return false;
    if (!nextTokenIs(b, EL_OPEN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = HtmlContent_0(b, l + 1);
    if (!r) r = HtmlContent_1(b, l + 1);
    exit_section_(b, m, HTML_CONTENT, r);
    return r;
  }

  // EL_OPEN VOID_TAG_NAME '/>'
  private static boolean HtmlContent_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "HtmlContent_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, EL_OPEN, VOID_TAG_NAME);
    r = r && consumeToken(b, "/>");
    exit_section_(b, m, null, r);
    return r;
  }

  // EL_OPEN PAIR_TAG_NAME '>' HtmlContent* '</' PAIR_TAG_NAME '>'
  private static boolean HtmlContent_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "HtmlContent_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, EL_OPEN, PAIR_TAG_NAME);
    r = r && consumeToken(b, ">");
    r = r && HtmlContent_1_3(b, l + 1);
    r = r && consumeToken(b, "</");
    r = r && consumeToken(b, PAIR_TAG_NAME);
    r = r && consumeToken(b, ">");
    exit_section_(b, m, null, r);
    return r;
  }

  // HtmlContent*
  private static boolean HtmlContent_1_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "HtmlContent_1_3")) return false;
    int c = current_position_(b);
    while (true) {
      if (!HtmlContent(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "HtmlContent_1_3", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // '<html' Attrs? '>' Whitespaces HeadTag? BodyTag '</html>'
  public static boolean HtmlTag(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "HtmlTag")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, "<html tag>");
    r = consumeToken(b, "<html");
    r = r && HtmlTag_1(b, l + 1);
    r = r && consumeToken(b, ">");
    r = r && Whitespaces(b, l + 1);
    r = r && HtmlTag_4(b, l + 1);
    r = r && BodyTag(b, l + 1);
    r = r && consumeToken(b, "</html>");
    exit_section_(b, l, m, HTML_TAG, r, false, null);
    return r;
  }

  // Attrs?
  private static boolean HtmlTag_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "HtmlTag_1")) return false;
    Attrs(b, l + 1);
    return true;
  }

  // HeadTag?
  private static boolean HtmlTag_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "HtmlTag_4")) return false;
    HeadTag(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // '_id=' ATTR_VALUE
  public static boolean LocalIdAttr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LocalIdAttr")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, "<local id attr>");
    r = consumeToken(b, "_id=");
    r = r && consumeToken(b, ATTR_VALUE);
    exit_section_(b, l, m, LOCAL_ID_ATTR, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // '<meta' '/>'
  public static boolean MetaTag(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MetaTag")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, "<meta tag>");
    r = consumeToken(b, "<meta");
    r = r && consumeToken(b, "/>");
    exit_section_(b, l, m, META_TAG, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // ':' AttrWithValue
  public static boolean ParentAttr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ParentAttr")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, "<parent attr>");
    r = consumeToken(b, ":");
    r = r && AttrWithValue(b, l + 1);
    exit_section_(b, l, m, PARENT_ATTR, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // (WHITE_SPACE | CRLF)*
  public static boolean Whitespaces(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Whitespaces")) return false;
    Marker m = enter_section_(b, l, _NONE_, "<whitespaces>");
    int c = current_position_(b);
    while (true) {
      if (!Whitespaces_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "Whitespaces", c)) break;
      c = current_position_(b);
    }
    exit_section_(b, l, m, WHITESPACES, true, false, null);
    return true;
  }

  // WHITE_SPACE | CRLF
  private static boolean Whitespaces_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Whitespaces_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, WHITE_SPACE);
    if (!r) r = consumeToken(b, CRLF);
    exit_section_(b, m, null, r);
    return r;
  }

}
