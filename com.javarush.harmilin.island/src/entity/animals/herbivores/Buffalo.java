package entity.animals.herbivores;

public class Buffalo extends Herbivore{
    public static int buffaloCount = 0;

    public Buffalo(String name, int initialEnergy) {
        super(name, initialEnergy);
        buffaloCount++;
    }

    @Override
    public void reproduce() {
        System.out.println(getName() + " gave birth.");
    }
}
