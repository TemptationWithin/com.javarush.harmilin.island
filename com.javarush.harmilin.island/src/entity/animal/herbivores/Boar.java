package entity.animal.herbivores;


import entity.animal.Animal;
import entity.island.Island;

import java.util.concurrent.atomic.AtomicInteger;

public class Boar extends Herbivore{
    public static AtomicInteger boarCount = new AtomicInteger(0);

    public Boar(Island island) {
        super(island,400, 2, 50, 100);
        setPreyChance(Mouse.class, 50);
        setPreyChance(Caterpillar.class, 90);
        boarCount.incrementAndGet();
    }

    @Override
    public String getIcon() {
        return "🐗";
    }

    @Override
    protected Animal createOffspring() {
        return new Boar(getIsland());
    }
}
