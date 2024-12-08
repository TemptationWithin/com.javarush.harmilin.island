package entity.plant;

import lombok.Data;

@Data
public class Plant {

    private int x_Coordinate, y_Coordinate;
    private String icon;

    public String grow(){
        return "Plants grow.";
    }

    public int getX_Coordinate() {
        return x_Coordinate;
    }

    public void setX_Coordinate(int x_Coordinate) {
        this.x_Coordinate = x_Coordinate;
    }

    public int getY_Coordinate() {
        return y_Coordinate;
    }

    public void setY_Coordinate(int y_Coordinate) {
        this.y_Coordinate = y_Coordinate;
    }

    public String getIcon() {
        return "🌱";
    }
}
