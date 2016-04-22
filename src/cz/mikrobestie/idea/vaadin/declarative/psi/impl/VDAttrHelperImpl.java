package cz.mikrobestie.idea.vaadin.declarative.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.*;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.search.searches.MethodReferencesSearch;
import com.intellij.util.IncorrectOperationException;
import com.intellij.util.Query;
import cz.mikrobestie.idea.vaadin.declarative.PluginUtils;
import cz.mikrobestie.idea.vaadin.declarative.VaadinUtils;
import cz.mikrobestie.idea.vaadin.declarative.psi.VDAttrHelper;
import cz.mikrobestie.idea.vaadin.declarative.psi.VDComponent;
import cz.mikrobestie.idea.vaadin.declarative.psi.VDTypes;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

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
            if (className != null) {
                String setterName = VaadinUtils.capitalizeSetter(name);
                return PluginUtils.findClassSetter(getProject(), className, setterName);
            }
        }
        return null;
    }

    @Override
    public PsiType getType() {
        PsiMethod setter = getSetter();
        if (setter != null) {
            return setter.getParameterList().getParameters()[0].getType();
        }
        String type = VaadinUtils.getCustomAttributes(getComponent().getComponentClass()).get(getName());
        if (type == null) {
            type = VaadinUtils.getParentAttributes(getComponent().getComponentClass()).get(getName());
        }
        if (type != null) {
            return PsiType.getTypeByName(type, getProject(), GlobalSearchScope.projectScope(getProject()));
        }
        return null;
    }

    @Override
    public String getValue() {
        PsiElement lastChild = getLastChild();
        if (lastChild != null && lastChild.getNode().getElementType() == VDTypes.ATTR_VALUE) {
            String text = lastChild.getText();
            if (text.startsWith("\"")) {
                return text.replace("\"", "");
            } else if (text.startsWith("'")) {
                return text.replace("'", "");
            }
        }
        return null;
    }

    @NotNull
    @Override
    public PsiReference[] getReferences() {
        PsiMethod setter = getSetter();
        if (setter != null) {
            Query<PsiReference> search = MethodReferencesSearch.search(setter);
            Collection<PsiReference> references = search.findAll();
            return references.toArray(new PsiReference[references.size()]);
        }
        return new PsiReference[0];
    }
}
