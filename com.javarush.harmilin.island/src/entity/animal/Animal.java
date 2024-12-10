package entity.animal;

import entity.animal.herbivores.CanEatAnotherHerbivore;
import entity.animal.herbivores.Herbivore;
import entity.animal.predators.Predator;
import entity.cell.Cell;
import lombok.Getter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Getter
public abstract class Animal{
    public static int animalCount = 0;

    private final int maxSpeed;
    private final double maxWeight;
    private final double foodRequired;
    private int x_Coordinate, y_Coordinate;
    private int energy;
    private String icon;
    private final char sex;
    private String name;
    private boolean isAlive = true;
    private boolean isHungry = true;
    private Cell currentCell;
    private final Map<Class<? extends Animal>, Integer> preyChances = new HashMap<>();

    public Animal(double maxWeight, int maxSpeed, double foodRequired, int initialEnergy) {
        this.maxWeight = maxWeight;
        this.maxSpeed = maxSpeed;
        this.foodRequired = foodRequired;
        this.energy = initialEnergy;
        this.sex = new Random().nextBoolean() ? 'F' : 'M';
        animalCount++;
    }

    protected void eat(){
        Cell cell = getCurrentCell();
        this.eatAnimals(cell);
        this.eatPlants(cell);
    }

    protected void eatAnimals(Cell cell) {
        List<Animal> potentialPrey = cell.getAnimals();
        Random random = new Random();
        for (Animal prey : potentialPrey){
            if (prey == this) continue;
            Integer chance = getPreyChances().get(prey.getClass());
            if (chance != null && random.nextInt(100) < chance){
                System.out.println(this.getIcon() + " successfully ate " + prey.getIcon());
                cell.removeAnimal(prey);
                isHungry = false;
                return;
            }
        }
        System.out.println(this.getIcon() + " found no prey to eat.");
    }

    private  void eatPlants(Cell cell){
        if ((this instanceof Herbivore) && isHungry()){
            int availablePlants = cell.getPlants();
            if (availablePlants > 0){
                int foodConsumed = (int) Math.min(getFoodRequired(), availablePlants);
                cell.consumePlants(foodConsumed);
                System.out.println(this.getIcon() + " ate " + foodConsumed + " plants.");
                isHungry = false;
            } else {
                System.out.println(this.getIcon() + " found no plants around.");
            }
        }
    }

    public abstract void reproduce();

    public boolean isAlive(){
        return isAlive;
    }

    public boolean isHungry(){
        return isHungry;
    }

    public void die(){
        isAlive = false;
    }

    protected void performActions(){
        Random random = new Random();
        int action = random.nextInt(3);
        switch (action){
            case 0 -> eat();
            case 1 -> move();
            case 2 -> reproduce();
        }
    }

    public void increaseEnergy(int amount) {
        energy = energy + amount;
    }

    public void decreaseEnergy(int amount) {
        energy = Math.max(0, energy - amount);
    }

    public void move() {
        String[] directions = {"Up", "Down", "Left", "Right"};
        String direction = directions[new Random().nextInt(directions.length)];
        decreaseEnergy(this.maxSpeed * 2);
    }

    public void setPreyChance(Class<? extends Animal> preyClass, int chance){
        preyChances.put(preyClass, chance);
    }

    public Map<Class<? extends Animal>, Integer> getPreyChances() {
        return preyChances;
    }

    public int getEnergy() {
        return energy;
    }

    public char getSex() {
        return sex;
    }

    public String getIcon() {
        return icon;
    }

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

    public Cell getCurrentCell() {
        return currentCell;
    }

    public void setCurrentCell(Cell currentCell) {
        this.currentCell = currentCell;
    }

    public double getFoodRequired() {
        return foodRequired;
    }
}
