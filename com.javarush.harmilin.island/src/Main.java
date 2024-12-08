import entity.animal.Animal;
import entity.animal.herbivores.*;
import entity.animal.predators.*;
import entity.island.Island;
import entity.plant.Plant;
import handler.Validator;

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
        island.placeAnimal(new Boar(100));
        island.placeAnimal(new Buffalo(100));
        island.placeAnimal(new Caterpillar(100));
        island.placeAnimal(new Deer(100));
        island.placeAnimal(new Duck(100));
        island.placeAnimal(new Goat(100));
        island.placeAnimal(new Horse(100));
        island.placeAnimal(new Mouse(100));
        island.placeAnimal(new Rabbit(100));
        island.placeAnimal(new Sheep(100));
        island.placeAnimal(new Bear(100));
        island.placeAnimal(new Boa(100));
        island.placeAnimal(new Eagle(100));
        island.placeAnimal(new Fox(100));
        island.placeAnimal(new Wolf(100));
        island.placePlant(new Plant());
        island.display();


        System.out.println("Animals: " + Animal.animalCount);
        System.out.println("Predators: " + Predator.predatorCount);
        System.out.println("Herbivores: " + Herbivore.herbivoreCount);

    }
}