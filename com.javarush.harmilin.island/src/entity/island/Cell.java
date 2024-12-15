package entity.island;

import entity.animal.Animal;
import entity.plant.Plant;
import lombok.Data;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

@Data
public class Cell {
    private final Set<String> animalIcons = new HashSet<>();
    private final Set<String> plantIcons = new HashSet<>();
    private final List<Animal> animals = new CopyOnWriteArrayList<>();
    private final List<Plant> plants = new CopyOnWriteArrayList<>();
    private static final Map<String, Integer> limits = Island.getAnimalLimits();
    private static final Map<String, Integer> plantLimits = Island.getPlantLimits();
    private int x_Coordinate, y_Coordinate;

    public Cell(int x_Coordinate, int y_Coordinate) {
        this.x_Coordinate = x_Coordinate;
        this.y_Coordinate = y_Coordinate;
    }

    public synchronized void addAnimal(Animal animal) {
        long count = animals.parallelStream().filter(a -> a.getClass() == animal.getClass()).count();
        int maxCount = getMaxAnimalsPerCell(animal.getClass());
        if (count <= maxCount) {
            this.animals.add(animal);
            animal.setCurrentCell(this);
            animal.getIsland().getAnimals().add(animal);
        } else {
            System.out.println("Cell is full for " + animal.getClass().getSimpleName());
        }
    }

    public synchronized void addPlant(Plant plant) {
        long count = plants.parallelStream().filter(a -> a.getClass() == plant.getClass()).count();
        int maxCount = getMaxPlantsPerCell(plant.getClass());
        if (count <= maxCount) {
            this.plants.add(plant);
            plant.setCurrentCell(this);
            plant.getCurrentCell().addIcon(plant.getIcon());
            plant.getIsland().getPlants().add(plant);
        } else {
            System.out.println("Cell is full for " + plant.getClass().getSimpleName());
        }
    }

    public synchronized boolean removeAnimal(Animal animal) {
        return animals.remove(animal);
    }

    private int getMaxAnimalsPerCell(Class<? extends Animal> animalClass) {
        return limits.get(animalClass.getSimpleName());
    }

    private int getMaxPlantsPerCell(Class<? extends Plant> plantClass) {
        return plantLimits.get(plantClass.getSimpleName());
    }

    public void addIcon(String icon) {
        synchronized (animalIcons) {
            if (animalIcons.size() < 3) {
                animalIcons.add(icon);
            }
        }
        synchronized (plantIcons) {
            if (plantIcons.size() < 2) {
                plantIcons.add(icon);
            }
        }
    }

    public void removeDeadIcons() {
        synchronized (animalIcons) {
            Iterator<String> animalIterator = animalIcons.iterator();
            while (animalIterator.hasNext()) {
                String icon = animalIterator.next();
                boolean isAlive = animals.parallelStream()
                        .anyMatch(animal -> animal.getIcon().equals(icon) && animal.isAlive()
                                && !animal.getIcon().equals("💀"));
                if (!isAlive) {
                    animalIterator.remove();
                }
            }
        }
        synchronized (plantIcons) {
            Iterator<String> plantIterator = plantIcons.iterator();
            while (plantIterator.hasNext()) {
                String icon = plantIterator.next();
                boolean isPlantAlive = plants.parallelStream().anyMatch(s -> s.getIcon().equals(icon) && s.isAlive());
                if (!isPlantAlive) {
                    plantIterator.remove();
                }
            }
        }
    }

    public boolean isEmpty() {
        return animalIcons.isEmpty() && plantIcons.isEmpty();
    }

    public boolean hasPlants() {
        return !plants.isEmpty();
    }

    public synchronized String getFormattedContent() {
        if (isEmpty()) {
            return "";
        }
        Set<String> allIcons = new HashSet<>();
        allIcons.addAll(animalIcons);
        allIcons.addAll(plantIcons);
        return String.join(" ", allIcons);
    }


    @Override
    public String toString() {
        return getFormattedContent();
    }

    public synchronized void cellStatistic() {
        if (!this.cellIsEmpty()) {
            System.out.println(" Animals: " + this.getAnimals().size()
                    + ". Plants: " + this.getPlants().size());
            if (!animals.isEmpty()) {
                animals.parallelStream().forEach(s -> System.out.print(s.toString() + ", "));
            }
            System.out.print("\n");
            if (!plants.isEmpty()) {
                plants.parallelStream().forEach(s -> System.out.print(s.toString() + ", "));
            }
            System.out.println("\n" + "---".repeat(100));
        } else {
            System.out.print(" is empty.");
            System.out.print("\n" + "---".repeat(100) +"\n");
        }
    }


    private boolean cellIsEmpty() {
        return animals.isEmpty() && plants.isEmpty();
    }
}
