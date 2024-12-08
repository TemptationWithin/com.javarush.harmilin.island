package entity.animals;
import lombok.Getter;

import java.util.Random;

@Getter
public abstract class Animal {
    public static int animalCount = 0;

    private String name;
    private int energy;

    public Animal(String name, int initialEnergy){
        this.name = name;
        this.energy = initialEnergy;
        animalCount++;
    }

    public abstract void eat(Object food);
    public abstract void reproduce();

    public void increaseEnergy(int amount){
        energy = energy + amount;
    }

    public void decreaseEnergy(int amount){
        energy = Math.max(0, energy - amount);
    }

    public void move(){
        String[] directions = {"Up", "Down", "Left", "Right"};
        String direction = directions[new Random().nextInt(directions.length)];
        decreaseEnergy(5);
    }

    public String getName() {
        return name;
    }

    public int getEnergy() {
        return energy;
    }

}
