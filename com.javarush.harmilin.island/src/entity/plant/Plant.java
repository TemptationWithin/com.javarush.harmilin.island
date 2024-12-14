package entity.plant;

import entity.animal.NotMovable;
import entity.cell.Cell;
import entity.island.Island;
import lombok.Data;

import java.util.concurrent.atomic.AtomicInteger;

@Data
public class Plant implements NotMovable {
    public static AtomicInteger plantCount = new AtomicInteger(0);

    private int x_Coordinate, y_Coordinate;
    private Cell currentCell;
    private String icon;
    private int weight;
    Island island;

    public Plant(Island island) {
        this.island = island;
        this.weight = 1;
        plantCount.incrementAndGet();
    }

    public String getIcon() {
        return "🌱";
    }

    public boolean isAlive() {
        return weight > 0;
    }

    @Override
    public String toString() {
        return this.getIcon() + ", weight: " + this.getWeight();
    }
}
