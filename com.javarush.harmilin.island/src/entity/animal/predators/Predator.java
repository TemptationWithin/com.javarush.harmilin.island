package entity.animal.predators;

import entity.animal.Animal;
import entity.animal.herbivores.Herbivore;
import entity.cell.Cell;

import javax.lang.model.element.AnnotationMirror;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public abstract class Predator extends Animal {
    public static int predatorCount = 0;

    public Predator(double maxWeight, int maxSpeed, double foodRequired, int initialEnergy){
        super(maxWeight, maxSpeed, foodRequired, initialEnergy);
        predatorCount++;
    }

}
