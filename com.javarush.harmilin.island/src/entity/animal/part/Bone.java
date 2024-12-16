package entity.animal.part;

import entity.animal.Animal;
import lombok.Getter;

import java.util.concurrent.atomic.AtomicInteger;


public class Bone extends AnimalPart {
    public static AtomicInteger animalBonesCount = new AtomicInteger(0);
    @Getter
    private double foodAmount;

    public Bone(Animal animal) {
        super(animal);
        this.foodAmount = animal.getWeight() * 0.3;  //30% bones
        animalBonesCount.incrementAndGet();
    }

    public String getIcon() {
        return "🦴";
    }
}
