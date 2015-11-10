package cz.mikrobestie.idea.vaadin.declarative;

import com.intellij.lang.Language;
import com.intellij.openapi.fileTypes.LanguageFileType;
import com.intellij.openapi.vfs.CharsetToolkit;
import com.intellij.openapi.vfs.VirtualFile;
import cz.mikrobestie.idea.vaadin.declarative.icons.VaadinIcons;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * Created by Michal on 9.11.2015.
 */
public class VaadinDesignFileType extends LanguageFileType {

    public static final VaadinDesignFileType INSTANCE = new VaadinDesignFileType();


    /**
     * Typ souboru Vaadin Designu.
     */
    protected VaadinDesignFileType() {
        super(VaadinDeclarativeLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public String getName() {
        return "Vaadin Design";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "Vaadin Declarative Design File";
    }

    @NotNull
    @Override
    public String getDefaultExtension() {
        return "html";
    }

    @Nullable
    @Override
    public Icon getIcon() {
        return VaadinIcons.VAADIN_16;
    }

    @Override
    public String getCharset(@NotNull VirtualFile file, @NotNull byte[] content) {
        return CharsetToolkit.UTF8;
    }
}
