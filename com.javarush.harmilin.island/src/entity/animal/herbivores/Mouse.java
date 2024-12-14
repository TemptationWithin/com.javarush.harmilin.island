package entity.animal.herbivores;

import entity.animal.Animal;
import entity.island.Island;

import java.util.concurrent.atomic.AtomicInteger;

public class Mouse extends Herbivore{
    public static AtomicInteger mouseCount = new AtomicInteger(0);

    public Mouse(Island island) {
        super(island,0.05, 1, 0.01, 100);
        setPreyChance(Caterpillar.class, 90);
        mouseCount.incrementAndGet();
    }

    @Override
    public String getIcon() {
        return "🐁";
    }

    @Override
    protected Animal createOffspring() {
        return new Mouse(getIsland());
    }
}
