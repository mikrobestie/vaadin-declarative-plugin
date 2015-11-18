package cz.mikrobestie.idea.vaadin.declarative;

import com.intellij.lexer.FlexAdapter;
import com.intellij.lexer.FlexLexer;

/**
 * Created by Michal on 18.11.2015.
 */
public class VaadinDesignLexerAdapter extends FlexAdapter {

    public VaadinDesignLexerAdapter(FlexLexer flex) {
        super(new VaadinDesignLexer(null));
    }
}
