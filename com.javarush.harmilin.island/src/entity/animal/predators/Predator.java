package entity.animal.predators;

import entity.animal.Animal;
import entity.island.Island;

public abstract class Predator extends Animal {
    public static int predatorCount = 0;

    public Predator(Island island, double maxWeight, int maxSpeed, double foodRequired, int initialEnergy){
        super(island, maxWeight, maxSpeed, foodRequired, initialEnergy);
        predatorCount++;
    }

}
