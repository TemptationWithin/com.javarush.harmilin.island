import entity.animal.Animal;
import entity.animal.herbivore.*;
import entity.animal.predator.*;
import entity.island.Island;
import entity.plant.Plant;
import handler.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        List<Runnable> tasks = new ArrayList<>();


        System.out.println("Welcome to Island. Please, enter size of island (has to be bigger than 19x19.");
        int width = Validator.getValidatedInput(console, "Please, select width of island: ");
        int length = Validator.getValidatedInput(console, "Please, select length of island: ");
        System.out.println("Thank you.\nIsland size will be: " + width + " x " + length);

        Island island = new Island(width, length);
        island.randomBegin(100,50);

        String input = console.nextLine();
        int day = 1;
        //while (!input.equalsIgnoreCase("STOP")){
            tasks.add(island::cleanUp);
            scheduler.scheduleWithFixedDelay(island::growAllPlants, 0, 60, TimeUnit.SECONDS);
            scheduler.scheduleWithFixedDelay(island::display, 0, 15, TimeUnit.SECONDS);

            System.out.println("");
            System.out.println("Welcome to day #" + day);

            List<Future<?>> futures = new ArrayList<>();

            for (Runnable task : tasks){
                futures.add((executorService.submit(task)));
            }
            for (Future<?> future : futures){
                try {
                    future.get();
                } catch (InterruptedException | ExecutionException e) {
                    throw new RuntimeException(e);
                }
            }
            //executorService.submit(island::display);

            if (input.equalsIgnoreCase("stat")){
                island.statisticPerCell();
            }
            day++;
            input = console.nextLine();
        //}

        System.out.println("Animals: " + Animal.animalCount);
        System.out.println("Predators: " + Predator.predatorCount);
        System.out.println("Herbivores: " + Herbivore.herbivoreCount);

        try{
            Thread.sleep(100);
        } catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }

        island.stopSimulation();
        executorService.shutdown();
        scheduler.shutdown();
        console.close();
        System.out.println("Simulation stopped.");
;
    }
}