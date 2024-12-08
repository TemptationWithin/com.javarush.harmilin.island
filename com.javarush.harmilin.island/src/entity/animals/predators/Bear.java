package entity.animals.predators;

public class Bear extends Predator{
    public static int bearCount = 0;

    public Bear(String name, int initialEnergy) {
        super(name, initialEnergy);
        bearCount++;
    }

    @Override
    public void reproduce() {
        System.out.println(getName() + " gave birth.");
    }
}
