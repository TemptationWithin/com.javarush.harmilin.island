package entity;

public abstract class Predator extends Animal{

    public Predator(String name, int initialEnergy){
        super(name, initialEnergy);
    }

    @Override
    public void eat(Object food) {
        if (food instanceof Herbivore){
            System.out.println(getName() + " ate " + (((Herbivore) food).getName()) + ".");
            increaseEnergy(20);
        } else {
            System.out.println(getName() + " can't eat this.");
        }
    }
}
