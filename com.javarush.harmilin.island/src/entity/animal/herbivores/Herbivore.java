package entity.animal.herbivores;

import entity.animal.Animal;
import entity.island.Island;

import java.util.concurrent.atomic.AtomicInteger;

public abstract class Herbivore extends Animal {
    public static AtomicInteger herbivoreCount = new AtomicInteger(0);

    public Herbivore(Island island, double maxWeight, int maxSpeed, double foodRequired, int initialEnergy){
        super(island, maxWeight, maxSpeed, foodRequired, initialEnergy);
        herbivoreCount.incrementAndGet();
    }

}
