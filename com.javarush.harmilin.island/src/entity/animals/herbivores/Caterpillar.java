package entity.animals.herbivores;

public class Caterpillar extends Herbivore{
    public static int caterpillarCount = 0;

    public Caterpillar(String name, int initialEnergy) {
        super(name, initialEnergy);
        caterpillarCount++;
    }

    @Override
    public void reproduce() {
        System.out.println(getName() + " gave birth.");
    }
}
