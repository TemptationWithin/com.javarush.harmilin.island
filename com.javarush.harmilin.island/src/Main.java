import entity.animal.Animal;
import entity.animal.herbivores.*;
import entity.animal.predators.*;
import entity.island.Island;
import entity.plant.Plant;
import handler.Validator;

import javax.swing.plaf.TableHeaderUI;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        System.out.println("Welcome to Island. Please, enter size of island (has to be bigger than 19x19.");
        int width = Validator.getValidatedInput(console, "Please, select width of island: ");
        int length = Validator.getValidatedInput(console, "Please, select length of island: ");
        System.out.println("Thank you.\nIsland size will be: " + width + " x " + length);

        Island island = new Island(width, length);
        island.placeAnimal(new Boar());
        island.placeAnimal(new Buffalo());
        island.placeAnimal(new Caterpillar());
        island.placeAnimal(new Deer());
        island.placeAnimal(new Duck());
        island.placeAnimal(new Goat());
        island.placeAnimal(new Horse());
        island.placeAnimal(new Mouse());
        island.placeAnimal(new Rabbit());
        island.placeAnimal(new Sheep());
        island.placeAnimal(new Bear());
        island.placeAnimal(new Boa());
        island.placeAnimal(new Eagle());
        island.placeAnimal(new Fox());
        island.placeAnimal(new Wolf());
        island.placePlant(new Plant());
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
    }
}