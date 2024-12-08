package entity.animals.predators;

public class Fox extends Predator{
    public static int foxCount = 0;

    public Fox(String name, int initialEnergy) {
        super(name, initialEnergy);
        foxCount++;
    }

    @Override
    public void reproduce() {
        System.out.println(getName() + " gave birth.");
    }
}
