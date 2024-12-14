package entity.animal.herbivores;

import entity.animal.Animal;
import entity.island.Island;

import java.util.concurrent.atomic.AtomicInteger;

public class Deer extends Herbivore{
    public static AtomicInteger deerCount = new AtomicInteger(0);

    public Deer(Island island) {
        super(island,300, 4, 50, 100);
        deerCount.incrementAndGet();
    }

    @Override
    public String getIcon() {
        return "🦌";
    }

    @Override
    protected Animal createOffspring() {
        return new Deer(getIsland());
    }
}
