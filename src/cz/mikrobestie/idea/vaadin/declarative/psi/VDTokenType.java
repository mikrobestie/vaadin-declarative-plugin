package cz.mikrobestie.idea.vaadin.declarative.psi;

import com.intellij.psi.tree.IElementType;
import cz.mikrobestie.idea.vaadin.declarative.VaadinDeclarativeLanguage;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

/**
 * Created by Michal on 10.11.2015.
 */
public class VDTokenType extends IElementType {

    public VDTokenType(@NotNull @NonNls String debugName) {
        super(debugName, VaadinDeclarativeLanguage.INSTANCE);
    }

    @Override
    public String toString() {
        return "VDTokenType." + super.toString();
    }
}
