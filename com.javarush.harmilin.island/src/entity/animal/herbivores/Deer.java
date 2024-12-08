package entity.animal.herbivores;

public class Deer extends Herbivore{
    public static int deerCount = 0;

    public Deer(int initialEnergy) {
        super(initialEnergy);
        deerCount++;
    }

    @Override
    public void reproduce() {
        System.out.println(getIcon() + " gave birth.");
    }

    @Override
    public String getIcon() {
        return "🦌";
    }
}
