package handler;

import entity.island.weather.Weather;

public final class TextInfo {

    public static final String wightRequest = "Please, select width of island: ";
    public static final String lengthRequest = "Please, select length of island: ";
    public static final String dayLimitRequest = "Please, let us know how many days you would like to observe: ";
    public static final String animalsAmountRequest = "Please, let us know how many animals you want to place?\n" +
            "Animal types will be chosen randomly but we will apply correct proportion of predators and herbivores: ";
    public static final String plantsAmountRequest = "Please, let us know how many plants you want to place?: ";
    public static final String enteredSettingsAre = "Thanks. Your settings are: ";
    public static final String funnyCue = "Welcome to hungry games. Lets start...";
    public static final String simulationEnded = "Simulation stopped.";
    public static final String howOftenDisplay = "Island will displayed after the last day of observation." +
            "\nIf you want display it more often - enter the frequency, If no - just press Enter." +
            "\n(ATTENTION! Displays between 1st and last days will not stop the simulation!): ";

    public static void greeting(){
        Timer.sleep(700);
        System.out.println("🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊🦅🌊🌊🌊🌊🌊🌅🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊");
        Timer.sleep(700);
        System.out.println("🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊🦅🌊🌊🌊🌊🌊🌊🌊🌊🌊🏝️🏝️🐇🏝️🏝️🏝️🏝️🐍🏝️🏝️🏝️🏝️🏝️🏝️🏝️🏝️🏝️🏝️🐛🏝️🏝️🏝️🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊🦅🌊🌊🌊");
        Timer.sleep(700);
        System.out.println("🌊🌊🦆🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊🏝️🏝️🏝️🏝️🌴🌴🌴  Welcome to ISLAND  🌴🐐🌴🌴🌴🌴🌴🌴🌴🌴️🏝️️🏝️️🏝️🏝️🏝️🐑🏝️🏝️🏝️🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊");
        Timer.sleep(700);
        System.out.println("🌊🌊🌊🌊🌊🌊🌊🌊🌊🦆🦆🦆🌊🌊🌊🌊🏝️🏝️🏝️🌴🌴🌴🐗🌴🌴🌴🌴🌴🌴🌴🌴🌴🌴🌴🌴💤🌴🌴🌴🌴🌴🌴🐁🌴🌴🌴🌴🌴🌴🐺🐺🌴🌴🏝️🏝️🏝️️🌊🌊🌊🌊🌊🌊🌊🌊🌊🦆🦆");
        Timer.sleep(700);
        System.out.println("🏝️🐛🏝️🏝️🏝️🏝️🏝️🏝️🦅️🏝️🏝️🏝️🏝️🏝️🏝️🏝️🌳🌲🌳🌲🌳🌲🌳🌲🌳🌲🐇🌲🌳🌲🌳🌲🌳🌲💤🌲🌳🌲🌳🌲🌳🌲🌳🌲🌳🌲🌳🌲🌳🌲🌳🌲🌳🌲🐃🐃🌴🏝️🏝️️🏝️🏝️🏝️🏝️🏝️🏝️🏝️🏝️");
        Timer.sleep(700);
        System.out.println("🐁🌲🌳🌲🌳🌲🌳🌲🌳🌲🌳🌲🐑🌲🦌🦌🌴🌳🐺🐺🐺🌳🌲🌳🌲🌳🌲🌳🌲🌳🌲🌳🌲🐻🌲🌳🌲🌳🌲🌳🌲🌳🌲🌳🌲🌳🌲🌳🦊🌳🌲🌳🌲🌳🌲🌳🌲🌳🌲🌳🌲🌳🌲🌳🐇🌳🌲");
        Timer.sleep(1000);
        System.out.println("Please, enter size of island: ");
    }

    public static void announcementOfTheDay(int day, Weather weather){
        System.out.println(weather);
        System.out.println("+" + "---".repeat(2) + " +");
        System.out.println("|DAY #" + day + " |");
        System.out.println("+" + "___".repeat(2) + " +");
    }
}