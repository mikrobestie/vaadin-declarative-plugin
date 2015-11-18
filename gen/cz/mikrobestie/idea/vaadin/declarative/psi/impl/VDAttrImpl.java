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

public class VDAttrImpl extends ASTWrapperPsiElement implements VDAttr {

  public VDAttrImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof VDVisitor) ((VDVisitor)visitor).visitAttr(this);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public VDAttrWithValue getAttrWithValue() {
    return findChildByClass(VDAttrWithValue.class);
  }

  @Override
  @Nullable
  public VDLocalIdAttr getLocalIdAttr() {
    return findChildByClass(VDLocalIdAttr.class);
  }

  @Override
  @Nullable
  public VDParentAttr getParentAttr() {
    return findChildByClass(VDParentAttr.class);
  }

}
