// This is a generated file. Not intended for manual editing.
package cz.mikrobestie.idea.vaadin.declarative.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiElement;

public class VDVisitor extends PsiElementVisitor {

  public void visitAttr(@NotNull VDAttr o) {
    visitAttrHelper(o);
  }

  public void visitBodyTag(@NotNull VDBodyTag o) {
    visitPsiElement(o);
  }

  public void visitComponent(@NotNull VDComponent o) {
    visitNamedElement(o);
  }

  public void visitHeadTag(@NotNull VDHeadTag o) {
    visitPsiElement(o);
  }

  public void visitHtmlTag(@NotNull VDHtmlTag o) {
    visitPsiElement(o);
  }

  public void visitMetaTag(@NotNull VDMetaTag o) {
    visitPsiElement(o);
  }

  public void visitAttrHelper(@NotNull VDAttrHelper o) {
    visitPsiElement(o);
  }

  public void visitNamedElement(@NotNull VDNamedElement o) {
    visitPsiElement(o);
  }

  public void visitPsiElement(@NotNull PsiElement o) {
    visitElement(o);
  }

}
