package entity.plant;

import entity.island.Island;
import lombok.Data;

@Data
public class Plant {
    private int x_Coordinate, y_Coordinate;
    private String icon;
    private final int maxWeight = 1;

    public Plant(Island island){
    }

    public String grow(){
        return "Plants grow.";
    }


    public String getIcon() {
        return "🌱";
    }
}
