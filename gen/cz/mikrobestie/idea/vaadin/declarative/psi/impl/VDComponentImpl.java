// This is a generated file. Not intended for manual editing.
package cz.mikrobestie.idea.vaadin.declarative.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import cz.mikrobestie.idea.vaadin.declarative.psi.VDAttr;
import cz.mikrobestie.idea.vaadin.declarative.psi.VDComponent;
import cz.mikrobestie.idea.vaadin.declarative.psi.VDVisitor;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class VDComponentImpl extends VDComponentHelperImpl implements VDComponent {

  public VDComponentImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof VDVisitor) ((VDVisitor)visitor).visitComponent(this);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<VDAttr> getAttrList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, VDAttr.class);
  }

  @Override
  @NotNull
  public List<VDComponent> getComponentList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, VDComponent.class);
  }

}
