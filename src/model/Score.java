package model;

import model.Observer.Observer;

public class Score extends Observer {
    private int points = 0;

    @Override
    public void update(boolean collectableCatch) {
        points+=1;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
