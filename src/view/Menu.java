package view;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;

import static view.Utils.center_text_horizontally;
import static view.Utils.center_text_vertically;

public class Menu {

    public static final int BUTTON_WIDTH = Game.WINDOW_WIDTH / 3;
    public static final int BUTTON_HEIGHT = 40;
    public static final int BUTTON_X_POS = (Game.WINDOW_WIDTH - Game.WINDOW_WIDTH / 3) / 2;
    public static final int NEW_GAME_BUTTON_Y_POS = 150;
    public static final int LOAD_GAME_BUTTON_Y_POS = 220;
    public static final int PLAY_BUTTON_Y_POS = 290;
    public static final int SHOP_BUTTON_Y_POS = 360;
    public static final int QUIT_BUTTON_Y_POS = 430;
    private final Font logo_font = new Font("Arial", Font.BOLD, 30);
    private final Font button_font = new Font("Arial", Font.BOLD, 20);
    private final BufferedImage frame;
    private final Graphics frameGraphics;

    private Game game;

    public Menu(Game game){
        this.game = game;
        frame = new BufferedImage(Game.WINDOW_WIDTH, Game.WINDOW_HEIGHT, BufferedImage.TYPE_4BYTE_ABGR);
        frameGraphics = frame.createGraphics();
    }

    public void paint (Graphics g) {
        frameGraphics.setColor(Color.BLACK);
        frameGraphics.fillRect(0,0,Game.WINDOW_WIDTH, Game.WINDOW_HEIGHT);

        frameGraphics.setColor(Color.WHITE);

        frameGraphics.setFont(logo_font);
        frameGraphics.drawString("PLATFORM GAME", center_text_horizontally(frameGraphics, "PLATFORM GAME", Game.WINDOW_WIDTH, 0), 100);

        frameGraphics.setFont(button_font);

        frameGraphics.drawRect(BUTTON_X_POS, NEW_GAME_BUTTON_Y_POS, BUTTON_WIDTH, BUTTON_HEIGHT);
        frameGraphics.drawString("NEW GAME", center_text_horizontally(frameGraphics, "NEW GAME", Game.WINDOW_WIDTH, 0),
                center_text_vertically(frameGraphics, "NEW GAME", BUTTON_HEIGHT, NEW_GAME_BUTTON_Y_POS));

        frameGraphics.drawRect(BUTTON_X_POS, LOAD_GAME_BUTTON_Y_POS, BUTTON_WIDTH, BUTTON_HEIGHT);
        frameGraphics.drawString("LOAD GAME", center_text_horizontally(frameGraphics, "LOAD GAME", Game.WINDOW_WIDTH, 0),
                center_text_vertically(frameGraphics, "LOAD GAME", BUTTON_HEIGHT, LOAD_GAME_BUTTON_Y_POS));

        frameGraphics.drawRect(BUTTON_X_POS, PLAY_BUTTON_Y_POS, BUTTON_WIDTH, BUTTON_HEIGHT);
        frameGraphics.drawString("PLAY", center_text_horizontally(frameGraphics, "PLAY", Game.WINDOW_WIDTH, 0),
                center_text_vertically(frameGraphics, "PLAY", BUTTON_HEIGHT, PLAY_BUTTON_Y_POS));

        frameGraphics.drawRect(BUTTON_X_POS, SHOP_BUTTON_Y_POS, BUTTON_WIDTH, BUTTON_HEIGHT);
        frameGraphics.drawString("SHOP", center_text_horizontally(frameGraphics, "SHOP", Game.WINDOW_WIDTH, 0),
                center_text_vertically(frameGraphics, "SHOP", BUTTON_HEIGHT, SHOP_BUTTON_Y_POS));

        frameGraphics.drawRect(BUTTON_X_POS, QUIT_BUTTON_Y_POS, BUTTON_WIDTH, BUTTON_HEIGHT);
        frameGraphics.drawString("QUIT", center_text_horizontally(frameGraphics, "QUIT", Game.WINDOW_WIDTH, 0),
                center_text_vertically(frameGraphics, "QUIT", BUTTON_HEIGHT, QUIT_BUTTON_Y_POS));

        g.drawImage(frame, 0, 0, null);
    }
}
