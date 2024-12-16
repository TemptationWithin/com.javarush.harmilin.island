package entity.animal.part;

import entity.animal.Animal;
import lombok.Getter;

import java.util.concurrent.atomic.AtomicInteger;

@Getter
public class Meat extends AnimalPart {
    public static AtomicInteger animalMeatCount = new AtomicInteger(0);

    private double foodAmount;

    public Meat(Animal animal) {
        super(animal);
        this.foodAmount = animal.getWeight() * 0.7; // 70% of meat
        animalMeatCount.incrementAndGet();
    }

    public String getIcon() {
        return "🥩";
    }
}
