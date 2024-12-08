package entity.animals.herbivores;

public class Rabbit extends Herbivore{
    public Rabbit(String name, int initialEnergy) {
        super(name, initialEnergy);
    }

    @Override
    public void reproduce() {
        System.out.println(getName() + " gave birth.");
    }
}
