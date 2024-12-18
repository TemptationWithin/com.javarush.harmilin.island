import handler.TextInfo;
import entity.island.Island;
import handler.ExecutorHandler;
import handler.Timer;
import handler.Validator;

import java.io.Console;
import java.util.Scanner;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        TextInfo.greeting();

        Scanner console = new Scanner(System.in);

        int width = Validator.getValidatedSizeInput(console, TextInfo.wightRequest);
        int length = Validator.getValidatedSizeInput(console, TextInfo.lengthRequest);
        int dayLimitation = Validator.getValidatedIntLimitInput(console, TextInfo.dayLimitRequest);
        int startAnimals = Validator.getValidatedIntLimitInput(console, TextInfo.animalsAmountRequest);
        int startPlants = Validator.getValidatedIntLimitInput(console, TextInfo.plantsAmountRequest);
        int displayFrequency = Validator.getValidatedFrequencyInput(console, TextInfo.howOftenDisplay, dayLimitation);

        Island island = new Island(width, length);
        island.randomBegin(startAnimals, startPlants);

        System.out.println(TextInfo.enteredSettingsAre);
        System.out.println(island);

        Timer.sleep(1000);
        System.out.println(TextInfo.funnyCue);
        Timer.funnyPreparing();
        Timer.sleep(500);

        ExecutorHandler executorHandler = new ExecutorHandler();

        int day = 0;
        island.display();

        while (day < dayLimitation) {
            synchronized (System.out) {
                island.changeWeather();
                TextInfo.announcementOfTheDay(day, island.getCurrentWeather());
            }
            Timer.sleep(2000);
            executorHandler.getExecutorService().submit(island::cleanUp);
            executorHandler.getExecutorService().submit(island::allPerformActions);
            executorHandler.getScheduler().scheduleWithFixedDelay(island::growAllPlants, 0, 20, TimeUnit.SECONDS);
            //executorHandler.getScheduler().scheduleWithFixedDelay(island::moveAllAnimals, 0, 10, TimeUnit.SECONDS);
            Timer.sleep(100);

            day++;

            if (day == dayLimitation) {
                executorHandler.shutdownAllExecutors();
                Timer.sleep(2000);
                island.display();
                dayLimitation = dayLimitation + Validator.optionsForContinue(console, island);
                executorHandler.restartExecutors();
            } else if (day % displayFrequency == 0){
                executorHandler.shutdownAllExecutors();
                Timer.sleep(2000);
                island.display();
                executorHandler.restartExecutors();
            }

        }

        System.out.println("Last day #" + dayLimitation + " ended.");
        island.statisticPerCell();
        island.display();
        island.stopSimulation();
        executorHandler.shutdownAllExecutors();
        console.close();
        System.out.println(TextInfo.simulationEnded);

        Timer.sleep(1000);
    }

}