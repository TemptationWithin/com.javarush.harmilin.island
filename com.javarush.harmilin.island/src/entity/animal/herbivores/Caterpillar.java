package entity.animal.herbivores;

import entity.animal.Animal;
import entity.animal.NotMovable;
import entity.island.Island;

public class Caterpillar extends Herbivore implements NotMovable {
    public static int caterpillarCount = 0;

    public Caterpillar(Island island) {
        super(island,0.01, 0, 0, 100);
        caterpillarCount++;
    }

    @Override
    public String getIcon() {
        return "🐛";
    }

    @Override
    protected Animal createOffspring() {
        return new Caterpillar(getIsland());
    }
}
