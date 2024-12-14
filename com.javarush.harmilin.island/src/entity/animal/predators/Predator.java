package entity.animal.predators;

import entity.animal.Animal;
import entity.island.Island;

import java.util.concurrent.atomic.AtomicInteger;

public abstract class Predator extends Animal {
    public static AtomicInteger predatorCount = new AtomicInteger(0);

    public Predator(Island island, double maxWeight, int maxSpeed, double foodRequired, int initialEnergy){
        super(island, maxWeight, maxSpeed, foodRequired, initialEnergy);
        predatorCount.incrementAndGet();
    }

}
