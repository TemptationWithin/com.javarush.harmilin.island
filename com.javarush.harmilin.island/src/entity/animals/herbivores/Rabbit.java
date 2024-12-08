package entity.animals.herbivores;

public class Rabbit extends Herbivore{
    public static int rabbitCount = 0;

    public Rabbit(String name, int initialEnergy) {
        super(name, initialEnergy);
        rabbitCount++;
    }

    @Override
    public void reproduce() {
        System.out.println(getName() + " gave birth.");
    }
}
