package entity.animals.herbivores;

public class Boar extends Herbivore{
    public static int boarCount = 0;

    public Boar(String name, int initialEnergy) {
        super(name, initialEnergy);
        boarCount++;
    }

    @Override
    public void reproduce() {
        System.out.println(getName() + " gave birth.");
    }
}
