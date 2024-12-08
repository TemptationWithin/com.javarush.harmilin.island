package entity.animal.herbivores;

public class Goat extends Herbivore{
    public static int goatCount = 0;

    public Goat(int initialEnergy) {
        super(initialEnergy);
        goatCount++;
    }

    @Override
    public void reproduce() {
        System.out.println(getSymbol() + " gave birth.");
    }

    @Override
    public String getSymbol() {
        return "🐐";
    }
}
