package entity.animals.herbivores;

import entity.animals.Animal;
import entity.plants.Plant;

public abstract class Herbivore extends Animal {
    public static int herbivoreCount = 0;

    public Herbivore(String name, int initialEnergy){
        super(name, initialEnergy);
        herbivoreCount++;
    }

    @Override
    public void eat(Object food) {
        if  (food instanceof Plant){
            System.out.println(getName() + " ate plant.");
            increaseEnergy(10);
        } else if (this instanceof Duck && food instanceof Caterpillar){
            System.out.println(getName() + " ate caterpillar.");
            increaseEnergy(15);
        } else {
            System.out.println(getName() + " can't eat that.");
        }
    }
}
