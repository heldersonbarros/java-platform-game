package controller;

import model.Facade;
import view.Game;
import view.Map;
import view.Menu;
import view.NewGame;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MenuController {

    private final Game game;

    public MenuController(Game game) {
        this.game = game;
    }

    public void init(){
        game.addMouseListener(new MouseHandler());
        // close game if VK_ESC is pressed
    }

    private class MouseHandler implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {
            int x = e.getX();
            int y = e.getY();

            if (x >= Menu.BUTTON_X_POS && x <= Menu.BUTTON_X_POS + Menu.BUTTON_WIDTH){
                if (y >= Menu.NEW_GAME_BUTTON_Y_POS && y <= Menu.NEW_GAME_BUTTON_Y_POS + Menu.BUTTON_HEIGHT) {
                    Game.game_state = Game.GAME_STATE.NEW_GAME;
                    NewSaveController newSaveController = new NewSaveController(game, game.getNewGame());
                    newSaveController.init();
                }
                else if (y >= Menu.LOAD_GAME_BUTTON_Y_POS && y <= Menu.LOAD_GAME_BUTTON_Y_POS + Menu.BUTTON_HEIGHT) {
                    Game.game_state = Game.GAME_STATE.LOAD_GAME;
                }
                else if (y >= Menu.PLAY_BUTTON_Y_POS && y <= Menu.PLAY_BUTTON_Y_POS + Menu.BUTTON_HEIGHT) {
                    Game.game_state = Game.GAME_STATE.GAME;
                    GameController controller = new GameController(game);
                    game.setMap(new Map());
                    controller.init();
                }
                else if (y >= Menu.SHOP_BUTTON_Y_POS && y <= Menu.SHOP_BUTTON_Y_POS + Menu.BUTTON_HEIGHT) {
                    System.out.println("SHOP");
                }
                else if (y >= Menu.QUIT_BUTTON_Y_POS && y <= Menu.QUIT_BUTTON_Y_POS + Menu.BUTTON_HEIGHT) {
                    System.out.println("QUIT");
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
}
