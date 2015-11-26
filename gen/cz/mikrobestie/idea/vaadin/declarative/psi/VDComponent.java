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

  @Nullable
  VDHtmlContent getHtmlContent();

}
