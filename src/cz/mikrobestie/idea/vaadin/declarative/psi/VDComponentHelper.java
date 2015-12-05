package cz.mikrobestie.idea.vaadin.declarative.psi;

import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiNameIdentifierOwner;

import java.util.Set;

/**
 * Created by Michal on 24.11.2015.
 */
public interface VDComponentHelper extends PsiNameIdentifierOwner {

    String getComponentClassName();

    PsiClass getComponentClass();

    Set<String> getAttrNames();
}
