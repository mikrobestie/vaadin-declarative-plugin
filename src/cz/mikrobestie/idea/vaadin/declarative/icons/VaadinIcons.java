package cz.mikrobestie.idea.vaadin.declarative.icons;

import com.intellij.openapi.util.IconLoader;

import javax.swing.*;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Michal on 9.11.2015.
 */
public class VaadinIcons {

    public static final Icon VAADIN_16 = IconLoader.getIcon("/cz/mikrobestie/idea/vaadin/declarative/icons/vaadin_16.png");

    public static final Icon VAADIN_128 = IconLoader.getIcon("/cz/mikrobestie/idea/vaadin/declarative/icons/vaadin_128.png");

    // "\u005b"
    public static final Icon FA_TEST = new FontAwesomeIcon(0xf0c0, 14);



    private static final Map<Integer, FontAwesomeIcon> cache = new ConcurrentHashMap<>();

    public static FontAwesomeIcon fontAwesome(int codepoint) {
        FontAwesomeIcon icon = cache.get(codepoint);
        if (icon == null) {
            icon = new FontAwesomeIcon(codepoint, 14);
            cache.putIfAbsent(codepoint, icon);
        }
        return icon;
    }
}
