package handler;

import entity.island.Island;

import java.util.Scanner;

public class Validator {

    public static int getValidatedSizeInput(Scanner scanner, String message){
        int size;
        while (true){
            System.out.println(message);
            if (scanner.hasNextInt()){
                size = scanner.nextInt();
                if (size > 0){
                    return size;
                } else {
                    System.out.println("Please, enter int:");
                }
            } else {
                System.out.println("Please, enter int:");
                scanner.next();
            }
        }
    }

    public static int getValidatedFrequencyInput(Scanner scanner, String message, int lastDay){
        int dayLimit;
        System.out.println(message);
        while (true){
            if (scanner.hasNextInt()){
                dayLimit = scanner.nextInt();
                if (dayLimit > 0 && dayLimit <= lastDay){
                    return dayLimit;
                } else {
                    System.out.println("Please, enter int > 0 but < last day: ");
                }
            } else {
                System.out.println("Please, enter int > 0 but < last day: ");
                scanner.next();
            }
        }
    }
    public static int getValidatedIntLimitInput(Scanner scanner, String message){
        int dayLimit;
        while (true){
            System.out.println(message);
            if (scanner.hasNextInt()){
                dayLimit = scanner.nextInt();
                if (dayLimit > 0){
                    return dayLimit;
                } else {
                    System.out.println("Please, enter int > 0: ");
                }
            } else {
                System.out.println("Please, enter int > 0:");
                scanner.next();
            }
        }
    }

    public static int optionsForContinue(Scanner console, Island island){
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
            return Validator.getValidatedIntLimitInput(console, "How many days would you like to add?");
        }
        return 0;
    }
}
