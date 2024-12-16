package entity.animal.part;

import entity.animal.Animal;
import lombok.Getter;


public class Bone extends AnimalPart {
    @Getter
    private double foodAmount;
    @Getter
    private double boneAmount;
    private String icon;

    public Bone(Animal animal) {
        super(animal);
        this.foodAmount = animal.getWeight();
        this.boneAmount = foodAmount * 0.3;  //30% bones
    }

    public String getIcon() {
        return "🦴";
    }
}
