package entity.animal.herbivores;

public class Boar extends Herbivore{
    public static int boarCount = 0;

    public Boar(int initialEnergy) {
        super(initialEnergy);
        boarCount++;
    }

    @Override
    public void reproduce() {
        System.out.println(getIcon() + " gave birth.");
    }

    @Override
    public String getIcon() {
        return "🐗";
    }
}
