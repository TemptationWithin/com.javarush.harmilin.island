package entity.animals.herbivores;

public class Sheep extends Herbivore{
    public Sheep(String name, int initialEnergy) {
        super(name, initialEnergy);
    }

    @Override
    public void reproduce() {
        System.out.println(getName() + " gave birth.");
    }
}
