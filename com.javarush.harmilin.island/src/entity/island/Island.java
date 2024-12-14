package entity.island;

import entity.animal.Animal;
import entity.animal.herbivore.*;
import entity.animal.predator.*;
import entity.cell.Cell;
import entity.plant.Plant;
import lombok.Data;
import lombok.Getter;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Data
public class Island implements Runnable {
    private final int rows;
    private final int cols;
    private final Cell[][] grid;
    private List<Animal> animals;
    private List<Plant> plants;
    @Getter
    private static final ConcurrentHashMap<String, Integer> animalLimits;
    @Getter
    private static final ConcurrentHashMap<String, Integer> plantLimits;

    static {
        animalLimits = new ConcurrentHashMap<>();
        animalLimits.put("Boar", 50);
        animalLimits.put("Buffalo", 10);
        animalLimits.put("Caterpillar", 1000);
        animalLimits.put("Deer", 20);
        animalLimits.put("Duck", 100);
        animalLimits.put("Goat", 150);
        animalLimits.put("Horse", 20);
        animalLimits.put("Mouse", 500);
        animalLimits.put("Rabbit", 150);
        animalLimits.put("Sheep", 140);
        animalLimits.put("Bear", 5);
        animalLimits.put("Boa", 30);
        animalLimits.put("Eagle", 20);
        animalLimits.put("Fox", 30);
        animalLimits.put("Wolf", 30);

        plantLimits = new ConcurrentHashMap<>();
        plantLimits.put("Plant", 200);
    }

    public Island(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.grid = new Cell[rows][cols];
        this.animals = new CopyOnWriteArrayList<>();
        this.plants = new CopyOnWriteArrayList<>();
        initializeGrid();
    }

    private void initializeGrid() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = new Cell();
            }
        }
    }

    public void placeAnimal(Island island, Animal animal) {
        Random random = new Random();
        animal.setX_Coordinate(random.nextInt(rows));
        animal.setY_Coordinate(random.nextInt(cols));
        island.animals.add(animal);
        animal.setCurrentCell(grid[animal.getX_Coordinate()][animal.getY_Coordinate()]);
        animal.getCurrentCell().addAnimal(animal);
        animal.getCurrentCell().addIcon(animal.getIcon());
    }

    public void placePlant(Island island, Plant plant) {
        Random random = new Random();
        plant.setX_Coordinate(random.nextInt(rows));
        plant.setY_Coordinate(random.nextInt(cols));
        island.plants.add(plant);
        plant.setCurrentCell(grid[plant.getX_Coordinate()][plant.getY_Coordinate()]);
        plant.getCurrentCell().addPlant(plant);
        plant.getCurrentCell().addIcon(plant.getIcon());
    }

    private int calculateMaxCellWidth() {
        int maxWidth = 0;
        for (Cell[] row : grid) {
            for (Cell cell : row) {
                int cellContentWidth = cell.getFormattedContent().length();
                if (cellContentWidth > maxWidth) {
                    maxWidth = cellContentWidth;
                }
            }
        }
        return maxWidth + 2;
    }

    public synchronized void display() {
        System.out.println("Animals: " + Animal.animalCount);
        System.out.println("Predators: " + Predator.predatorCount);
        System.out.println("Herbivores: " + Herbivore.herbivoreCount);
        System.out.println("Plants: " + Plant.plantCount);
        int cellWidth = calculateMaxCellWidth();
        String horizontalBorder = "+".repeat(cellWidth * cols + cols + 1);
        System.out.println(horizontalBorder);
        for (Cell[] row : grid) {
            StringBuilder rowContent = new StringBuilder("|");
            for (Cell cell : row) {
                String content = cell.getFormattedContent();
                rowContent.append(String.format(" %-" + (cellWidth - 2) + "s |", content));
            }
            System.out.println(rowContent);
            System.out.println(horizontalBorder);
        }
    }

    public void stopSimulation() {
        for (Animal animal : animals) {
            animal.die();
        }
    }

    public void moveAllAnimals() {
        for (Animal animal : animals) {
            if (animal.isAlive()) {
                animal.move(animal.getIsland().getRows(), animal.getIsland().getCols());
                grid[animal.getX_Coordinate()][animal.getY_Coordinate()].addIcon(animal.getIcon());
            }
        }
    }

    public void allPerformActions() {
        for (Animal animal : animals) {
            animal.performActions();
        }
    }

    public void growAllPlants() {
        System.out.println("Plants growing...");
        Random random = new Random();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j].hasPlants()) {
                    Plant plant = getPlants().get(random.nextInt(grid[i][j].getPlants().size()));
                    grid[i][j].addPlant(plant);
                    this.plants.add(plant);
                    grid[i][j].addIcon(plant.getIcon());
                    Plant.plantCount.incrementAndGet();
                }
            }
        }
    }

    public void growAfterRain() {
        Random random = new Random();
        int count = random.nextInt(1000);
        for (int i = 0; i < count; i++) {
            Plant plant = new Plant(this);
            this.placePlant(this, plant);
            plant.getCurrentCell().addPlant(plant);
            plant.getCurrentCell().addIcon(plant.getIcon());
            Plant.plantCount.incrementAndGet();
        }
        System.out.println("Rain stops." + count + " plants appears.");
    }

    public void cleanUp() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j].removeDeadIcons();
            }
        }
    }

    @Override
    public String toString() {
        return "Island{" +
                "rows=" + rows +
                ", cols=" + cols +
                ", numberOfAnimals=" + animals.size() +  // Просто количество животных
                '}';
    }

    public synchronized void statisticPerCell() {
        System.out.println("Total animals: " + Animal.animalCount + ". Predators: " + Predator.predatorCount + ", herbivores: " + Herbivore.herbivoreCount);
        System.out.println("Total plants: " + Plant.plantCount);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print("Statistic in cell: " + "[" + i + "]" + "," + "[" + j + "]");
                grid[i][j].cellStatistic();
            }
        }
    }

    public void randomBegin(int animals, int plants) {
        Random random = new Random();
        for (int j = 0; j < animals; j++) {
            int i = random.nextInt(animalLimits.size());
            switch (i) {
                case 0:{
                    this.placeAnimal(this, new Bear(this));
                    break;
                }
                case 1:{
                    this.placeAnimal(this, new Boa(this));
                    break;
                }
                case 2: {
                    this.placeAnimal(this, new Eagle(this));
                    break;
                }
                case 3: {
                    this.placeAnimal(this, new Fox(this));
                    break;
                }
                case 4: {
                    this.placeAnimal(this, new Wolf(this));
                    break;
                }
                case 5: {
                    this.placeAnimal(this, new Boar(this));
                    break;
                }
                case 6: {
                    this.placeAnimal(this, new Buffalo(this));
                    break;
                }
                case 7: {
                    this.placeAnimal(this, new Caterpillar(this));
                    break;
                }
                case 8: {
                    this.placeAnimal(this, new Deer(this));
                    break;
                }
                case 9: {
                    this.placeAnimal(this, new Duck(this));
                    break;
                }
                case 10: {
                    this.placeAnimal(this, new Goat(this));
                    break;
                }
                case 11: {
                    this.placeAnimal(this, new Horse(this));
                    break;
                }
                case 12: {
                    this.placeAnimal(this, new Mouse(this));
                    break;
                }
                case 13: {
                    this.placeAnimal(this, new Rabbit(this));
                    break;
                }
                case 14: {
                    this.placeAnimal(this, new Sheep(this));
                    break;
                }
            }
        }
        for (int j = 0; j < plants; j++) {
            this.placePlant(this, new Plant(this));
        }
    }

    @Override
    public void run() {
        allPerformActions();
    }
}
