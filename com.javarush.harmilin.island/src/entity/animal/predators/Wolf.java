package entity.animal.predators;

import entity.animal.Animal;
import entity.animal.herbivores.*;

public class Wolf extends Predator{
    public static int wolfCount = 0;

    public Wolf() {
        super(50, 3, 8, 100);
        setPreyChance(Horse.class, 10);
        setPreyChance(Deer.class, 15);
        setPreyChance(Rabbit.class, 60);
        setPreyChance(Mouse.class, 80);
        setPreyChance(Goat.class, 60);
        setPreyChance(Sheep.class, 70);
        setPreyChance(Boar.class, 15);
        setPreyChance(Buffalo.class, 10);
        setPreyChance(Duck.class, 40);
        wolfCount++;
    }

    @Override
    public void reproduce() {
        System.out.println(getIcon() + " gave birth.");
    }

    @Override
    public String getIcon() {
        return "🐺";
    }

    @Override
    protected Animal createOffspring() {
        return new Wolf();
    }
}
