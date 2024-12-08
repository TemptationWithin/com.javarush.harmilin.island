package entity.animals.herbivores;

public class Duck extends Herbivore{
    public Duck(String name, int initialEnergy) {
        super(name, initialEnergy);
    }

    @Override
    public void reproduce() {
        System.out.println(getName() + " laid eggs.");
    }
}
