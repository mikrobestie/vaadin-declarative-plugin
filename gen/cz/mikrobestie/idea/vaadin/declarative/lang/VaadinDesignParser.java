// This is a generated file. Not intended for manual editing.
package cz.mikrobestie.idea.vaadin.declarative.lang;

import com.intellij.lang.ASTNode;
import com.intellij.lang.LightPsiParser;
import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import com.intellij.lang.PsiParser;
import com.intellij.psi.tree.IElementType;

import static cz.mikrobestie.idea.vaadin.declarative.lang.VaadinDesignParserUtil.*;
import static cz.mikrobestie.idea.vaadin.declarative.psi.VDTypes.*;

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
    else if (t == BODY_TAG) {
      r = BodyTag(b, 0);
    }
    else if (t == COMPONENT) {
      r = Component(b, 0);
    }
    else if (t == HEAD_TAG) {
      r = HeadTag(b, 0);
    }
    else if (t == HTML_TAG) {
      r = HtmlTag(b, 0);
    }
    else if (t == META_TAG) {
      r = MetaTag(b, 0);
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
  // ATTR_NAME (EQ ATTR_VALUE)?
  public static boolean Attr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Attr")) return false;
    if (!nextTokenIs(b, ATTR_NAME)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ATTR_NAME);
    r = r && Attr_1(b, l + 1);
    exit_section_(b, m, ATTR, r);
    return r;
  }

  // (EQ ATTR_VALUE)?
  private static boolean Attr_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Attr_1")) return false;
    Attr_1_0(b, l + 1);
    return true;
  }

  // EQ ATTR_VALUE
  private static boolean Attr_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Attr_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, EQ, ATTR_VALUE);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // COMMENT* TAG_BODY_OPEN COMMENT* Component COMMENT* TAG_BODY_CLOSE
  public static boolean BodyTag(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "BodyTag")) return false;
    if (!nextTokenIs(b, "<body tag>", COMMENT, TAG_BODY_OPEN)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, "<body tag>");
    r = BodyTag_0(b, l + 1);
    r = r && consumeToken(b, TAG_BODY_OPEN);
    p = r; // pin = 2
    r = r && report_error_(b, BodyTag_2(b, l + 1));
    r = p && report_error_(b, Component(b, l + 1)) && r;
    r = p && report_error_(b, BodyTag_4(b, l + 1)) && r;
    r = p && consumeToken(b, TAG_BODY_CLOSE) && r;
    exit_section_(b, l, m, BODY_TAG, r, p, null);
    return r || p;
  }

  // COMMENT*
  private static boolean BodyTag_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "BodyTag_0")) return false;
    int c = current_position_(b);
    while (true) {
      if (!consumeToken(b, COMMENT)) break;
      if (!empty_element_parsed_guard_(b, "BodyTag_0", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // COMMENT*
  private static boolean BodyTag_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "BodyTag_2")) return false;
    int c = current_position_(b);
    while (true) {
      if (!consumeToken(b, COMMENT)) break;
      if (!empty_element_parsed_guard_(b, "BodyTag_2", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // COMMENT*
  private static boolean BodyTag_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "BodyTag_4")) return false;
    int c = current_position_(b);
    while (true) {
      if (!consumeToken(b, COMMENT)) break;
      if (!empty_element_parsed_guard_(b, "BodyTag_4", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // EL_LEFT ELEM_NAME Attr* (EL_CLOSE_RIGHT | EL_RIGHT (COMMENT | Component)* ComponentClose)
  public static boolean Component(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Component")) return false;
    if (!nextTokenIs(b, EL_LEFT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, EL_LEFT, ELEM_NAME);
    r = r && Component_2(b, l + 1);
    r = r && Component_3(b, l + 1);
    exit_section_(b, m, COMPONENT, r);
    return r;
  }

  // Attr*
  private static boolean Component_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Component_2")) return false;
    int c = current_position_(b);
    while (true) {
      if (!Attr(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "Component_2", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // EL_CLOSE_RIGHT | EL_RIGHT (COMMENT | Component)* ComponentClose
  private static boolean Component_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Component_3")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, EL_CLOSE_RIGHT);
    if (!r) r = Component_3_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // EL_RIGHT (COMMENT | Component)* ComponentClose
  private static boolean Component_3_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Component_3_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, EL_RIGHT);
    r = r && Component_3_1_1(b, l + 1);
    r = r && ComponentClose(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (COMMENT | Component)*
  private static boolean Component_3_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Component_3_1_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!Component_3_1_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "Component_3_1_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // COMMENT | Component
  private static boolean Component_3_1_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Component_3_1_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMENT);
    if (!r) r = Component(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // EL_CLOSE_LEFT ELEM_NAME EL_RIGHT
  static boolean ComponentClose(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ComponentClose")) return false;
    if (!nextTokenIs(b, EL_CLOSE_LEFT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, EL_CLOSE_LEFT, ELEM_NAME, EL_RIGHT);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // DOCTYPE_DECL? (HtmlTag | Component)
  static boolean Document(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Document")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Document_0(b, l + 1);
    r = r && Document_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // DOCTYPE_DECL?
  private static boolean Document_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Document_0")) return false;
    consumeToken(b, DOCTYPE_DECL);
    return true;
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
  // COMMENT* TAG_HEAD_OPEN (MetaTag | COMMENT)* TAG_HEAD_CLOSE
  public static boolean HeadTag(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "HeadTag")) return false;
    if (!nextTokenIs(b, "<head tag>", COMMENT, TAG_HEAD_OPEN)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, "<head tag>");
    r = HeadTag_0(b, l + 1);
    r = r && consumeToken(b, TAG_HEAD_OPEN);
    p = r; // pin = 2
    r = r && report_error_(b, HeadTag_2(b, l + 1));
    r = p && consumeToken(b, TAG_HEAD_CLOSE) && r;
    exit_section_(b, l, m, HEAD_TAG, r, p, null);
    return r || p;
  }

  // COMMENT*
  private static boolean HeadTag_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "HeadTag_0")) return false;
    int c = current_position_(b);
    while (true) {
      if (!consumeToken(b, COMMENT)) break;
      if (!empty_element_parsed_guard_(b, "HeadTag_0", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // (MetaTag | COMMENT)*
  private static boolean HeadTag_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "HeadTag_2")) return false;
    int c = current_position_(b);
    while (true) {
      if (!HeadTag_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "HeadTag_2", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // MetaTag | COMMENT
  private static boolean HeadTag_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "HeadTag_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = MetaTag(b, l + 1);
    if (!r) r = consumeToken(b, COMMENT);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // COMMENT* TAG_HTML_OPEN Attr* EL_RIGHT HeadTag? BodyTag TAG_HTML_CLOSE
  public static boolean HtmlTag(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "HtmlTag")) return false;
    if (!nextTokenIs(b, "<html tag>", COMMENT, TAG_HTML_OPEN)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, "<html tag>");
    r = HtmlTag_0(b, l + 1);
    r = r && consumeToken(b, TAG_HTML_OPEN);
    p = r; // pin = 2
    r = r && report_error_(b, HtmlTag_2(b, l + 1));
    r = p && report_error_(b, consumeToken(b, EL_RIGHT)) && r;
    r = p && report_error_(b, HtmlTag_4(b, l + 1)) && r;
    r = p && report_error_(b, BodyTag(b, l + 1)) && r;
    r = p && consumeToken(b, TAG_HTML_CLOSE) && r;
    exit_section_(b, l, m, HTML_TAG, r, p, null);
    return r || p;
  }

  // COMMENT*
  private static boolean HtmlTag_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "HtmlTag_0")) return false;
    int c = current_position_(b);
    while (true) {
      if (!consumeToken(b, COMMENT)) break;
      if (!empty_element_parsed_guard_(b, "HtmlTag_0", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // Attr*
  private static boolean HtmlTag_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "HtmlTag_2")) return false;
    int c = current_position_(b);
    while (true) {
      if (!Attr(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "HtmlTag_2", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // HeadTag?
  private static boolean HtmlTag_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "HtmlTag_4")) return false;
    HeadTag(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // COMMENT* TAG_META_OPEN Attr+ EL_CLOSE_RIGHT
  public static boolean MetaTag(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MetaTag")) return false;
    if (!nextTokenIs(b, "<meta tag>", COMMENT, TAG_META_OPEN)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, "<meta tag>");
    r = MetaTag_0(b, l + 1);
    r = r && consumeToken(b, TAG_META_OPEN);
    p = r; // pin = 2
    r = r && report_error_(b, MetaTag_2(b, l + 1));
    r = p && consumeToken(b, EL_CLOSE_RIGHT) && r;
    exit_section_(b, l, m, META_TAG, r, p, null);
    return r || p;
  }

  // COMMENT*
  private static boolean MetaTag_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MetaTag_0")) return false;
    int c = current_position_(b);
    while (true) {
      if (!consumeToken(b, COMMENT)) break;
      if (!empty_element_parsed_guard_(b, "MetaTag_0", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // Attr+
  private static boolean MetaTag_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MetaTag_2")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Attr(b, l + 1);
    int c = current_position_(b);
    while (r) {
      if (!Attr(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "MetaTag_2", c)) break;
      c = current_position_(b);
    }
    exit_section_(b, m, null, r);
    return r;
  }

}
