package entity.animal.herbivores;

import entity.animal.Animal;
import entity.island.Island;

public class Rabbit extends Herbivore{
    public static int rabbitCount = 0;

    public Rabbit(Island island) {
        super(island,2, 2, 0.45, 100);
        rabbitCount++;
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
