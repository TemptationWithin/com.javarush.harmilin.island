package entity.animal.herbivore;

import entity.animal.Animal;
import entity.island.Island;

import java.util.concurrent.atomic.AtomicInteger;

public class Goat extends Herbivore{
    public static AtomicInteger goatCount = new AtomicInteger(0);

    public Goat(Island island) {
        super(island,70, 3, 10, 100);
        setSpeed(getMaxSpeed());
        goatCount.incrementAndGet();
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
