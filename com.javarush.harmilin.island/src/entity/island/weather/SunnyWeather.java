package entity.island.weather;

import entity.animal.Animal;
import entity.animal.CanFly;
import entity.island.Island;

public class SunnyWeather extends Weather{

    public SunnyWeather(){
        super("Sunny", 0, 0);
    }

    @Override
    public void affectAnimal(Animal animal) {
        if (animal instanceof CanFly){
            animal.setSpeed(animal.getSpeed() + getMaxSpeedModifier() + 1);
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
