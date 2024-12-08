package entity.animal;
import lombok.Getter;

import java.util.Random;

@Getter
public abstract class Animal {
    public static int animalCount = 0;

    private int energy;
    private String symbol;
    private char sex;
    private String name;

    public Animal(int initialEnergy){
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

    public int getEnergy() {
        return energy;
    }

    public char getSex(){return sex;}

    public String getSymbol(){return symbol;}

}
