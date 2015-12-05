package cz.mikrobestie.idea.vaadin.declarative.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import com.intellij.psi.impl.source.resolve.reference.ReferenceProvidersRegistry;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.IncorrectOperationException;
import cz.mikrobestie.idea.vaadin.declarative.PluginUtils;
import cz.mikrobestie.idea.vaadin.declarative.VaadinUtils;
import cz.mikrobestie.idea.vaadin.declarative.psi.*;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Michal on 24.11.2015.
 */
public abstract class VDComponentHelperImpl extends ASTWrapperPsiElement implements VDComponentHelper {

    public VDComponentHelperImpl(@NotNull ASTNode node) {
        super(node);
    }

    @Override
    public String getName() {
        ASTNode nameNode = getNode().findChildByType(VDTypes.ELEM_NAME);
        return nameNode == null ? null : nameNode.getText();
    }

    @Override
    public PsiElement setName(@NonNls @NotNull String name) throws IncorrectOperationException {
        ASTNode keyNode = getNode().findChildByType(VDTypes.ELEM_NAME);
        if (keyNode != null) {

            VDComponent component = VDElementFactory.createComponent(getProject(), name);
            ASTNode newKeyNode = component.getNode().findChildByType(VDTypes.ELEM_NAME);
            getNode().replaceChild(keyNode, newKeyNode);
        }
        return this;
    }

    @Nullable
    @Override
    public PsiElement getNameIdentifier() {
        ASTNode keyNode = getNode().findChildByType(VDTypes.ELEM_NAME);
        if (keyNode != null) {
            return keyNode.getPsi();
        } else {
            return null;
        }
    }

    @NotNull
    @Override
    public PsiReference[] getReferences() {
        return ReferenceProvidersRegistry.getReferencesFromProviders(this);
    }

    @Override
    public String getComponentClassName() {
        String name = getName();
        if (name != null) {
            String[] parts = name.split("-", 2);
            if (parts.length == 2) {
                String prefix = parts[0];
                if (prefix != null) {
                    VaadinDesignFile file = (VaadinDesignFile) getContainingFile();
                    VaadinDesignFile.PackageDefinition pkg = file.getPackageByPrefix(prefix);
                    if (pkg != null) {
                        return pkg.getPackageName() + "." + VaadinUtils.capitalizeClass(parts[1]);
                    }
                }
            }
        }
        return null;
    }

    @Override
    public PsiClass getComponentClass() {
        String className = getComponentClassName();
        return className == null ? null : PluginUtils.findClass(getProject(), className);
    }

    @Override
    public Set<String> getAttrNames() {
        List<VDAttr> list = PsiTreeUtil.getChildrenOfTypeAsList(this, VDAttr.class);
        if (!list.isEmpty()) {
            Set<String> names = new HashSet<>();
            for (VDAttr attr : list) {
                names.add(attr.getName());
            }
            return names;
        }
        return Collections.emptySet();
    }
}
