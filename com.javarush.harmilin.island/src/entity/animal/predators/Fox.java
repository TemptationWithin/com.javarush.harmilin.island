package entity.animal.predators;

import entity.animal.herbivores.*;

public class Fox extends Predator{
    public static int foxCount = 0;

    public Fox() {
        super(8, 2, 2, 100);
        setPreyChance(Rabbit.class, 70);
        setPreyChance(Mouse.class, 90);;
        setPreyChance(Duck.class, 60);
        setPreyChance(Caterpillar.class, 40);
        foxCount++;
    }

    @Override
    public void reproduce() {
        System.out.println(getIcon() + " gave birth.");
    }

    @Override
    public String getIcon() {
        return "🦊";
    }
}
