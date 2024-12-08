package entity.animals.predators;

public class Eagle extends Predator{
    public static int eagleCount = 0;

    public Eagle(String name, int initialEnergy) {
        super(name, initialEnergy);
        eagleCount++;
    }

    @Override
    public void reproduce() {
        System.out.println(getName() + " laid eggs.");
    }
}
