package model.Player;

import model.Skin.SkinDAO;
import model.Skin.SkinVO;

import java.util.ArrayList;

public class PlayerVO {
    private int id;
    private String name;
    private ArrayList<SkinVO> skinVOS;
    private int collectables;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<SkinVO> getSkinVOS() {
        if (skinVOS == null){
            SkinDAO skinDAO = new SkinDAO();
            skinVOS = skinDAO.getByPlayerId(id);
        }
        return skinVOS;
    }

    public void setSkinVOS(ArrayList<SkinVO> skinVOS) {
        this.skinVOS = skinVOS;
    }

    public int getCollectables() {
        return collectables;
    }

    public void setCollectables(int collectables) {
        this.collectables = collectables;
    }
}
