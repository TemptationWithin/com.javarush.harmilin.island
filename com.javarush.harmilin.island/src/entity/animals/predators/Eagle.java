package entity.animals.predators;

public class Eagle extends Predator{

    public Eagle(String name, int initialEnergy) {
        super(name, initialEnergy);
    }

    @Override
    public void reproduce() {
        System.out.println(getName() + " laid eggs.");
    }
}
