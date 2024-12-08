package entity.animals.predators;

import entity.animals.Animal;
import entity.animals.herbivores.Herbivore;

public abstract class Predator extends Animal {
    public static int predatorCount = 0;

    public Predator(String name, int initialEnergy){
        super(name, initialEnergy);
        predatorCount++;
    }

    @Override
    public void eat(Object food) {
        if (food instanceof Herbivore){
            System.out.println(getName() + " ate " + (((Herbivore) food).getName()) + ".");
            increaseEnergy(20);
        } else {
            System.out.println(getName() + " can't eat this.");
        }
    }

}
