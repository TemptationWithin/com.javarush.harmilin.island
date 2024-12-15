package entity.animal.predator;

import entity.animal.Animal;
import entity.animal.CanFly;
import entity.animal.herbivore.*;
import entity.island.Island;

import java.util.concurrent.atomic.AtomicInteger;

public class Eagle extends Predator implements CanFly {
    public static AtomicInteger eagleCount = new AtomicInteger(0);

    public Eagle(Island island) {
        super(island, 6, 3, 1, 100);
        setPreyChance(Fox.class, 10);
        setPreyChance(Rabbit.class, 90);
        setPreyChance(Mouse.class, 90);
        setPreyChance(Duck.class, 80);
        setSpeed(getMaxSpeed());
        eagleCount.incrementAndGet();
    }

    @Override
    public String getIcon() {
        return "🦅";
    }

    @Override
    protected Animal createOffspring() {
        return new Eagle(getIsland());
    }
}
