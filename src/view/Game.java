package view;

import model.Player.PlayerVO;

import java.awt.Graphics;
import java.io.Serial;

import javax.swing.JFrame;

public class Game extends JFrame implements Runnable {
    @Serial
    private static final long serialVersionUID = 1L;

    public static int WINDOW_WIDTH = 592;
    public static int WINDOW_HEIGHT = 496;

    private final Menu menu;
    private final NewGame newGame;
    private final LoadGame loadGame;

    public static PlayerVO playerVO;
    private Map map;

    public enum GAME_STATE {
        MENU,
        NEW_GAME,
        LOAD_GAME,
        GAME
    }

    public static GAME_STATE game_state = GAME_STATE.MENU;

    public Game() {
        setTitle("Platform Game");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);
        setUndecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        menu = new Menu(this);
        newGame = new NewGame();
        loadGame = new LoadGame();

        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        if (game_state == GAME_STATE.GAME){
            map.paint(g);
        } else if (game_state == GAME_STATE.MENU){
            menu.paint(g);
        } else if (game_state == GAME_STATE.NEW_GAME){
            newGame.paint(g);
        } else if (game_state == GAME_STATE.LOAD_GAME){
            loadGame.paint(g);
        }
    }

    @Override
    public void run() {
        while (true) {
            repaint();
        }
    }

    public static int getWindowWidth() {
        return WINDOW_WIDTH;
    }

    public static void setWindowWidth(int windowWidth) {
        WINDOW_WIDTH = windowWidth;
    }

    public static int getWindowHeight() {
        return WINDOW_HEIGHT;
    }

    public static void setWindowHeight(int windowHeight) {
        WINDOW_HEIGHT = windowHeight;
    }

    public Menu getMenu() {
        return menu;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public static GAME_STATE getGame_state() {
        return game_state;
    }

    public static void setGame_state(GAME_STATE game_state) {
        Game.game_state = game_state;
    }

    public NewGame getNewGame() {
        return newGame;
    }

    public LoadGame getLoadGame() {
        return loadGame;
    }

    public PlayerVO getPlayerVO() {
        return playerVO;
    }

    public void setPlayerVO(PlayerVO playerVO) {
        this.playerVO = playerVO;
    }
}
