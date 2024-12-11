package entity.animal.predators;

import entity.animal.Animal;

public abstract class Predator extends Animal {
    public static int predatorCount = 0;

    public Predator(double maxWeight, int maxSpeed, double foodRequired, int initialEnergy){
        super(maxWeight, maxSpeed, foodRequired, initialEnergy);
        predatorCount++;
    }

}
