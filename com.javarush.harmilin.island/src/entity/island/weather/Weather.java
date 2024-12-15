package entity.island.weather;

import entity.animal.Animal;
import entity.island.Island;
import entity.plant.Plant;
import lombok.Data;

@Data
public abstract class Weather {

    private final String name;
    private int maxSpeedModifier;
    private int plantGrowModifier;


    public Weather(String name, int maxSpeedModifier, int plantGrowModifier) {
        this.name = name;
        this.maxSpeedModifier = maxSpeedModifier;
        this.plantGrowModifier = plantGrowModifier;
    }

    public abstract void affectAnimal(Animal animal);

    public abstract void affectPlants(Island island);
}
