package entity.island.weather;

import entity.animal.Animal;
import entity.animal.CanSleepAtWinter;
import entity.island.Island;
import entity.plant.Plant;

public class SnowWeather extends Weather {

    public SnowWeather() {
        super("Snow", -1, -1);
    }

    @Override
    public void affectAnimal(Animal animal) {
        if (animal instanceof CanSleepAtWinter) {
            ((CanSleepAtWinter) animal).sleep();
            System.out.println(animal + " went to sleep until winter will gone in cell " + animal.coordinatesToString());
        } else {
            animal.setEnergy(Math.max(0, animal.getEnergy() - 30));
            if (animal.getEnergy() == 0) {
                animal.die();
                System.out.println("❄️❄️🥶❄️❄️ " + animal + " froze to death in cell: " + animal.coordinatesToString() + " ❄️❄️🥶❄️❄️");
            }
        }
    }

    @Override
    public void affectPlants(Island island) {

    }

    @Override
    public String toString() {
        return "\n" + "--".repeat(5) + "❄️❄️❄️❄️❄️" + "Snow came to Island..." + "❄️❄️❄️❄️❄️" + "--".repeat(5) + "\n" +
                "--".repeat(5) + "❄️❄️❄️❄️❄️" + "Some animals went to sleep..." + "❄️❄️❄️❄️❄️" + "--".repeat(5) + "\n" +
                "--".repeat(5) + "❄️❄️❄️❄️❄️" + "Weak animals will froze..." + "❄️❄️❄️❄️❄️" + "--".repeat(5);
    }
}
