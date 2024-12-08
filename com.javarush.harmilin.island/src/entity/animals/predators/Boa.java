package entity.animals.predators;

public class Boa extends Predator{
    public static int boaCount = 0;

    public Boa(String name, int initialEnergy) {
        super(name, initialEnergy);
        boaCount++;
    }

    @Override
    public void reproduce() {
        System.out.println(getName() + " laid eggs.");
    }
}
