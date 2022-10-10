package model;

import model.Player.PlayerBO;
import model.Player.PlayerDAO;
import model.Player.PlayerVO;

public class Facade {
    public static boolean createPlayer(PlayerVO playerVO){
        return PlayerBO.inserir(playerVO);
    }

    public static void loadPlayer(int id){
        PlayerDAO playerDAO = new PlayerDAO();
        PlayerVO playerVO = playerDAO.getById(id);
        playerDAO.updateCollectables(playerVO);
    }
}
