package entity.cell;

import entity.animal.Animal;

import java.util.*;

public class Cell {
    private final Set<String> icons = new HashSet<>();
    private final List<Animal> animals = new ArrayList<>();
    private int plants = 50;
    private final Map<String, Integer> limits;

    public Cell() {
        this.limits = getLimits();
    }

    public void addAnimal(Animal animal){
        animals.add(animal);
        animal.setCurrentCell(this);
    }

    public boolean removeAnimal(Animal animal){
        return animals.remove(animal);
    }

    public void consumePlants(int amount){
        plants = Math.max(0, plants - amount);
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

    public Map<String, Integer> getLimits() {
        return limits;
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public int getPlants() {
        return plants;
    }
}
