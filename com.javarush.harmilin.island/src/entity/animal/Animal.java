package entity.animal;

import catalog.Names;
import entity.animal.herbivore.Herbivore;
import entity.animal.predator.Predator;
import entity.island.Cell;
import entity.island.Island;
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
        if (sex == 'F') {
            this.name = Names.getRandomFemaleName();
        } else this.name = Names.getRandomMaleName();
        animalCount.incrementAndGet();
    }

    protected synchronized void eat() {
        this.eatAnimals(getCurrentCell());
        this.eatPlants(getCurrentCell());
    }

    protected synchronized void eatAnimals(Cell cell) {
        List<Animal> potentialPrey = cell.getAnimals();
        Random random = new Random();
        if (getEnergy() <= 70) {
            isHungry = true;
        }
            for (Animal prey : potentialPrey) {
                if (prey == this) continue;
                Integer chance = getPreyChances().get(prey.getClass());
                if (chance != null && random.nextInt(100) < chance) {
                    System.out.println(this + " successfully ate " + prey +
                            " at cell: " + "[" + this.getX_Coordinate() + ", " + this.getY_Coordinate() + "]");
                    prey.die();
                    increaseEnergy(Math.min(100, getEnergy() + 40));
                    isHungry = false;
                    return;
                }
            }
        System.out.println(this + " found no prey to eat.");
    }

    private synchronized void eatPlants(Cell cell) {
            if (getEnergy() <= 70) {
                isHungry = true;
            }
            if ((this instanceof Herbivore) && isHungry()) {
                List<Plant> plants = cell.getPlants();
                Random random = new Random();
                if (!plants.isEmpty()) {
                    int preyPlantIndex = random.nextInt(plants.size());
                    System.out.println(this + " ate " + plants.get(preyPlantIndex).toString() +
                            " at cell: " + "[" + this.getX_Coordinate() + ", " + this.getY_Coordinate() + "]");
                    plants.get(preyPlantIndex).die();
                    increaseEnergy(Math.min(100, getEnergy() + 10));
                    isHungry = false;
                } else {
                    System.out.println(this + " found no plants around.");
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
                        this.decreaseEnergy(10);
                        partner.decreaseEnergy(10);
                        System.out.println(this + " reproduced with " + partner);
                    }
                    return;
                }
            }
            System.out.println(this + " found no partner to reproduce.");
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
        this.setIcon("💀");
        this.getCurrentCell().addIcon("💀");
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

    public synchronized void move(int rows, int cols) {
        if (!(this instanceof NotMovable && this.isAlive())) {
            Random random = new Random();
            int oldX = this.getX_Coordinate();
            int oldY = this.getY_Coordinate();
            int moveDistance = random.nextInt(getMaxSpeed()) + 1;
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
            this.decreaseEnergy(getMaxSpeed() * 2);
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
                        " to " + "[" + this.getX_Coordinate() + ", " + this.getY_Coordinate() + "]");
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
}
