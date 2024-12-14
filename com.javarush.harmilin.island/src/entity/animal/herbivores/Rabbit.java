package entity.animal.herbivores;

import entity.animal.Animal;
import entity.island.Island;

import java.util.concurrent.atomic.AtomicInteger;

public class Rabbit extends Herbivore{
    public static AtomicInteger rabbitCount = new AtomicInteger(0);

    public Rabbit(Island island) {
        super(island,2, 2, 0.45, 100);
        rabbitCount.incrementAndGet();
    }

    @Override
    public String getIcon() {
        return "🐇";
    }

    @Override
    protected Animal createOffspring() {
        return new Rabbit(getIsland());
    }
}
