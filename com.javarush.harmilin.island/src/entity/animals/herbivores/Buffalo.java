package entity.animals.herbivores;

public class Buffalo extends Herbivore{
    public Buffalo(String name, int initialEnergy) {
        super(name, initialEnergy);
    }

    @Override
    public void reproduce() {
        System.out.println(getName() + " gave birth.");
    }
}
