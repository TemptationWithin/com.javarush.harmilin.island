package entity.animals.herbivores;

public class Boar extends Horse{
    public Boar(String name, int initialEnergy) {
        super(name, initialEnergy);
    }

    @Override
    public void reproduce() {
        System.out.println(getName() + " gave birth.");
    }
}
