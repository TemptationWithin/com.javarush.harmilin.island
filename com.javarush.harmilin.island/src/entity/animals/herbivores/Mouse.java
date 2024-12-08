package entity.animals.herbivores;

public class Mouse extends Herbivore{
    public Mouse(String name, int initialEnergy) {
        super(name, initialEnergy);
    }

    @Override
    public void reproduce() {
        System.out.println(getName() + " gave birth.");
    }
}
