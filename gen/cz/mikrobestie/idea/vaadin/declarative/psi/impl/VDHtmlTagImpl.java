// This is a generated file. Not intended for manual editing.
package cz.mikrobestie.idea.vaadin.declarative.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static cz.mikrobestie.idea.vaadin.declarative.psi.VDTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import cz.mikrobestie.idea.vaadin.declarative.psi.*;

public class VDHtmlTagImpl extends ASTWrapperPsiElement implements VDHtmlTag {

  public VDHtmlTagImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof VDVisitor) ((VDVisitor)visitor).visitHtmlTag(this);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public VDAttrs getAttrs() {
    return findChildByClass(VDAttrs.class);
  }

  @Override
  @NotNull
  public VDBodyTag getBodyTag() {
    return findNotNullChildByClass(VDBodyTag.class);
  }

  @Override
  @Nullable
  public VDHeadTag getHeadTag() {
    return findChildByClass(VDHeadTag.class);
  }

  @Override
  @NotNull
  public VDWhitespaces getWhitespaces() {
    return findNotNullChildByClass(VDWhitespaces.class);
  }

}
