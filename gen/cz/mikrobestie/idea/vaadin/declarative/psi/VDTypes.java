// This is a generated file. Not intended for manual editing.
package cz.mikrobestie.idea.vaadin.declarative.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import cz.mikrobestie.idea.vaadin.declarative.psi.impl.*;

public interface VDTypes {

  IElementType ATTR = new VDElementType("ATTR");
  IElementType ATTRS = new VDElementType("ATTRS");
  IElementType ATTR_WITH_VALUE = new VDElementType("ATTR_WITH_VALUE");
  IElementType BODY_TAG = new VDElementType("BODY_TAG");
  IElementType COMPONENT = new VDElementType("COMPONENT");
  IElementType DOCTYPE = new VDElementType("DOCTYPE");
  IElementType HEAD_TAG = new VDElementType("HEAD_TAG");
  IElementType HTML_CONTENT = new VDElementType("HTML_CONTENT");
  IElementType HTML_TAG = new VDElementType("HTML_TAG");
  IElementType LOCAL_ID_ATTR = new VDElementType("LOCAL_ID_ATTR");
  IElementType META_TAG = new VDElementType("META_TAG");
  IElementType PARENT_ATTR = new VDElementType("PARENT_ATTR");
  IElementType WHITESPACES = new VDElementType("WHITESPACES");

  IElementType ATTR_VALUE = new VDTokenType("ATTR_VALUE");
  IElementType CRLF = new VDTokenType("CRLF");
  IElementType EL_OPEN = new VDTokenType("EL_OPEN");
  IElementType NAME = new VDTokenType("NAME");
  IElementType PAIR_TAG_NAME = new VDTokenType("PAIR_TAG_NAME");
  IElementType VOID_TAG_NAME = new VDTokenType("VOID_TAG_NAME");
  IElementType WHITE_SPACE = new VDTokenType("WHITE_SPACE");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
       if (type == ATTR) {
        return new VDAttrImpl(node);
      }
      else if (type == ATTRS) {
        return new VDAttrsImpl(node);
      }
      else if (type == ATTR_WITH_VALUE) {
        return new VDAttrWithValueImpl(node);
      }
      else if (type == BODY_TAG) {
        return new VDBodyTagImpl(node);
      }
      else if (type == COMPONENT) {
        return new VDComponentImpl(node);
      }
      else if (type == DOCTYPE) {
        return new VDDoctypeImpl(node);
      }
      else if (type == HEAD_TAG) {
        return new VDHeadTagImpl(node);
      }
      else if (type == HTML_CONTENT) {
        return new VDHtmlContentImpl(node);
      }
      else if (type == HTML_TAG) {
        return new VDHtmlTagImpl(node);
      }
      else if (type == LOCAL_ID_ATTR) {
        return new VDLocalIdAttrImpl(node);
      }
      else if (type == META_TAG) {
        return new VDMetaTagImpl(node);
      }
      else if (type == PARENT_ATTR) {
        return new VDParentAttrImpl(node);
      }
      else if (type == WHITESPACES) {
        return new VDWhitespacesImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
