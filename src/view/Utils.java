package view;

import java.awt.*;

public class Utils {
    public static int center_text_horizontally(Graphics g, String text, int width, int pos){
        int font_width = g.getFontMetrics(g.getFont()).stringWidth(text);
        return pos + (width-font_width)/2;
    }

    // remember to look at getAscent method
    public static int center_text_vertically(Graphics g, String text, int height, int pos_y){
        FontMetrics fontMetrics = g.getFontMetrics();
        return pos_y + ((height - fontMetrics.getHeight()) / 2) + fontMetrics.getAscent();
    }
}
