package entity.animals.herbivores;

public class Goat extends Herbivore{
    public Goat(String name, int initialEnergy) {
        super(name, initialEnergy);
    }

    @Override
    public void reproduce() {
        System.out.println(getName() + " gave birth.");
    }
}
