package entity.animals.herbivores;

public class Caterpillar extends Herbivore{
    public Caterpillar(String name, int initialEnergy) {
        super(name, initialEnergy);
    }

    @Override
    public void reproduce() {
        System.out.println(getName() + " gave birth.");
    }
}
