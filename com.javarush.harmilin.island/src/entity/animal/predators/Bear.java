package entity.animal.predators;

import entity.animal.Animal;
import entity.animal.herbivores.*;

public class Bear extends Predator{
    public static int bearCount = 0;

    public Bear() {
        super(500, 2, 80, 100);
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
        bearCount++;
    }

    @Override
    public void reproduce() {
        System.out.println(getIcon() + " gave birth.");
    }

    @Override
    public String getIcon() {
        return "🐻";
    }

    @Override
    protected Animal createOffspring() {
        return new Bear();
    }
}
