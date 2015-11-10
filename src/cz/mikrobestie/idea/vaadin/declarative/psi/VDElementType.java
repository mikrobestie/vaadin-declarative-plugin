package cz.mikrobestie.idea.vaadin.declarative.psi;

import com.intellij.psi.tree.IElementType;
import cz.mikrobestie.idea.vaadin.declarative.VaadinDeclarativeLanguage;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

/**
 * Created by Michal on 10.11.2015.
 */
public class VDElementType extends IElementType {

    public VDElementType(@NotNull @NonNls String debugName) {
        super(debugName, VaadinDeclarativeLanguage.INSTANCE);
    }
}
