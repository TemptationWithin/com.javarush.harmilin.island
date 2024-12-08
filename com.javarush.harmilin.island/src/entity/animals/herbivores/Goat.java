package entity.animals.herbivores;

public class Goat extends Herbivore{
    public static int goatCount = 0;

    public Goat(String name, int initialEnergy) {
        super(name, initialEnergy);
        goatCount++;
    }

    @Override
    public void reproduce() {
        System.out.println(getName() + " gave birth.");
    }
}
