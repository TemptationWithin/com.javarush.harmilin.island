package entity.animal;
import lombok.Getter;

import java.util.Random;

@Getter
public abstract class Animal {
    public static int animalCount = 0;

    private int maxSpeed;
    private double maxWeight;
    private double foodRequired;
    private int x_Coordinate, y_Coordinate;
    private int energy;
    private String icon;
    private final char sex;
    private String name;

    public Animal(double maxWeight, int maxSpeed, double foodRequired, int initialEnergy){
        this.maxWeight = maxWeight;
        this.maxSpeed = maxSpeed;
        this.foodRequired = foodRequired;
        this.energy = initialEnergy;
        this.sex = new Random().nextBoolean() ? 'F' : 'M';
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

    public String getIcon(){return icon;}

    public int getX_Coordinate() {
        return x_Coordinate;
    }

    public void setX_Coordinate(int x_Coordinate) {
        this.x_Coordinate = x_Coordinate;
    }

    public int getY_Coordinate() {
        return y_Coordinate;
    }

    public void setY_Coordinate(int y_Coordinate) {
        this.y_Coordinate = y_Coordinate;
    }
}
