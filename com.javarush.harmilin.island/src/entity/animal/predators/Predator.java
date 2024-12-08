package entity.animal.predators;

import entity.animal.Animal;
import entity.animal.herbivores.Herbivore;

public abstract class Predator extends Animal {
    public static int predatorCount = 0;

    public Predator(int initialEnergy){
        super(initialEnergy);
        predatorCount++;
    }

    @Override
    public void eat(Object food) {
        if (food instanceof Herbivore){
            System.out.println(getIcon() + " ate " + (((Herbivore) food).getIcon()) + ".");
            increaseEnergy(20);
        } else {
            System.out.println(getIcon() + " can't eat this.");
        }
    }

}
