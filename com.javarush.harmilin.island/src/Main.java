import entity.island.Island;
import handler.Timer;
import handler.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

        System.out.println("Welcome to Island. Lets customize your island.\nPlease, enter size of island (AxB):");
        int width = Validator.getValidatedSizeInput(console, "Please, select width of island: ");
        int length = Validator.getValidatedSizeInput(console, "Please, select length of island: ");
        int dayLimitation = Validator.getValidatedIntLimitInput(console, "Please, let us know how many days you would like to observe: ");
        int startAnimals = Validator.getValidatedIntLimitInput(console, "Please, let us know how many animals you want to place?\n" +
                "Animal types will be chosen randomly but we will apply correct proportion of predators and herbivores: ");
        int startPlants = Validator.getValidatedIntLimitInput(console, "Please, let us know how many plants you want to place?: ");
        Island island = new Island(width, length);
        island.randomBegin(startAnimals, startPlants);
        System.out.println("Thanks. Your settings are: ");
        System.out.println(island);
        Timer.sleep(1000);
        System.out.println("Welcome to hungry games. Lets start...");
        Timer.funnyPreparing();
        int day = 1;
        while (day < dayLimitation) {
            Timer.sleep(4000);
            synchronized (System.out){
                System.out.println("+" + "---".repeat(2) + " +");
                System.out.println("|DAY #" + day + " |");
                System.out.println("+" + "___".repeat(2) + " +");
            }
            executorService.submit(island::cleanUp);

            scheduler.scheduleWithFixedDelay(island::growAllPlants, 0, 10, TimeUnit.SECONDS);
            scheduler.scheduleWithFixedDelay(island::display, 0, 4000, TimeUnit.MILLISECONDS);

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }

            day++;

            if (day == dayLimitation) {
                executorService.shutdown();
                scheduler.shutdown();
                int firstOption = Validator.getValidatedIntLimitInput(console, "Would you like to continue? \n1. - Yes." +
                        "\nAny other number will stop simulation");
                if (firstOption == 1) {
                    island.statisticPerCell();
                    int secondOptionInput = Validator.getValidatedIntLimitInput(console, "Do you want to add more Animals and plants? \nIf Yes - press 1.");
                    if (secondOptionInput == 1) {
                        System.out.println("You have to add at least 1 animal and 1 plant!");
                        int amountOfNewAnimals = Validator.getValidatedIntLimitInput(console, "How many animals would you like to add to your island?");
                        int amountOfNewPlants = Validator.getValidatedIntLimitInput(console, "And many plants?");
                        island.randomBegin(amountOfNewAnimals, amountOfNewPlants);
                    }
                    int additionalDays = Validator.getValidatedIntLimitInput(console, "How many days would you like to add?");
                    dayLimitation = dayLimitation + additionalDays;
                }
            }
        }

        System.out.println("Last day #" + dayLimitation + " ended.");
        island.statisticPerCell();
        island.display();
        island.stopSimulation();
        executorService.shutdown();
        scheduler.shutdown();
        console.close();
        System.out.println("Simulation stopped.");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}