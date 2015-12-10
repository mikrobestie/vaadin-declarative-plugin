package cz.mikrobestie.idea.vaadin.declarative.psi;

import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiNameIdentifierOwner;
import com.intellij.psi.PsiType;

/**
 * Created by Michal on 29.11.2015.
 */
public interface VDAttrHelper extends PsiNameIdentifierOwner {

    String getName();

    VDComponent getComponent();

    PsiMethod getSetter();

    PsiType getType();

    /**
     * Returns attribute value as text. If the attribute does not have any value defined (even empty value), returns
     * {@code null}.
     *
     * @return Value or null
     */
    String getValue();
}
