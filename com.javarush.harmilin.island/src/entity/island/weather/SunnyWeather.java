package entity.island.weather;

import entity.animal.Animal;
import entity.animal.CanFly;
import entity.animal.CanSleepAtWinter;
import entity.island.Island;

public class SunnyWeather extends Weather{

    public SunnyWeather(){
        super("Sunny", 0, 0);
    }

    @Override
    public void affectAnimal(Animal animal) {
        if (animal instanceof CanSleepAtWinter){
            ((CanSleepAtWinter) animal).awake();
        }
        if (animal instanceof CanFly){
            animal.setSpeed(animal.getMaxSpeed() + 1); // during set of sunny days birds will fly to long distances
        } else {
            animal.setSpeed(animal.getMaxSpeed());
        }
    }

    @Override
    public void affectPlants(Island island) {

    }

    @Override
    public String toString() {
        return "\n" + "--".repeat(5) + "🌞🌞🌞 " + "Sun came out from the clouds.."+ "--------==---🌞🌞🌞" + "--".repeat(5) +"\n" +
                "--".repeat(5) + "🌞🌞🌞 "+ "All animals have regular characteristics! " + "🌞🌞🌞" + "--".repeat(5) ;
    }
}
