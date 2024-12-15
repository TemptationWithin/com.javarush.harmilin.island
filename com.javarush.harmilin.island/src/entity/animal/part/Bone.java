package entity.animal.part;

import entity.animal.Animal;


public class Bone extends AnimalPart {

    private double foodAmount;
    private String icon;

    public Bone(Animal animal) {
        super(animal);
        this.foodAmount = animal.getWeight() * 0.3;  //30% bones
    }

    public String getIcon() {
        return "🦴";
    }
}
