package entity.animal.herbivores;

import entity.animal.Animal;
import entity.plant.Plant;

public abstract class Herbivore extends Animal {
    public static int herbivoreCount = 0;

    public Herbivore(int initialEnergy){
        super(initialEnergy);
        herbivoreCount++;
    }

    @Override
    public void eat(Object food) {
        if  (food instanceof Plant){
            System.out.println(getSymbol() + " ate plant.");
            increaseEnergy(10);
        } else if (this instanceof Duck && food instanceof Caterpillar){
            System.out.println(getSymbol() + " ate caterpillar.");
            increaseEnergy(15);
        } else {
            System.out.println(getSymbol() + " can't eat that.");
        }
    }
}
