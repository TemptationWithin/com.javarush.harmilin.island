package entity.animal.herbivore;

import entity.animal.Animal;
import entity.animal.CanFly;
import entity.island.Island;

import java.util.concurrent.atomic.AtomicInteger;

public class Duck extends Herbivore implements CanFly {
    public static AtomicInteger duckCount = new AtomicInteger(0);

    public Duck(Island island) {
        super(island,1, 4, 0.15, 100);
        setPreyChance(Caterpillar.class, 90);
        setSpeed(getMaxSpeed());
        duckCount.incrementAndGet();
    }

    @Override
    public String getIcon() {
        return "🦆";
    }

    @Override
    protected Animal createOffspring() {
        return new Duck(getIsland());
    }
}
