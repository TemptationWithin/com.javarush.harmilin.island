package entity.animal.herbivores;

import entity.animal.Animal;
import entity.cell.Cell;
import entity.plant.Plant;

import java.util.HashMap;
import java.util.Map;

public abstract class Herbivore extends Animal {
    public static int herbivoreCount = 0;

    public Herbivore(double maxWeight, int maxSpeed, double foodRequired, int initialEnergy){
        super(maxWeight, maxSpeed, foodRequired, initialEnergy);
        herbivoreCount++;
    }

}
