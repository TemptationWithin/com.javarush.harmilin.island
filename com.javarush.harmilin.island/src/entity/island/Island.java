package entity.island;

import entity.animal.Animal;
import entity.cell.Cell;
import entity.plant.Plant;
import lombok.Data;
import lombok.Getter;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Data
public class Island {
    private final int rows;
    private final int cols;
    private final Cell[][] grid;
    private List<Animal> animals;
    private List<Plant> plants;
    @Getter
    private static final Map<String, Integer> animalLimits;
    @Getter
    private static final Map<String, Integer> plantLimits;
    private final ExecutorService executorService = Executors.newCachedThreadPool();

    static {
        animalLimits = new HashMap<>();
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

        plantLimits = new HashMap<>();
        plantLimits.put("Plant", 200);
    }

    public Island(int rows, int cols){
        this.rows = rows;
        this.cols = cols;
        this.grid = new Cell[rows][cols];
        this.animals = new CopyOnWriteArrayList<>();
        this.plants = new CopyOnWriteArrayList<>();
        initializeGrid();
    }

    private void initializeGrid(){
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = new Cell();
            }
        }
    }

    public void placeAnimal(Island island, Animal animal){
        Random random = new Random();
        animal.setX_Coordinate(random.nextInt(rows));
        animal.setY_Coordinate(random.nextInt(cols));
        island.animals.add(animal);
        animal.setCurrentCell(grid[animal.getX_Coordinate()][animal.getY_Coordinate()]);
        animal.getCurrentCell().addIcon(animal.getIcon());
    }

    public void placePlant(Island island, Plant plant){
        Random random = new Random();
        plant.setX_Coordinate(random.nextInt(rows));
        plant.setY_Coordinate(random.nextInt(cols));
        island.plants.add(plant);
        plant.setCurrentCell(grid[plant.getX_Coordinate()][plant.getY_Coordinate()]);
        plant.getCurrentCell().addIcon(plant.getIcon());
    }

    private int calculateMaxCellWidth(){
        int maxWidth = 0;
        for(Cell[] row: grid){
            for (Cell cell : row){
                int cellContentWidth = cell.getFormattedContent().length();
                if (cellContentWidth > maxWidth){
                    maxWidth = cellContentWidth;
                }
            }
        }
        return maxWidth + 2;
    }

    public void display(){
        int cellWidth = calculateMaxCellWidth();
        String horizontalBorder = "+".repeat(cellWidth * cols + cols +1);
        System.out.println(horizontalBorder);
        for(Cell[] row : grid){
            StringBuilder rowContent = new StringBuilder("|");
            for (Cell cell : row){
                String content = cell.getFormattedContent();
                rowContent.append(String.format(" %-" + (cellWidth - 2) + "s |", content));
            }
            System.out.println(rowContent);
            System.out.println(horizontalBorder);
        }
    }

    public void stopSimulation(){
        for (Animal animal : animals){
            animal.die();
        }
        executorService.shutdown();
    }

    public void moveAllAnimals(){
        for (Animal animal : animals){
            if (animal.isAlive()){
                animal.move(animal.getIsland().getRows(), animal.getIsland().getCols());
                grid[animal.getX_Coordinate()][animal.getY_Coordinate()].addIcon(animal.getIcon());
            }
        }
    }

    public void growAllPlants(){
        Random random = new Random();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j].hasPlants()){
                    Plant plant = getPlants().get(random.nextInt(plants.size()));
                    grid[i][j].addPlant(plant);
                    grid[i][j].addIcon(plant.getIcon());
                }
            }
        }
    }

    public void growAfterRain(){
        for (int i = 0; i < 100; i++) {
            this.placePlant(this, new Plant(this));
        }
    }

    public void cleanUp(){
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j].removeDeadIcons();
            }
        }
    }

    @Override
    public String toString(){
        return "Island{" +
                "rows=" + rows +
                ", cols=" + cols +
                ", numberOfAnimals=" + animals.size() +  // Просто количество животных
                '}';
    }
}
