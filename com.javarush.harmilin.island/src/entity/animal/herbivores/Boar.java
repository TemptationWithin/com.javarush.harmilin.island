package entity.animal.herbivores;

public class Boar extends Herbivore{
    public static int boarCount = 0;

    public Boar(int initialEnergy) {
        super(initialEnergy);
        boarCount++;
    }

    @Override
    public void reproduce() {
        System.out.println(getSymbol() + " gave birth.");
    }

    @Override
    public String getSymbol() {
        return "🐗";
    }
}
