package entity.cell;

import entity.animal.Animal;
import entity.island.Island;
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

    public synchronized void addAnimal(Animal animal) {
        long count = animals.stream().filter(a -> a.getClass() == animal.getClass()).count();
        int maxCount = getMaxAnimalsPerCell(animal.getClass());
        if (count <= maxCount) {
            this.animals.add(animal);
            animal.setCurrentCell(this);
        } else {
            System.out.println("Cell is full for " + animal.getClass().getSimpleName());
        }
    }

    public synchronized void addPlant(Plant plant) {
        long count = plants.stream().filter(a -> a.getClass() == plant.getClass()).count();
        int maxCount = getMaxPlantsPerCell(plant.getClass());
        if (count <= maxCount) {
            plants.add(plant);
            plant.setCurrentCell(this);
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

    private int getMaxPlantsPerCell(Class<? extends Plant> plantClass){
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
            Iterator<String> iterator = animalIcons.iterator();
            while (iterator.hasNext()) {
                String icon = iterator.next();
                boolean isAlive = animals.stream()
                        .anyMatch(animal -> animal.getIcon().equals(icon) && animal.isAlive()
                        && !animal.getIcon().equals("💀"));
               if (!isAlive)
                {
                    iterator.remove();
               }
            }
        }
    }

    public boolean isEmpty() {
        return animalIcons.isEmpty();
    }

    public boolean allPlantsAreDead(String icon) {
        return plants.stream()
                .filter(p -> p.getIcon().equals(icon))
                .allMatch(p -> p.getWeight() == 0);
    }
    public boolean hasPlants(){
        return !plants.isEmpty();
    }

    public String getFormattedContent() {
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

}
