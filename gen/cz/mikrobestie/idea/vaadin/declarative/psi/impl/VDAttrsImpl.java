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

public class VDAttrsImpl extends ASTWrapperPsiElement implements VDAttrs {

  public VDAttrsImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof VDVisitor) ((VDVisitor)visitor).visitAttrs(this);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<VDAttr> getAttrList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, VDAttr.class);
  }

  @Override
  @NotNull
  public List<VDWhitespaces> getWhitespacesList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, VDWhitespaces.class);
  }

}
