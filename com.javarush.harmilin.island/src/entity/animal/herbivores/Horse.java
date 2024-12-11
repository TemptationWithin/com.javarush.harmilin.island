package entity.animal.herbivores;

import entity.animal.Animal;
import entity.island.Island;

public class Horse extends Herbivore{
    public static int horseCount = 0;

    public Horse(Island island) {
        super(island, 400, 4, 60, 100);
        horseCount++;
    }

    @Override
    public String getIcon() {
        return "🐎";
    }

    @Override
    protected Animal createOffspring() {
        return new Horse(getIsland());
    }
}
