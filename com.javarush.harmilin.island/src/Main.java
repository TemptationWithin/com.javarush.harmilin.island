import entity.animal.Animal;
import entity.animal.herbivores.*;
import entity.animal.predators.*;
import entity.island.Day;
import entity.island.Island;
import entity.plant.Plant;
import handler.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        List<Callable<Void>> tasks = new ArrayList<>();

        Scanner console = new Scanner(System.in);

        System.out.println("Welcome to Island. Please, enter size of island (has to be bigger than 19x19.");
        int width = Validator.getValidatedInput(console, "Please, select width of island: ");
        int length = Validator.getValidatedInput(console, "Please, select length of island: ");
        System.out.println("Thank you.\nIsland size will be: " + width + " x " + length);

        Island island = new Island(width, length);
        //tests
        for (int i = 0; i < 5; i++) {
            island.placeAnimal(island, new Boar(island));
            island.placeAnimal(island, new Buffalo(island));
            island.placeAnimal(island, new Caterpillar(island));
            island.placeAnimal(island, new Deer(island));
            island.placeAnimal(island, new Duck(island));
            island.placeAnimal(island, new Goat(island));
            island.placeAnimal(island, new Horse(island));
            island.placeAnimal(island, new Mouse(island));
            island.placeAnimal(island, new Rabbit(island));
            island.placeAnimal(island, new Sheep(island));
            island.placeAnimal(island, new Bear(island));
            island.placeAnimal(island, new Boa(island));
            island.placeAnimal(island, new Eagle(island));
            island.placeAnimal(island, new Fox(island));
            island.placeAnimal(island, new Wolf(island));
        }
        for (int i = 0; i < 100; i++) {
            island.placePlant(island, new Plant(island));
        }

        String input = console.nextLine();
        int day = 1;
        while (!input.equalsIgnoreCase("STOP")){

            tasks.add(() -> {
                island.cleanUp();
                return null;
            });

            if (day % 5 == 0){
                tasks.add(() -> {
                        island.growAllPlants();
                        System.out.println("Plants growing...");
                    return null;
                });
            }
            System.out.println("");
            System.out.println("Welcome to day #" + day);
            tasks.add(() -> {
                System.out.println("Animals: " + Animal.animalCount);
                System.out.println("Predators: " + Predator.predatorCount);
                System.out.println("Herbivores: " + Herbivore.herbivoreCount);
                System.out.println("Plants: " + Plant.plantCount);
                island.display();
                return null;
            });

            try {
                executorService.invokeAll(tasks);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (input.equalsIgnoreCase("stat")){
                island.statisticPerCell();
            }
            day++;
            input = console.nextLine();
        }

        System.out.println("Animals: " + Animal.animalCount);
        System.out.println("Predators: " + Predator.predatorCount);
        System.out.println("Herbivores: " + Herbivore.herbivoreCount);

        try{
            Thread.sleep(100);
        } catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }

        island.stopSimulation();
        System.out.println("Simulation stopped.");
;
    }
}