package entity.animals.predators;

public class Boa extends Predator{
    public Boa(String name, int initialEnergy) {
        super(name, initialEnergy);
    }

    @Override
    public void reproduce() {
        System.out.println(getName() + " laid eggs.");
    }
}