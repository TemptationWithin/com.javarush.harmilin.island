package entity.animals.herbivores;

public class Horse extends Herbivore{
    public static int horseCount = 0;

    public Horse(String name, int initialEnergy) {
        super(name, initialEnergy);
        horseCount++;
    }

    @Override
    public void reproduce() {
        System.out.println(getName() + " gave birth.");
    }
}
