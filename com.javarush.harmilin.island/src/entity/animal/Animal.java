package entity.animal;

import entity.animal.herbivores.Herbivore;
import entity.cell.Cell;
import entity.island.Island;
import entity.plant.Plant;
import lombok.Data;

import java.util.List;
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
    private Island island;

    private final ConcurrentHashMap<Class<? extends Animal>, Integer> preyChances = new ConcurrentHashMap<>();

    public Animal(Island island, double maxWeight, int maxSpeed, double foodRequired, int initialEnergy) {
        this.island = island;
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
                    increaseEnergy(Math.min(100, getEnergy()+40));
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
                    increaseEnergy(Math.min(100, getEnergy()+10));
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
                    if (offspring != null) {
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
            case 1 -> move(island.getRows(), island.getCols());
            case 2 -> reproduce();
        }
    }

    public void increaseEnergy(int amount) {
        energy = energy + amount;
    }

    public void decreaseEnergy(int amount) {
        energy = Math.max(0, energy - amount);
    }

    public void move(int rows, int cols) {
        Random random = new Random();
        int moveDistance = random.nextInt(getMaxSpeed())+1;
        int direction = random.nextInt(4);
        switch (direction){
            case 0:
                if (x_Coordinate - moveDistance >= 0) {
                    x_Coordinate -= moveDistance;
                }
                break;
            case 1:
            if (x_Coordinate + moveDistance < rows){
                x_Coordinate += moveDistance;
            }
                break;
            case 2:
                if (y_Coordinate - moveDistance >= 0){
                    y_Coordinate -= moveDistance;
                }
                break;
            case 3:
                if (y_Coordinate + moveDistance < cols){
                    y_Coordinate += moveDistance;
                }
                break;
            default:{
                move(rows, cols);
            }
        }
        this.decreaseEnergy(getMaxSpeed()*2);
        System.out.println(getIcon() + " moved to cell: " + x_Coordinate + ", " + y_Coordinate);
    }

    public void setPreyChance(Class<? extends Animal> preyClass, int chance) {
        preyChances.put(preyClass, chance);
    }

}
