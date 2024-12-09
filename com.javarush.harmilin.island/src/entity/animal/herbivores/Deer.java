package entity.animal.herbivores;

public class Deer extends Herbivore{
    public static int deerCount = 0;

    public Deer() {
        super(300, 4, 50, 100);
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
