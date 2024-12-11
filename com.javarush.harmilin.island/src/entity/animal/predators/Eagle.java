package entity.animal.predators;

import entity.animal.Animal;
import entity.animal.herbivores.*;
import entity.island.Island;

public class Eagle extends Predator{
    public static int eagleCount = 0;

    public Eagle(Island island) {
        super(island, 6, 3, 1, 100);
        setPreyChance(Fox.class, 10);
        setPreyChance(Rabbit.class, 90);
        setPreyChance(Mouse.class, 90);
        setPreyChance(Duck.class, 80);
        eagleCount++;
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
