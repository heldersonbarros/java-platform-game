package controller;

import model.Facade;
import model.Player.PlayerVO;
import view.Game;
import view.Menu;
import view.NewGame;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class NewSaveController {
    private final NewGame newGame;
    private final Game game;

    public NewSaveController(Game game, NewGame newGame) {
        this.game = game;
        this.newGame = newGame;
    }

    public void init() {
        game.addMouseListener(new MouseHandler());
        game.addKeyListener(new KeyListener());
    }

    private class MouseHandler implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {
            int x = e.getX();
            int y = e.getY();
            if (x >= NewGame.INPUT_X_POS && x <= NewGame.INPUT_X_POS + NewGame.INPUT_WIDTH){
                if (y >= NewGame.TEXT_FIELD_Y_POS && y <= NewGame.TEXT_FIELD_Y_POS + NewGame.INPUT_HEIGHT) {
                    PlayerVO playerVO = new PlayerVO();
                    playerVO.setName(newGame.getInputText());
                    Facade.createPlayer(playerVO);
                }
            } else if (x >= NewGame.BACK_BUTTON_X_POS && x <= NewGame.BACK_BUTTON_X_POS + NewGame.BACK_BUTTON_WIDTH){
                if (y >= NewGame.BACK_BUTTON_Y_POS && y <= NewGame.BACK_BUTTON_Y_POS + NewGame.INPUT_HEIGHT){
                    Game.game_state = Game.GAME_STATE.NEW_GAME;
                }
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    private class KeyListener extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE){
                newGame.setInputText(newGame.getInputText().substring(0, newGame.getInputText().length() - 1));
            } else if (KeyEvent.getKeyText(e.getKeyCode()).length() == 1) {
                newGame.setInputText(newGame.getInputText() + e.getKeyChar());
            }
        }
    }
}