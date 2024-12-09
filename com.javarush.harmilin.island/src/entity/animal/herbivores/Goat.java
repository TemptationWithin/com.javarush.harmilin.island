package entity.animal.herbivores;

public class Goat extends Herbivore{
    public static int goatCount = 0;

    public Goat() {
        super(70, 3, 10, 100);
        goatCount++;
    }

    @Override
    public void reproduce() {
        System.out.println(getIcon() + " gave birth.");
    }

    @Override
    public String getIcon() {
        return "🐐";
    }
}
