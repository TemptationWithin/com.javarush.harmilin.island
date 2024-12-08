package entity.cell;

import entity.animal.Animal;
import entity.animal.herbivores.Herbivore;
import entity.animal.predators.Predator;

import java.util.ArrayList;
import java.util.List;

public class Cell {
    private final int x, y;
    private final List<Animal> animalList = new ArrayList<>();

    public Cell(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void addAnimal(Animal animal){
        synchronized (animalList){
            animalList.add(animal);
        }
    }

    public String getSymbols(){
        synchronized (animalList){
            boolean hasHerbivores = animalList.stream().allMatch(animal -> animal instanceof Herbivore);
            boolean hasPredators = animalList.stream().allMatch(animal -> animal instanceof Predator);
            if (hasPredators){
            }
            return null;
        }
    }
}
