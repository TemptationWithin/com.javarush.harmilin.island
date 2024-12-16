package entity.animal.part;

import entity.animal.Animal;
import lombok.Getter;

public class Meat extends AnimalPart {
    @Getter
    private double foodAmount;
    @Getter
    private double meatAmount;
    private String icon;

    public Meat(Animal animal) {
        super(animal);
        this.foodAmount = animal.getWeight();
        this.meatAmount = foodAmount * 0.7; // 70% of meat
    }

    public String getIcon() {
        return "🥩";
    }
}
