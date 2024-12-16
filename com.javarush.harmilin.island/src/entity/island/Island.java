package entity.island;

import entity.animal.Animal;
import entity.animal.herbivore.*;
import entity.animal.part.AnimalPart;
import entity.animal.predator.*;
import entity.island.weather.RainWeather;
import entity.island.weather.SnowWeather;
import entity.island.weather.SunnyWeather;
import entity.island.weather.Weather;
import entity.plant.Plant;
import lombok.Data;
import lombok.Getter;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

@Data
public class Island implements Runnable {
    private final int rows;
    private final int cols;
    private final Cell[][] grid;
    private List<Animal> animals;
    private List<Plant> plants;
    private List<AnimalPart> animalParts;
    private Weather currentWeather;
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
        this.animalParts = new CopyOnWriteArrayList<>();
        this.animals = new CopyOnWriteArrayList<>();
        this.plants = new CopyOnWriteArrayList<>();
        initializeGrid();
    }

    private void initializeGrid() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = new Cell(i, j);
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
        animal.getCurrentCell().updateIcons();
    }

    public void placePlant(Island island, Plant plant) {
        Random random = new Random();
        plant.setX_Coordinate(random.nextInt(rows));
        plant.setY_Coordinate(random.nextInt(cols));
        island.plants.add(plant);
        plant.setCurrentCell(grid[plant.getX_Coordinate()][plant.getY_Coordinate()]);
        plant.getCurrentCell().addPlant(plant);
        plant.getCurrentCell().updateIcons();
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
        System.out.println(amountToString());
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
        int i = 0;
        for (Animal animal : animals) {
            animal.die();
            if (i <= 4) {
                System.out.print("God killed: " + animal + "|");
                i++;
            } else {
                System.out.print("\n");
                i = 0;
            }
        }
        System.out.println("God killed all " + this.plants.size() + " plants.|");
        for (Plant plant : plants) {
            plant.die();
        }
        System.out.println("The Island is sterile again");
    }

    public void moveAllAnimals() {
        for (Animal animal : animals) {
            if (animal.isAlive()) {
                animal.move(animal.getIsland().getRows(), animal.getIsland().getCols());
                grid[animal.getX_Coordinate()][animal.getY_Coordinate()].updateIcons();
            }
        }
        cleanUp();
    }

    public void allPerformActions() {
        for (Animal animal : animals) {
            animal.performActions();
        }
    }

    public void growAllPlants() {
        System.out.println("--".repeat(5) + "🌱" + "🌱" + "Plants are growing..." + "🌱" + "🌱" + "--".repeat(5));
        Random random = new Random();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j].hasPlants()) {
                    Plant plant = getPlants().get(random.nextInt(grid[i][j].getPlants().size()));
                    grid[i][j].addPlant(plant);
                    Plant.plantCount.incrementAndGet();

                    switch (random.nextInt(100)) {
                        case 0: {
                            caterpillarsAppearance(grid[i][j]);
                            break;
                        }
                    }

                }
            }
        }
    }

    public void caterpillarsAppearance(Cell cell) {
        System.out.println("--".repeat(5) + "🐛" + "🐛" + "🐛" +
                "New caterpillars begin to crawl out of the plants" +
                "🐛" + "🐛" + "--".repeat(5));
        Random random = new Random();
        for (int i = 1; i < random.nextInt(4) + 1; i++) {
            Caterpillar caterpillar = new Caterpillar(this);
            caterpillar.setCurrentCell(cell);
            cell.getAnimals().add(caterpillar);
            this.getAnimals().add(caterpillar);
            cell.getAnimalIcons().add(caterpillar.getIcon());
            System.out.println("--".repeat(5) +
                    caterpillar + " was added to" + caterpillar.coordinatesToString() +
                    "--".repeat(5));
        }
    }

    public synchronized void cleanUp() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j].updateIcons();
            }
        }
    }

    public String amountToString() {
        return "Animals: " + Animal.animalCount + ": " +
                "Predators: " + Predator.predatorCount +
                ", Herbivores: " + Herbivore.herbivoreCount + ". " +
                "Plants: " + Plant.plantCount;
    }

    @Override
    public String toString() {
        return "Island size is: " + rows + " x " + cols +
                "\n" + amountToString();
    }

    public synchronized void statisticPerCell() {
        System.out.println(amountToString());
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
                case 0: {
                    this.placeAnimal(this, new Bear(this));
                    break;
                }
                case 1: {
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

    public Cell getCellByCoordinates(int x, int y) {
        if ((x < getRows() && y < getCols() && (x >= 0 && y >= 0))) {
            return grid[x][y];
        }
        System.out.println("There is no grid with those coordinates");
        return null;
    }

    public void changeWeather() {
        Random random = new Random();
        int weatherType = random.nextInt(11);
        switch (weatherType) {
            case 0, 1, 2, 3, 4, 5, 6, 7 -> currentWeather = new SunnyWeather();
            case 8, 9 -> currentWeather = new RainWeather();
            case 10 -> currentWeather = new SnowWeather();
        }
        System.out.println(currentWeather);
        applyWeatherEffects();
    }

    public void applyWeatherEffects() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                for (Animal animal : grid[i][j].getAnimals()) {
                    currentWeather.affectAnimal(animal);
                }
            }
        }
        currentWeather.affectPlants(this);
    }

    @Override
    public void run() {
        allPerformActions();
    }
}
