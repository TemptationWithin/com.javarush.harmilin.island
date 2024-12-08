package entity.animals.herbivores;

public class Deer extends Herbivore{
    public Deer(String name, int initialEnergy) {
        super(name, initialEnergy);
    }

    @Override
    public void reproduce() {
        System.out.println(getName() + " gave birth.");
    }
}
