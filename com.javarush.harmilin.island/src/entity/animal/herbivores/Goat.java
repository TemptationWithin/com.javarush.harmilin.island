package entity.animal.herbivores;

import entity.animal.Animal;
import entity.island.Island;

public class Goat extends Herbivore{
    public static int goatCount = 0;

    public Goat(Island island) {
        super(island,70, 3, 10, 100);
        goatCount++;
    }

    @Override
    public String getIcon() {
        return "🐐";
    }

    @Override
    protected Animal createOffspring() {
        return new Goat(getIsland());
    }
}
