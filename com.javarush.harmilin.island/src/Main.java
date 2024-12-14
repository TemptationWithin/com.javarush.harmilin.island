import entity.island.Island;
import handler.ExecutorHandler;
import handler.Timer;
import handler.Validator;

import java.util.Scanner;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        ExecutorHandler executorHandler = new ExecutorHandler();

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
        //Timer.funnyPreparing();
        Timer.sleep(500);
        int day = 1;

        while (day < dayLimitation) {
            synchronized (System.out){
                System.out.println("+" + "---".repeat(2) + " +");
                System.out.println("|DAY #" + day + " |");
                System.out.println("+" + "___".repeat(2) + " +");
            }

            executorHandler.getExecutorService().submit(island::cleanUp);
            executorHandler.getScheduler().scheduleWithFixedDelay(island::growAllPlants, 0, 10, TimeUnit.SECONDS);
            executorHandler.getScheduler().scheduleWithFixedDelay(island::moveAllAnimals, 0, 60, TimeUnit.SECONDS);
            executorHandler.getScheduler().scheduleWithFixedDelay(island::display, 0, 4000, TimeUnit.MILLISECONDS);

            Timer.sleep(5000);

            day++;

            if (day == dayLimitation) {
                executorHandler.shutdownAllExecutors();
                System.out.println("\n==================== PAUSED ====================");
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
                executorHandler.restartExecutors();
            }
        }

        System.out.println("Last day #" + dayLimitation + " ended.");
        island.statisticPerCell();
        island.display();
        island.stopSimulation();
        executorHandler.shutdownAllExecutors();
        console.close();
        System.out.println("Simulation stopped.");

        Timer.sleep(1000);
    }
}