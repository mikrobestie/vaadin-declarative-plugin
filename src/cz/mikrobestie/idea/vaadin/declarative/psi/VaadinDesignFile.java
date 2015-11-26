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
    public PackageDefinition[] getPackages() {

        VDHtmlTag html = PsiTreeUtil.getChildOfType(this, VDHtmlTag.class);
        if (html != null) {
            VDHeadTag head = PsiTreeUtil.getChildOfType(html, VDHeadTag.class);
            if (head != null) {
                VDMetaTag[] metaTags = PsiTreeUtil.getChildrenOfType(head, VDMetaTag.class);
                PackageDefinition[] definitions = new PackageDefinition[metaTags.length];
                for (int i = 0; i < metaTags.length; i++) {
                    VDMetaTag mt = metaTags[i];
                    PackageDefinition def = new PackageDefinition();
                    for (VDAttr attr : mt.getAttrList()) {
                        if (attr.getFirstChild().getText().equals("content")) {

                            String text1 = attr.getLastChild().getText();
                            String text = text1.substring(1, text1.length() - 1);
                            String[] split = text.split(":");
                            def.setPrefix(split[0]);
                            def.setPackageName(split[1]);
                        }
                        definitions[i] = def;
                    }
                    return definitions;
                }
            }
        }

        return new PackageDefinition[0];
    }

    /**
     * Returns package name by prefix. Returns null when not found.
     *
     * @param prefix Prefix
     * @return Package name
     */
    public PackageDefinition getPackageByPrefix(String prefix) {
        for (PackageDefinition pkg : getPackages()) {
            if (prefix.equals(pkg.getPrefix())) {
                return pkg;
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
