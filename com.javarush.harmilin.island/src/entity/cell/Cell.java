package entity.cell;

import entity.animal.Animal;
import entity.island.Island;
import entity.plant.Plant;
import lombok.Data;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

@Data
public class Cell {
    private final Set<String> icons = new HashSet<>();
    private final List<Animal> animals = new CopyOnWriteArrayList<>();
    private final List<Plant> plants = new CopyOnWriteArrayList<>();
    private final Map<String, Integer> limits;

    public Cell() {
        this.limits = Island.getAnimalLimits();
    }

    public synchronized void addAnimal(Animal animal){
        long count = animals.stream().filter(a -> a.getClass() == animal.getClass()).count();
        int maxCount = getMaxAnimalsPerCell(animal.getClass());
        if (count < maxCount){
            animals.add(animal);
            animal.setCurrentCell(this);
        } else {
            System.out.println("Cell is full for " + animal.getClass().getSimpleName());
        }
    }

    public boolean removeAnimal(Animal animal){
        return animals.remove(animal);
    }

    private int getMaxAnimalsPerCell(Class<? extends Animal> animalClass){
        return limits.get(animalClass.getSimpleName());
    }

    public void addIcon(String icon){
        synchronized (icons){
            icons.add(icon);
        }
    }

    public boolean isEmpty(){
        return icons.isEmpty();
    }

    public String getFormattedContent(){
        if (isEmpty()){
            return "";
        }
        return String.join(" ", icons);
    }
}
