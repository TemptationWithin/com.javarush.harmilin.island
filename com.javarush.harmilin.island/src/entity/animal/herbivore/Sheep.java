package entity.animal.herbivore;

import entity.animal.Animal;
import entity.island.Island;

import java.util.concurrent.atomic.AtomicInteger;

public class Sheep extends Herbivore{
    public static AtomicInteger sheepCount = new AtomicInteger(0);

    public Sheep(Island island) {
        super(island, 70, 3, 15, 100);
        sheepCount.incrementAndGet();
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
