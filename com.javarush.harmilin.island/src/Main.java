import entity.animal.Animal;
import entity.animal.herbivores.*;
import entity.animal.predators.*;
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

        ArrayList<Animal> animals = new ArrayList<>();
        animals.add(new Bear(100));
        animals.add(new Buffalo(100));
        animals.add(new Caterpillar(100));
        animals.add(new Deer(100));
        animals.add(new Duck(100));
        animals.add(new Goat(100));
        animals.add(new Horse(100));
        animals.add(new Mouse(100));
        animals.add(new Rabbit(100));
        animals.add(new Sheep(100));
        animals.add(new Bear(100));
        animals.add(new Boa(100));
        animals.add(new Eagle(100));
        animals.add(new Fox(100));
        animals.add(new Wolf(100));

        animals.forEach(x -> System.out.println(x.getSymbol() + " (" + x.getClass().getSimpleName() + ") " + " has " + x.getEnergy() + " energy."));
        System.out.println("Animals: " + Animal.animalCount);
        System.out.println("Predators: " + Predator.predatorCount);
        System.out.println("Herbivores: " + Herbivore.herbivoreCount);
    }
}