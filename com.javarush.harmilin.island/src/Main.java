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
        int day = 0;
        island.display();

        while (day < dayLimitation) {
            synchronized (System.out){
                System.out.println("+" + "---".repeat(2) + " +");
                System.out.println("|DAY #" + day + " |");
                System.out.println("+" + "___".repeat(2) + " +");
            }
            Timer.sleep(2000);
            executorHandler.getExecutorService().submit(island::cleanUp);
            executorHandler.getExecutorService().submit(island::allPerformActions);
            executorHandler.getScheduler().scheduleWithFixedDelay(island::growAllPlants, 0, 10, TimeUnit.SECONDS);
            //executorHandler.getScheduler().scheduleWithFixedDelay(island::moveAllAnimals, 0, 10, TimeUnit.SECONDS);
            Timer.sleep(100);
            day++;

            if (day == dayLimitation) {
                executorHandler.shutdownAllExecutors();
                island.display();
                dayLimitation = dayLimitation + Validator.optionsForContinue(console, island);
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