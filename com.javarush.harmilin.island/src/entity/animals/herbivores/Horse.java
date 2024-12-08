package entity.animals.herbivores;

public class Horse extends Herbivore{
    public Horse(String name, int initialEnergy) {
        super(name, initialEnergy);
    }

    @Override
    public void reproduce() {
        System.out.println(getName() + " gave birth.");
    }
}
