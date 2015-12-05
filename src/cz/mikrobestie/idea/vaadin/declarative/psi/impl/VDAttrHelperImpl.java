package cz.mikrobestie.idea.vaadin.declarative.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiMethod;
import com.intellij.util.IncorrectOperationException;
import cz.mikrobestie.idea.vaadin.declarative.PluginUtils;
import cz.mikrobestie.idea.vaadin.declarative.VaadinUtils;
import cz.mikrobestie.idea.vaadin.declarative.psi.VDAttrHelper;
import cz.mikrobestie.idea.vaadin.declarative.psi.VDComponent;
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

    @Override
    public VDComponent getComponent() {
        PsiElement parent = getParent();
        return parent instanceof VDComponent ? (VDComponent) parent : null;
    }

    @Override
    public PsiMethod getSetter() {
        String name = getName();
        VDComponent component = getComponent();
        if (name != null && component != null) {
            String className = component.getComponentClassName();
            String setterName = VaadinUtils.capitalizeSetter(name);
            return PluginUtils.findClassSetter(getProject(), className, setterName);
        }
        return null;
    }
}
