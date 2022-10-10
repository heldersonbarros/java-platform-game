package model.Skin;

import model.ConnectionManager;
import model.Player.PlayerVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SkinDAO {
    public ArrayList<SkinVO> getByPlayerId(int id){
        Connection connection = ConnectionManager.getConnection();
        String sql = "SELECT FROM Skin WHERE player_id = (?)";
        ArrayList<SkinVO> skinVOS = new ArrayList<SkinVO>();
        if (connection!=null){
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();

                while(resultSet.next()){
                    SkinVO skinVO = new SkinVO();
                    skinVO.setId(resultSet.getInt(1));
                    skinVO.setName(resultSet.getString(2));
                    skinVOS.add(skinVO);
                }

                resultSet.close();
                preparedStatement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return skinVOS;
    }
}
