import entity.animal.Animal;
import entity.animal.herbivores.*;
import entity.animal.predators.*;
import entity.cell.Cell;
import entity.island.Island;
import entity.plant.Plant;
import handler.Validator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        System.out.println("Welcome to Island. Please, enter size of island (has to be bigger than 19x19.");
        int width = Validator.getValidatedInput(console, "Please, select width of island: ");
        int length = Validator.getValidatedInput(console, "Please, select length of island: ");
        System.out.println("Thank you.\nIsland size will be: " + width + " x " + length);

        Island island = new Island(width, length);
        //tests
        island.placeAnimal(new Boar(island));
        island.placeAnimal(new Buffalo(island));
        island.placeAnimal(new Caterpillar(island));
        island.placeAnimal(new Deer(island));
        island.placeAnimal(new Duck(island));
        island.placeAnimal(new Goat(island));
        island.placeAnimal(new Horse(island));
        island.placeAnimal(new Mouse(island));
        island.placeAnimal(new Rabbit(island));
        island.placeAnimal(new Sheep(island));
        island.placeAnimal(new Bear(island));
        island.placeAnimal(new Boa(island));
        island.placeAnimal(new Eagle(island));
        island.placeAnimal(new Fox(island));
        island.placeAnimal(new Wolf(island));
        island.placePlant(new Plant(island));

        island.display();

        System.out.println("Animals: " + Animal.animalCount);
        System.out.println("Predators: " + Predator.predatorCount);
        System.out.println("Herbivores: " + Herbivore.herbivoreCount);

        try{
            Thread.sleep(10000);
        } catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
        island.stopSimulation();
        System.out.println("Simulation stopped.");
;
    }
}