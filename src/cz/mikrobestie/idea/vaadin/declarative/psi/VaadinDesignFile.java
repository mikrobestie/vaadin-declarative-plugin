package cz.mikrobestie.idea.vaadin.declarative.psi;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
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
}
