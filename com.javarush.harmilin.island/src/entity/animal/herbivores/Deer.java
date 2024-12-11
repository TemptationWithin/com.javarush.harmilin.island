package entity.animal.herbivores;

import entity.animal.Animal;
import entity.island.Island;

public class Deer extends Herbivore{
    public static int deerCount = 0;

    public Deer(Island island) {
        super(island,300, 4, 50, 100);
        deerCount++;
    }

    @Override
    public String getIcon() {
        return "🦌";
    }

    @Override
    protected Animal createOffspring() {
        return new Deer(getIsland());
    }
}
