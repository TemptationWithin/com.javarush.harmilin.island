package entity;

public abstract class Predator extends Animal{

    public Predator(String name, int initialEnergy){
        super(name, initialEnergy);
    }

    @Override
    public void eat(Object food) {
        if (food instanceof Herbivore){
            System.out.println();
        }
    }
}
