package entity.animal.herbivore;

import entity.animal.Animal;
import entity.island.Island;

import java.util.concurrent.atomic.AtomicInteger;

public class Buffalo extends Herbivore{
    public static AtomicInteger buffaloCount = new AtomicInteger(0);

    public Buffalo(Island island) {
        super(island,700, 3, 100, 100);
        setSpeed(getMaxSpeed());
        buffaloCount.incrementAndGet();
    }

    @Override
    public String getIcon() {
        return "🐃";
    }

    @Override
    protected Animal createOffspring() {
        return new Buffalo(getIsland());
    }
}
