package entity.animal.herbivores;

public class Caterpillar extends Herbivore{
    public static int caterpillarCount = 0;

    public Caterpillar() {
        super(0.01, 0, 0, 100);
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
