package model.Player;

import model.Observer.Observer;
import model.ConnectionManager;
import view.Game;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlayerDAO extends Observer {
    public void insert(PlayerVO playerVO){
        Connection connection = ConnectionManager.getConnection();

        String sql = "INSERT INTO player (name) VALUES (?);";
        if (connection!=null){
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, playerVO.getName());

                if(preparedStatement.executeUpdate() == 0){
                    preparedStatement.close();
                    connection.close();
                    throw new Exception("Error to insert player");
                } else{
                    preparedStatement.close();
                    String last_id = "SELECT MAX(rowid) FROM player";
                    PreparedStatement preparedStatement1 = connection.prepareStatement(last_id);
                    ResultSet resultSet = preparedStatement1.executeQuery();
                    int id = 0;
                    if (resultSet.next()){
                        id = resultSet.getInt(1);
                    }
                    resultSet.close();
                    preparedStatement1.close();
                    playerVO.setId(id);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public PlayerVO getById(int id){
        Connection connection = ConnectionManager.getConnection();
        PlayerVO playerVO = null;
        String sql = "SELECT FROM player WHERE id = (?)";
        if (connection!=null){
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                if(resultSet.next()){
                    playerVO = new PlayerVO();
                    playerVO.setId(resultSet.getInt(1));
                    playerVO.setName(resultSet.getString(2));
                }

                resultSet.close();
                preparedStatement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return playerVO;
    }

    public void updateCollectables(PlayerVO playerVO){
        Connection connection = ConnectionManager.getConnection();
        String sql = "UPDATE player (collectables) VALUES (?);";
        if (connection!=null){
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, playerVO.getName());

                if(preparedStatement.executeUpdate() == 0){
                    preparedStatement.close();
                    connection.close();
                    throw new Exception("Error to update player collectables");
                } else{
                    preparedStatement.close();
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void update(boolean collectableCatch) {
        updateCollectables(Game.playerVO);
    }
}
