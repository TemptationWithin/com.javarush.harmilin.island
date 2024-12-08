package entity.animals.herbivores;

public class Mouse extends Herbivore{
    public static int mouseCount = 0;

    public Mouse(String name, int initialEnergy) {
        super(name, initialEnergy);
        mouseCount++;
    }

    @Override
    public void reproduce() {
        System.out.println(getName() + " gave birth.");
    }
}
