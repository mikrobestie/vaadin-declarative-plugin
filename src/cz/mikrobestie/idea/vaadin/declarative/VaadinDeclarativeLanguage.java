package cz.mikrobestie.idea.vaadin.declarative;

import com.intellij.lang.Language;

/**
 * Created by Michal on 9.11.2015.
 */
public class VaadinDeclarativeLanguage extends Language {

    public static final VaadinDeclarativeLanguage INSTANCE = new VaadinDeclarativeLanguage();


    /**
     * Popis jazyka.
     */
    protected VaadinDeclarativeLanguage() {
        super("Vaadin declarative");
    }
}
