package model.Player;

public class PlayerBO {
    public static boolean inserir(PlayerVO playerVO){
        if (validate(playerVO)){
            PlayerDAO playerDAO = new PlayerDAO();
            playerDAO.insert(playerVO);
            return true;
        } else{
            return false;
        }
    }

    public static boolean validate(PlayerVO playerVO){
        return playerVO != null && !playerVO.getName().isBlank();
    }
}
