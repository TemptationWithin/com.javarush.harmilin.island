package entity.animal.predator;

import entity.animal.Animal;
import entity.animal.herbivore.*;
import entity.island.Island;

import java.util.concurrent.atomic.AtomicInteger;

public class Boa extends Predator{
    public static AtomicInteger boaCount = new AtomicInteger(0);

    public Boa(Island island) {
        super(island, 15, 1, 3, 100);
        setPreyChance(Fox.class, 15);
        setPreyChance(Rabbit.class, 20);
        setPreyChance(Mouse.class, 40);
        setPreyChance(Duck.class, 10);
        boaCount.incrementAndGet();
    }

    @Override
    public String getIcon() {
        return "🐍";
    }

    @Override
    protected Animal createOffspring() {
        return new Boa(getIsland());
    }
}
