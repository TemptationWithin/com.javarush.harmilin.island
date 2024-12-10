package entity.animal.predators;

import entity.animal.herbivores.*;

public class Boa extends Predator{
    public static int boaCount = 0;

    public Boa() {
        super(15, 1, 3, 100);
        setPreyChance(Fox.class, 15);
        setPreyChance(Rabbit.class, 20);
        setPreyChance(Mouse.class, 40);
        setPreyChance(Duck.class, 10);
        boaCount++;
    }

    @Override
    public void reproduce() {
        System.out.println(getIcon() + " laid eggs.");
    }

    @Override
    public String getIcon() {
        return "🐍";
    }
}
