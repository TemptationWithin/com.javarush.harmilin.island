package entity.animal;

import entity.animal.herbivores.Herbivore;
import entity.cell.Cell;
import entity.plant.Plant;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@Data
public abstract class Animal {
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

    private final ConcurrentHashMap<Class<? extends Animal>, Integer> preyChances = new ConcurrentHashMap<>();

    public Animal(double maxWeight, int maxSpeed, double foodRequired, int initialEnergy) {
        this.maxWeight = maxWeight;
        this.maxSpeed = maxSpeed;
        this.foodRequired = foodRequired;
        this.energy = initialEnergy;
        this.sex = new Random().nextBoolean() ? 'F' : 'M';
        animalCount++;
    }

    protected void eat() {
        Cell cell = getCurrentCell();
        this.eatAnimals(cell);
        this.eatPlants(cell);
    }

    protected void eatAnimals(Cell cell) {
        List<Animal> potentialPrey = cell.getAnimals();
        Random random = new Random();
        synchronized (cell) {
            for (Animal prey : potentialPrey) {
                if (prey == this) continue;
                Integer chance = getPreyChances().get(prey.getClass());
                if (chance != null && random.nextInt(100) < chance) {
                    System.out.println(this.getIcon() + " successfully ate " + prey.getIcon());
                    cell.removeAnimal(prey);
                    isHungry = false;
                    return;
                }
            }
        }
        System.out.println(this.getIcon() + " found no prey to eat.");
    }

    private void eatPlants(Cell cell) {
        synchronized (cell) {
            if ((this instanceof Herbivore) && isHungry()) {
                List<Plant> plants = cell.getPlants();
                if (!plants.isEmpty()) {
                    System.out.println(this.getIcon() + " ate " + plants.get(0).getIcon());
                    isHungry = false;
                } else {
                    System.out.println(this.getIcon() + " found no plants around.");
                }
            }
        }
    }

    public void reproduce() {
        Cell cell = getCurrentCell();
        synchronized (cell) {
            List<Animal> animalsInCell = cell.getAnimals();
            for (Animal partner : animalsInCell) {
                if (partner != this
                        && partner.getClass() == this.getClass()
                        && partner.getSex() != this.getSex()
                        && partner.isAlive()
                        && this.getEnergy() > 10
                        && partner.getEnergy() > 10) {
                    Animal offspring = createOffspring();
                    if (offspring != null){
                        cell.addAnimal(createOffspring());
                        this.decreaseEnergy(10);
                        partner.decreaseEnergy(10);
                        System.out.println(this.getIcon() + "(" + this.sex + ")" + " reproduced with " + partner.getIcon() + "(" + partner.sex + ")");
                    }
                    return;
                }
            }
            System.out.println(this.getIcon() + "(" + this.sex + ")" + " found no partner to reproduce.");
        }
    }

    protected abstract Animal createOffspring();

    public boolean isAlive() {
        return isAlive;
    }

    public boolean isHungry() {
        return isHungry;
    }

    public void die() {
        isAlive = false;
    }

    protected void performActions() {
        Random random = new Random();
        int action = random.nextInt(3);
        switch (action) {
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

    public void setPreyChance(Class<? extends Animal> preyClass, int chance) {
        preyChances.put(preyClass, chance);
    }

}
