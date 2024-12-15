package entity.animal.part;

import entity.animal.Animal;

public class Meat extends AnimalPart {

    private double foodAmount;
    private String icon;

    public Meat(Animal animal) {
        super(animal);
        this.foodAmount = animal.getWeight() * 0.7; //70% of meat
    }

    public String getIcon() {
        return "🥩";
    }
}
