package entity.animal.herbivores;

import entity.animal.Animal;
import entity.animal.NotMovable;
import entity.island.Island;

import java.util.concurrent.atomic.AtomicInteger;

public class Caterpillar extends Herbivore implements NotMovable {
    public static AtomicInteger caterpillarCount = new AtomicInteger(0);

    public Caterpillar(Island island) {
        super(island,0.01, 0, 0, 100);
        caterpillarCount.incrementAndGet();
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
