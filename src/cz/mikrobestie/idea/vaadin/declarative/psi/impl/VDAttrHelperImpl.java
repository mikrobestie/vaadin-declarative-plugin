package cz.mikrobestie.idea.vaadin.declarative.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.util.IncorrectOperationException;
import cz.mikrobestie.idea.vaadin.declarative.psi.VDAttrHelper;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Created by Michal on 29.11.2015.
 */
public abstract class VDAttrHelperImpl extends ASTWrapperPsiElement implements VDAttrHelper {

    public VDAttrHelperImpl(@NotNull ASTNode node) {
        super(node);
    }

    @Override
    public String getName() {
        return getFirstChild() != null ? getFirstChild().getText() : null;
    }

    @Override
    public PsiElement setName(@NonNls @NotNull String name) throws IncorrectOperationException {
        return null;
    }

    @Nullable
    @Override
    public PsiElement getNameIdentifier() {
        return getFirstChild();
    }
}
