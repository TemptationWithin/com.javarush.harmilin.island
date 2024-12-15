package entity.island.weather;

import entity.animal.Animal;
import entity.animal.CanFly;
import entity.island.Island;
import entity.plant.Plant;

import java.util.Random;

public class RainWeather extends Weather{

    public RainWeather(){
        super("Rain", -1, 2);
    }

    @Override
    public void affectAnimal(Animal animal) {
        if (animal instanceof CanFly){
            animal.setSpeed(0);
        } else {
            animal.setSpeed(Math.max(0, animal.getSpeed() + getMaxSpeedModifier()));

        }
    }

    @Override
    public void affectPlants(Island island) {
        Random random = new Random();
        int count = random.nextInt(100);
        for (int i = 0; i < count; i++) {
            Plant plant = new Plant(island);
            island.placePlant(island, plant);
            plant.getCurrentCell().addPlant(plant);
            plant.getCurrentCell().addIcon(plant.getIcon());
            Plant.plantCount.incrementAndGet();
        }
        System.out.println("Rain stops." + count + " plants appearing.");
    }

    @Override
    public String toString() {
        return "\n" + "--".repeat(5) + "🌧️🌦️ 🌧️🌦️" + "Rain starts...."+ "---------🌧️🌦️ 🌧️🌦️" + "--".repeat(5) + "\n" +
                "--".repeat(5) + "🌧️🌦️ 🌧️🌦️" + "Birds can't fly" + "---------🌧️🌦️ 🌧️🌦️" + "--".repeat(5) + "\n" +
                "--".repeat(5) + "🌧️🌦️ 🌧️🌦️" + "Animals have " + getMaxSpeedModifier() + " to speed" + "🌧️🌦️ 🌧️🌦️" + "--".repeat(5)+ "\n" +
                "--".repeat(5) + "🌧️🌦️ 🌧️🌦️" + "Plants is growing..." + "----🌧️🌦️ 🌧️🌦️" + "--".repeat(5);
    }
}
