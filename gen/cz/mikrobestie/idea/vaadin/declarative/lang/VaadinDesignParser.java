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
  // TAG_BODY_OPEN Component TAG_BODY_CLOSE
  public static boolean BodyTag(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "BodyTag")) return false;
    if (!nextTokenIs(b, TAG_BODY_OPEN)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, null);
    r = consumeToken(b, TAG_BODY_OPEN);
    p = r; // pin = 1
    r = r && report_error_(b, Component(b, l + 1));
    r = p && consumeToken(b, TAG_BODY_CLOSE) && r;
    exit_section_(b, l, m, BODY_TAG, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // ComponentSelfClosing | ComponentPair
  public static boolean Component(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Component")) return false;
    if (!nextTokenIs(b, EL_LEFT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = ComponentSelfClosing(b, l + 1);
    if (!r) r = ComponentPair(b, l + 1);
    exit_section_(b, m, COMPONENT, r);
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
  // EL_LEFT ELEM_NAME Attr* EL_RIGHT
  static boolean ComponentOpen(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ComponentOpen")) return false;
    if (!nextTokenIs(b, EL_LEFT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, EL_LEFT, ELEM_NAME);
    r = r && ComponentOpen_2(b, l + 1);
    r = r && consumeToken(b, EL_RIGHT);
    exit_section_(b, m, null, r);
    return r;
  }

  // Attr*
  private static boolean ComponentOpen_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ComponentOpen_2")) return false;
    int c = current_position_(b);
    while (true) {
      if (!Attr(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "ComponentOpen_2", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // !EL_LEFT
  static boolean ComponentOpenRecover(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ComponentOpenRecover")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NOT_, null);
    r = !consumeToken(b, EL_LEFT);
    exit_section_(b, l, m, null, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // ComponentOpen Component* ComponentClose
  static boolean ComponentPair(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ComponentPair")) return false;
    if (!nextTokenIs(b, EL_LEFT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = ComponentOpen(b, l + 1);
    r = r && ComponentPair_1(b, l + 1);
    r = r && ComponentClose(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // Component*
  private static boolean ComponentPair_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ComponentPair_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!Component(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "ComponentPair_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // !(Component | TAG_BODY_CLOSE)
  static boolean ComponentPairRecover(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ComponentPairRecover")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NOT_, null);
    r = !ComponentPairRecover_0(b, l + 1);
    exit_section_(b, l, m, null, r, false, null);
    return r;
  }

  // Component | TAG_BODY_CLOSE
  private static boolean ComponentPairRecover_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ComponentPairRecover_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Component(b, l + 1);
    if (!r) r = consumeToken(b, TAG_BODY_CLOSE);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // EL_LEFT ELEM_NAME Attr* EL_CLOSE_RIGHT
  static boolean ComponentSelfClosing(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ComponentSelfClosing")) return false;
    if (!nextTokenIs(b, EL_LEFT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, EL_LEFT, ELEM_NAME);
    r = r && ComponentSelfClosing_2(b, l + 1);
    r = r && consumeToken(b, EL_CLOSE_RIGHT);
    exit_section_(b, m, null, r);
    return r;
  }

  // Attr*
  private static boolean ComponentSelfClosing_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ComponentSelfClosing_2")) return false;
    int c = current_position_(b);
    while (true) {
      if (!Attr(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "ComponentSelfClosing_2", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // DOCTYPE_DECL? COMMENT* (HtmlTag | Component)
  static boolean Document(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Document")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Document_0(b, l + 1);
    r = r && Document_1(b, l + 1);
    r = r && Document_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // DOCTYPE_DECL?
  private static boolean Document_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Document_0")) return false;
    consumeToken(b, DOCTYPE_DECL);
    return true;
  }

  // COMMENT*
  private static boolean Document_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Document_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!consumeToken(b, COMMENT)) break;
      if (!empty_element_parsed_guard_(b, "Document_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // HtmlTag | Component
  private static boolean Document_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Document_2")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = HtmlTag(b, l + 1);
    if (!r) r = Component(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // TAG_HEAD_OPEN MetaTag* TAG_HEAD_CLOSE
  public static boolean HeadTag(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "HeadTag")) return false;
    if (!nextTokenIs(b, TAG_HEAD_OPEN)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, null);
    r = consumeToken(b, TAG_HEAD_OPEN);
    p = r; // pin = 1
    r = r && report_error_(b, HeadTag_1(b, l + 1));
    r = p && consumeToken(b, TAG_HEAD_CLOSE) && r;
    exit_section_(b, l, m, HEAD_TAG, r, p, null);
    return r || p;
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
  // TAG_HTML_OPEN Attr* EL_RIGHT HeadTag? BodyTag TAG_HTML_CLOSE
  public static boolean HtmlTag(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "HtmlTag")) return false;
    if (!nextTokenIs(b, TAG_HTML_OPEN)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, null);
    r = consumeToken(b, TAG_HTML_OPEN);
    p = r; // pin = 1
    r = r && report_error_(b, HtmlTag_1(b, l + 1));
    r = p && report_error_(b, consumeToken(b, EL_RIGHT)) && r;
    r = p && report_error_(b, HtmlTag_3(b, l + 1)) && r;
    r = p && report_error_(b, BodyTag(b, l + 1)) && r;
    r = p && consumeToken(b, TAG_HTML_CLOSE) && r;
    exit_section_(b, l, m, HTML_TAG, r, p, null);
    return r || p;
  }

  // Attr*
  private static boolean HtmlTag_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "HtmlTag_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!Attr(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "HtmlTag_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // HeadTag?
  private static boolean HtmlTag_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "HtmlTag_3")) return false;
    HeadTag(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // TAG_META_OPEN Attr+ EL_CLOSE_RIGHT
  public static boolean MetaTag(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MetaTag")) return false;
    if (!nextTokenIs(b, TAG_META_OPEN)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, null);
    r = consumeToken(b, TAG_META_OPEN);
    p = r; // pin = 1
    r = r && report_error_(b, MetaTag_1(b, l + 1));
    r = p && consumeToken(b, EL_CLOSE_RIGHT) && r;
    exit_section_(b, l, m, META_TAG, r, p, null);
    return r || p;
  }

  // Attr+
  private static boolean MetaTag_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MetaTag_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Attr(b, l + 1);
    int c = current_position_(b);
    while (r) {
      if (!Attr(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "MetaTag_1", c)) break;
      c = current_position_(b);
    }
    exit_section_(b, m, null, r);
    return r;
  }

}
