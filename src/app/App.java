package app;

import controller.GameController;
import controller.MenuController;
import model.ConnectionManager;
import view.Game;

public class App {
	public static void main(String[] args) {
		ConnectionManager.createTables();
		Game game = new Game();
		MenuController menuController = new MenuController(game);
		menuController.init();
		game.run();
	}
}
