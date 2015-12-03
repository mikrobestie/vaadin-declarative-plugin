package cz.mikrobestie.idea.vaadin.declarative.icons;

import com.intellij.ui.JBColor;
import com.intellij.util.ui.UIUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Michal on 30.11.2015.
 */
public class FontAwesomeIcon implements Icon {

    private static final Font fontAwesome;

    static {
        try (InputStream is = VaadinIcons.class.getResourceAsStream("fontawesome-webfont.ttf")) {
            fontAwesome = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(Font.PLAIN, 14);
            GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(fontAwesome);
        } catch (IOException | FontFormatException exp) {
            throw new IllegalArgumentException("Cannot load FontAwesome", exp);
        }
    }


    private BufferedImage buffer;
    private int codepoint;
    private Font font;
    private int size;
    private int width;
    private int height;

    public FontAwesomeIcon(int codepoint) {
        this(codepoint, 14);
    }

    public FontAwesomeIcon(int codepoint, int size) {
        this.codepoint = codepoint;
        this.setSize(size);
    }

    public void setSize(int size) {
        if (size > 0) {
            this.size = size;
            font = fontAwesome.deriveFont(Font.PLAIN, size);

            BufferedImage tmp = UIUtil.createImage(size, size, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = GraphicsEnvironment.getLocalGraphicsEnvironment().createGraphics(tmp);
            g2.setFont(font);
            this.width = g2.getFontMetrics().charWidth((char) codepoint);
            this.height = g2.getFontMetrics().getHeight();

            g2.dispose();

            synchronized (fontAwesome) {
                buffer = null;
            }
        }
    }

    @Override
    public int getIconHeight() {
        return height;
    }

    @Override
    public int getIconWidth() {
        return width;
    }

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        synchronized (fontAwesome) {
            if (buffer == null) {
                buffer = UIUtil.createImage(size, size, BufferedImage.TYPE_INT_ARGB);
                Graphics2D g2 = (Graphics2D) buffer.getGraphics();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setFont(font);
                g2.setColor(JBColor.BLACK);
                int sy = size - (size / 4) + (size / 16);

                String s = String.valueOf((char) codepoint);
                g2.drawString(s, 0, sy);
                g2.dispose();
            }
            g.drawImage(buffer, x, y, null);
        }
    }

    public static void main(String[] args) {

        JFrame frame = new JFrame();
        frame.setLayout(new FlowLayout());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            sb.append((char) 0xf000 + i);
        }

        JLabel jLabel = new JLabel(sb.toString());
        jLabel.setFont(fontAwesome);
//        frame.add(jLabel);

        for (int i = 0; i < 100; i++) {
            JLabel label = new JLabel(new FontAwesomeIcon(0xf000 + i));
            frame.add(label);
        }

        frame.setSize(300, 100);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
