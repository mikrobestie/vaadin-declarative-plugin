// This is a generated file. Not intended for manual editing.
package cz.mikrobestie.idea.vaadin.declarative.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface VDComponent extends VDNamedElement {

  @NotNull
  List<VDAttr> getAttrList();

  @NotNull
  List<VDComponent> getComponentList();

  @NotNull
  List<VDHtmlContent> getHtmlContentList();

  String getName();

  PsiElement setName(String name);

  @Nullable
  PsiElement getNameIdentifier();

}
