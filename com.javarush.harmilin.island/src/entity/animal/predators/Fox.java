package entity.animal.predators;

import entity.animal.Animal;
import entity.animal.herbivores.*;
import entity.island.Island;

public class Fox extends Predator{
    public static int foxCount = 0;

    public Fox(Island island) {
        super(island, 8, 2, 2, 100);
        setPreyChance(Rabbit.class, 70);
        setPreyChance(Mouse.class, 90);;
        setPreyChance(Duck.class, 60);
        setPreyChance(Caterpillar.class, 40);
        foxCount++;
    }

    @Override
    public String getIcon() {
        return "🦊";
    }

    @Override
    protected Animal createOffspring() {
        return new Fox(getIsland());
    }
}
