// This is a generated file. Not intended for manual editing.
package cz.mikrobestie.idea.vaadin.declarative.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import cz.mikrobestie.idea.vaadin.declarative.psi.impl.*;

public interface VDTypes {

  IElementType ATTR = new VDElementType("ATTR");
  IElementType BODY_TAG = new VDElementType("BODY_TAG");
  IElementType COMPONENT = new VDElementType("COMPONENT");
  IElementType HEAD_TAG = new VDElementType("HEAD_TAG");
  IElementType HTML_TAG = new VDElementType("HTML_TAG");
  IElementType META_TAG = new VDElementType("META_TAG");

  IElementType ATTR_NAME = new VDTokenType("ATTR_NAME");
  IElementType ATTR_VALUE = new VDTokenType("ATTR_VALUE");
  IElementType COMMENT = new VDTokenType("COMMENT");
  IElementType COMPONENT_NAME = new VDTokenType("COMPONENT_NAME");
  IElementType DOCTYPE_DECL = new VDTokenType("DOCTYPE_DECL");
  IElementType EL_CLOSE_LEFT = new VDTokenType("EL_CLOSE_LEFT");
  IElementType EL_CLOSE_RIGHT = new VDTokenType("EL_CLOSE_RIGHT");
  IElementType EL_LEFT = new VDTokenType("EL_LEFT");
  IElementType EL_RIGHT = new VDTokenType("EL_RIGHT");
  IElementType EQ = new VDTokenType("EQ");
  IElementType TAG_BODY = new VDTokenType("TAG_BODY");
  IElementType TAG_HEAD = new VDTokenType("TAG_HEAD");
  IElementType TAG_HTML = new VDTokenType("TAG_HTML");
  IElementType TAG_META = new VDTokenType("TAG_META");
  IElementType TEXT = new VDTokenType("TEXT");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
       if (type == ATTR) {
        return new VDAttrImpl(node);
      }
      else if (type == BODY_TAG) {
        return new VDBodyTagImpl(node);
      }
      else if (type == COMPONENT) {
        return new VDComponentImpl(node);
      }
      else if (type == HEAD_TAG) {
        return new VDHeadTagImpl(node);
      }
      else if (type == HTML_TAG) {
        return new VDHtmlTagImpl(node);
      }
      else if (type == META_TAG) {
        return new VDMetaTagImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
