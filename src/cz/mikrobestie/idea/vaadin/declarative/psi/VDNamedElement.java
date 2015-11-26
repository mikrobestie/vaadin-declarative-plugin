package cz.mikrobestie.idea.vaadin.declarative.psi;

import com.intellij.psi.PsiNameIdentifierOwner;

/**
 * Created by Michal on 24.11.2015.
 */
public interface VDNamedElement extends PsiNameIdentifierOwner {

    String getComponentClassName();
}
