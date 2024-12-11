package entity.animal.herbivores;

import entity.animal.Animal;
import entity.island.Island;

public class Sheep extends Herbivore{
    public static int sheepCount = 0;

    public Sheep(Island island) {
        super(island, 70, 3, 15, 100);
        sheepCount++;
    }

    @Override
    public String getIcon() {
        return "🐑";
    }

    @Override
    protected Animal createOffspring() {
        return new Sheep(getIsland());
    }
}
