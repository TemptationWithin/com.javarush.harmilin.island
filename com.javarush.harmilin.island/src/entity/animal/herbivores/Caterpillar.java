package entity.animal.herbivores;

public class Caterpillar extends Herbivore{
    public static int caterpillarCount = 0;

    public Caterpillar(int initialEnergy) {
        super(initialEnergy);
        caterpillarCount++;
    }

    @Override
    public void reproduce() {
        System.out.println(getIcon() + " gave birth.");
    }

    @Override
    public String getIcon() {
        return "🐛";
    }
}
