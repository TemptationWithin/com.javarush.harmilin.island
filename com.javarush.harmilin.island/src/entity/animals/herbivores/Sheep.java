package entity.animals.herbivores;

public class Sheep extends Herbivore{
    public static int sheepCount = 0;

    public Sheep(String name, int initialEnergy) {
        super(name, initialEnergy);
        sheepCount++;
    }

    @Override
    public void reproduce() {
        System.out.println(getName() + " gave birth.");
    }
}
