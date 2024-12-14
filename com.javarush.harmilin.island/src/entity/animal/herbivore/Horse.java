package entity.animal.herbivore;

import entity.animal.Animal;
import entity.island.Island;

import java.util.concurrent.atomic.AtomicInteger;

public class Horse extends Herbivore{
    public static AtomicInteger horseCount = new AtomicInteger(0);

    public Horse(Island island) {
        super(island, 400, 4, 60, 100);
        horseCount.incrementAndGet();
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
