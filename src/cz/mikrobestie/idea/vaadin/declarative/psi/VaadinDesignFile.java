package cz.mikrobestie.idea.vaadin.declarative.psi;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.util.PsiTreeUtil;
import cz.mikrobestie.idea.vaadin.declarative.VaadinDeclarativeLanguage;
import cz.mikrobestie.idea.vaadin.declarative.VaadinDesignFileType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michal on 18.11.2015.
 */
public class VaadinDesignFile extends PsiFileBase {

    public VaadinDesignFile(@NotNull FileViewProvider viewProvider) {
        super(viewProvider, VaadinDeclarativeLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public FileType getFileType() {
        return VaadinDesignFileType.INSTANCE;
    }

    @Override
    public String toString() {
        return "Vaadin Design file";
    }

    @Nullable
    @Override
    public Icon getIcon(int flags) {
        return super.getIcon(flags);
    }

    /**
     * Returns prefix definitions of custom packages.
     *
     * @return Array of custom package definitions
     */
    public List<PackageDefinition> getPackages() {

        List<PackageDefinition> packages = new ArrayList<>();

        VDHtmlTag html = PsiTreeUtil.getChildOfType(this, VDHtmlTag.class);
        if (html != null) {
            VDHeadTag head = PsiTreeUtil.getChildOfType(html, VDHeadTag.class);
            if (head != null) {

                // Go through all meta tags
                VDMetaTag[] metaTags = PsiTreeUtil.getChildrenOfType(head, VDMetaTag.class);
                if (metaTags != null) {
                    for (int i = 0; i < metaTags.length; i++) {
                        VDMetaTag mt = metaTags[i];
                        for (VDAttr attr : mt.getAttrList()) {
                            if (attr.getFirstChild().getText().equals("content")) {
                                String text1 = attr.getLastChild().getText();
                                String text = text1.substring(1, text1.length() - 1);
                                String[] split = text.split(":");
                                if (split.length == 2) {
                                    packages.add(new PackageDefinition(split[0], split[1]));
                                }
                            }
                        }
                    }
                }
            }
        }

        // Add Vaadin package
        packages.add(new PackageDefinition("v", "com.vaadin.ui"));
        return packages;
    }

    /**
     * Returns package name by prefix. Returns null when not found.
     *
     * @param prefix Prefix
     * @return Package name
     */
    public PackageDefinition getPackageByPrefix(String prefix) {
        if (prefix != null) {
            for (PackageDefinition pkg : getPackages()) {
                if (prefix.equals(pkg.getPrefix())) {
                    return pkg;
                }
            }
        }
        return null;
    }

    /**
     * Returns component by ID.
     *
     * @param id ID
     * @return
     */
    public VDComponent getComponentById(String id) {
        for (VDComponent component : PsiTreeUtil.findChildrenOfType(this, VDComponent.class)) {
            if (id.equals(component.getId())) {
                return component;
            }
        }
        return null;
    }


    /**
     * Holder for prefix definition of custom package components.
     */
    public static class PackageDefinition {

        private String prefix;
        private String packageName;

        public PackageDefinition() {
        }

        public PackageDefinition(String prefix, String packageName) {
            this.prefix = prefix;
            this.packageName = packageName;
        }

        public String getPrefix() {
            return prefix;
        }

        public void setPrefix(String prefix) {
            this.prefix = prefix;
        }

        public String getPackageName() {
            return packageName;
        }

        public void setPackageName(String packageName) {
            this.packageName = packageName;
        }
    }
}
