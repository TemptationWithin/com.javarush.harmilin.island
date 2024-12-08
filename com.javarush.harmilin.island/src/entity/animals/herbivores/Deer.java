package entity.animals.herbivores;

public class Deer extends Herbivore{
    public static int deerCount = 0;

    public Deer(String name, int initialEnergy) {
        super(name, initialEnergy);
        deerCount++;
    }

    @Override
    public void reproduce() {
        System.out.println(getName() + " gave birth.");
    }
}
