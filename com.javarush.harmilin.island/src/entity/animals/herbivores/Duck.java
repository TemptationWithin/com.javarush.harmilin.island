package entity.animals.herbivores;

public class Duck extends Herbivore{
    public static int duckCount = 0;

    public Duck(String name, int initialEnergy) {
        super(name, initialEnergy);
        duckCount++;
    }

    @Override
    public void reproduce() {
        System.out.println(getName() + " laid eggs.");
    }
}
