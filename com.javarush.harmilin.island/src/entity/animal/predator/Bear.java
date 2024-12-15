package entity.animal.predator;

import entity.animal.Animal;
import entity.animal.herbivore.*;
import entity.island.Island;

import java.util.concurrent.atomic.AtomicInteger;

public class Bear extends Predator{
    public static AtomicInteger bearCount = new AtomicInteger(0);

    public Bear(Island island) {
        super(island,500, 2, 80, 100);
        setPreyChance(Boa.class, 80);
        setPreyChance(Horse.class, 40);
        setPreyChance(Deer.class, 80);
        setPreyChance(Rabbit.class, 80);
        setPreyChance(Mouse.class, 90);
        setPreyChance(Goat.class, 70);
        setPreyChance(Sheep.class, 70);
        setPreyChance(Boar.class, 50);
        setPreyChance(Buffalo.class, 20);
        setPreyChance(Duck.class, 10);
        bearCount.incrementAndGet();
    }

    @Override
    public String getIcon() {
        return "🐻";
    }

    @Override
    protected Animal createOffspring() {
        return new Bear(getIsland());
    }
}