package view;

import java.awt.*;
import java.awt.image.BufferedImage;

public class NewGame {

    private final Font font = new Font("Arial", Font.BOLD, 20);
    public static final int INPUT_WIDTH = Game.WINDOW_WIDTH / 3;
    public static final int INPUT_HEIGHT = 40;
    public static final int INPUT_X_POS = (Game.WINDOW_WIDTH - Game.WINDOW_WIDTH / 3) / 2;
    public static final int TEXT_FIELD_Y_POS = Game.WINDOW_HEIGHT / 2 - INPUT_HEIGHT;
    public static final int SAVE_BUTTON_Y_POS = Game.WINDOW_HEIGHT - (INPUT_HEIGHT + 75);
    public static final int BACK_BUTTON_Y_POS  = 20;
    public static final int BACK_BUTTON_X_POS = 20;
    public static final int BACK_BUTTON_WIDTH = Game.WINDOW_WIDTH / 4;
    private final BufferedImage frame;
    private final Graphics frameGraphics;
    private String inputText = "";

    public NewGame() {
        frame = new BufferedImage(Game.WINDOW_WIDTH, Game.WINDOW_HEIGHT, BufferedImage.TYPE_4BYTE_ABGR);
        frameGraphics = frame.createGraphics();
    }

    public void paint (Graphics g) {
        frameGraphics.setColor(Color.BLACK);
        frameGraphics.fillRect(0,0,Game.WINDOW_WIDTH, Game.WINDOW_HEIGHT);
        frameGraphics.setFont(font);

        frameGraphics.setColor(Color.WHITE);

        frameGraphics.drawRect(BACK_BUTTON_X_POS, BACK_BUTTON_Y_POS, BACK_BUTTON_WIDTH, INPUT_HEIGHT);
        frameGraphics.drawString("GO BACK", Utils.center_text_horizontally(frameGraphics, "GO BACK", BACK_BUTTON_WIDTH, BACK_BUTTON_X_POS),
                Utils.center_text_vertically(frameGraphics, "GO BACK", INPUT_HEIGHT, BACK_BUTTON_Y_POS));

        frameGraphics.drawString("Type your name here", INPUT_X_POS, TEXT_FIELD_Y_POS - 25);
        frameGraphics.drawRect(INPUT_X_POS, TEXT_FIELD_Y_POS, INPUT_WIDTH, INPUT_HEIGHT);
        frameGraphics.drawString(inputText, Utils.center_text_horizontally(frameGraphics, inputText, Game.WINDOW_WIDTH, 0),
                Utils.center_text_vertically(frameGraphics, inputText, INPUT_HEIGHT, TEXT_FIELD_Y_POS));

        frameGraphics.drawRect(INPUT_X_POS, SAVE_BUTTON_Y_POS, INPUT_WIDTH, INPUT_HEIGHT);
        frameGraphics.drawString("SAVE", Utils.center_text_horizontally(frameGraphics, "SAVE", Game.WINDOW_WIDTH, 0),
                Utils.center_text_vertically(frameGraphics, "SAVE", INPUT_HEIGHT, SAVE_BUTTON_Y_POS));

        g.drawImage(frame, 0, 0, null);
    }

    public String getInputText() {
        return inputText;
    }

    public void setInputText(String inputText) {
        this.inputText = inputText;
    }
}
