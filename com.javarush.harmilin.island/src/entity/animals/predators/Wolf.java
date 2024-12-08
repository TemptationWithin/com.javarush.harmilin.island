package entity.animals.predators;

public class Wolf extends Predator{
    public static int wolfCount = 0;

    public Wolf(String name, int initialEnergy) {
        super(name, initialEnergy);
        wolfCount++;
    }

    @Override
    public void reproduce() {
        System.out.println(getName() + " gave birth.");
    }
}
