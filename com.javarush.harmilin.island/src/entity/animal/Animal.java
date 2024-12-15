package entity.animal;

import catalog.Names;
import entity.animal.herbivore.Herbivore;
import entity.animal.part.Bone;
import entity.animal.part.Meat;
import entity.animal.predator.Predator;
import entity.island.Cell;
import entity.island.Island;
import entity.island.weather.SnowWeather;
import entity.plant.Plant;
import lombok.Data;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Data
public abstract class Animal {
    public static AtomicInteger animalCount = new AtomicInteger(0);

    private final int maxSpeed;
    private int speed;
    private double weight;
    private double foodInStomach;
    private final double maxFoodRequiredLimit;
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

    public Animal(Island island, double maxWeight, int maxSpeed, double maxFoodRequiredLimit, int initialEnergy) {
        this.island = island;
        this.weight = maxWeight;
        this.maxSpeed = maxSpeed;
        this.maxFoodRequiredLimit = maxFoodRequiredLimit;
        this.foodInStomach = maxFoodRequiredLimit;
        this.energy = initialEnergy;
        this.sex = new Random().nextBoolean() ? 'F' : 'M';
        if (sex == 'F') {
            this.name = Names.getRandomFemaleName();
        } else this.name = Names.getRandomMaleName();
        animalCount.incrementAndGet();
    }

    protected abstract Animal createOffspring();

    protected synchronized void eat() {
        if (getEnergy() <= 70 && maxFoodRequiredLimit - foodInStomach > 0) {
            isHungry = true;
        }
        if (isHungry) { //add animalpieces
            this.eatAnimals(getCurrentCell());
            this.eatPlants(getCurrentCell());
        } else {
            this.decreaseEnergy(Math.min(0, getEnergy() - 2));
        }
    }

    protected synchronized void eatAnimals(Cell cell) {
        List<Animal> potentialPrey = cell.getAnimals();
        Random random = new Random();
        for (Animal prey : potentialPrey) {
            if (prey == this) continue;
            Integer chance = getPreyChances().get(prey.getClass());
            if (chance != null && random.nextInt(100) < chance) {
                System.out.println(this + " successfully ate " + prey +
                        " in cell: " + this.coordinatesToString());
                //decreasing required food for this animal by weight of prey
                double preyLoosingWeight = this.maxFoodRequiredLimit - this.foodInStomach;
                this.foodInStomach = Math.max(this.maxFoodRequiredLimit, this.foodInStomach + prey.getWeight());
                prey.setWeight(Math.min(0, prey.getWeight() - preyLoosingWeight));
                prey.die();
                increaseEnergy(Math.min(100, getEnergy() + 40)); // increasing energy after eating (+40 energy)
                if (this.getMaxFoodRequiredLimit() == this.getFoodInStomach()) {
                    isHungry = false;
                }
                return;
            }
        }
        System.out.println(this + " found no prey to eat in cell:" + this.coordinatesToString());
    }

    private synchronized void eatPlants(Cell cell) {
        if ((this instanceof Herbivore) && isHungry()) {
            List<Plant> plants = cell.getPlants();
            Random random = new Random();
            if (!plants.isEmpty()) {
                int preyPlantIndex = random.nextInt(plants.size());
                System.out.println(this + " ate " + plants.get(preyPlantIndex).toString() +
                        " in cell: " + this.coordinatesToString());
                //decreasing required food for this animal by weight of prey
                double preyLoosingWeight = this.maxFoodRequiredLimit - this.foodInStomach;
                this.foodInStomach = Math.max(this.maxFoodRequiredLimit, this.foodInStomach + plants.get(preyPlantIndex).getWeight());
                plants.get(preyPlantIndex).setWeight(Math.min(0, plants.get(preyPlantIndex).getWeight() - preyLoosingWeight));
                plants.get(preyPlantIndex).die();
                increaseEnergy(Math.min(100, getEnergy() + 10)); // increasing energy after eating (+10 energy)
                if (this.getMaxFoodRequiredLimit() == this.getFoodInStomach()) {
                    isHungry = false;
                }
            } else {
                System.out.println(this + " found no plants in cell:" + this.coordinatesToString());
            }
        }
    }

    public synchronized void reproduce() {
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
                        this.decreaseEnergy(Math.max(0, getEnergy() - 10));// making animals get tired after activities (-10 energy)
                        partner.decreaseEnergy(Math.max(0, getEnergy() - 10));
                        this.setFoodInStomach(Math.min(0, getFoodInStomach() * 0.9)); // making animals be hungry after activities (10%)
                        partner.setFoodInStomach(Math.min(0, getFoodInStomach() * 0.9));
                        System.out.println(this + " reproduced with " + partner + " in cell:" + this.coordinatesToString());
                    }
                    return;
                }
            }
            this.decreaseEnergy(Math.max(0, getEnergy() - 5));
            System.out.println(this + " found no partner to reproduce in cell:" + this.coordinatesToString());
        }
    }

    public boolean isAlive() {
        return isAlive;
    }

    public boolean isHungry() {
        return isHungry;
    }

    public void die() {
        isAlive = false;
        this.setIcon("💀");
        this.getCurrentCell().addIcon("💀");
        System.out.println(this.divisionIntoParts(this));
        island.getAnimals().remove(this);
        this.getCurrentCell().removeAnimal(this);
        animalCount.decrementAndGet();
        if (this instanceof Predator) {
            Predator.predatorCount.decrementAndGet();
        } else if (this instanceof Herbivore) {
            Herbivore.herbivoreCount.decrementAndGet();
        }
    }

    public void performActions() {
        if (this instanceof CanSleepAtWinter && getIsland().getCurrentWeather() instanceof SnowWeather) {
            return;
        }
        Random random = new Random();
        int action = random.nextInt(3);
        switch (action) {
            case 0 -> eat();
            case 1 -> {
                if (!(this instanceof NotMovable)) {
                    move(island.getRows(), island.getCols());
                } else {
                    performActions();
                }
            }
            case 2 -> reproduce();
        }
    }

    public void increaseEnergy(int amount) {
        energy = energy + amount;
    }

    public void decreaseEnergy(int amount) {
        energy = Math.max(0, energy - amount);
    }

    public synchronized void move(int rows, int cols) {
        if (!(this instanceof NotMovable && this.isAlive())) {
            Random random = new Random();
            int oldX = this.getX_Coordinate();
            int oldY = this.getY_Coordinate();
            int moveDistance = random.nextInt(getSpeed()) + 1;
            int direction = random.nextInt(4);
            int newX = oldX;
            int newY = oldY;
            String dir = "";
            switch (direction) {
                case 0:
                    if (x_Coordinate - moveDistance >= 0) {
                        newX = oldX - moveDistance;
                        dir = "up";
                    }
                    break;
                case 1:
                    if (x_Coordinate + moveDistance < rows) {
                        newX = oldX + moveDistance;
                        dir = "down";
                    }
                    break;
                case 2:
                    if (y_Coordinate - moveDistance >= 0) {
                        newY = oldY - moveDistance;
                        dir = "left";
                    }
                    break;
                case 3:
                    if (y_Coordinate + moveDistance < cols) {
                        newY = oldY + moveDistance;
                        dir = "right";
                    }
                    break;
            }
            if (newX == oldX && newY == oldY) {
                System.out.println(this + " cannot move out of bounds.");
                return;
            }
            this.decreaseEnergy(getSpeed() * 2);
            if (this.getEnergy() <= 0) {
                this.die();
                System.out.println(this + "died of exhaustion >>" + "💀");
            } else {
                this.getCurrentCell().removeAnimal(this);
                this.setCurrentCell(island.getCellByCoordinates(x_Coordinate, y_Coordinate));
                this.getCurrentCell().getAnimals().add(this);
                this.getCurrentCell().addIcon(this.getIcon());
                island.cleanUp();
                System.out.println(this + " with max speed: " + this.getMaxSpeed() +
                        " moved " + dir + ", from " + "[" + oldX + ", " + oldY + "]" +
                        " to " + coordinatesToString());
            }
        }
    }

    public void setPreyChance(Class<? extends Animal> preyClass, int chance) {
        preyChances.put(preyClass, chance);
    }

    @Override
    public String toString() {
        return this.getIcon() + this.getName() +
                "(" + this.getClass().getSimpleName() + "(" + this.getSex() + "))" +
                ". Energy: " + this.getEnergy() + " ";
    }

    public String coordinatesToString() {
        return " [" + this.getX_Coordinate() + ", " + this.getY_Coordinate() + "] ";
    }

    public void setCurrentCell(Cell currentCell) {
        this.currentCell = currentCell;
        this.x_Coordinate = currentCell.getX_Coordinate();
        this.y_Coordinate = currentCell.getY_Coordinate();
    }

    private String divisionIntoParts(Animal animal) {
        if (animal.getWeight() > 0){
            Bone bone = new Bone(animal);
            Meat meat = new Meat(animal);
            return animal + "split to the parts: " +
                    bone.getIcon() + "(" + bone.getFoodAmount() + "), " +
                    meat.getIcon() + "(" + meat.getFoodAmount() + ") " +
                    " in cell:" + animal.coordinatesToString();
        }
        return "Nothing left after " + animal;
    }
}
