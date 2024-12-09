package entity.animal.predators;

import entity.animal.Animal;
import entity.animal.herbivores.*;
import entity.island.Island;

public class Boa extends Predator{
    public static int boaCount = 0;

    public Boa(Island island) {
        super(island, 15, 1, 3, 100);
        setPreyChance(Fox.class, 15);
        setPreyChance(Rabbit.class, 20);
        setPreyChance(Mouse.class, 40);
        setPreyChance(Duck.class, 10);
        boaCount++;
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
