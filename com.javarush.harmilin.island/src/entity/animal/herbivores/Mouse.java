package entity.animal.herbivores;

public class Mouse extends Herbivore{
    public static int mouseCount = 0;

    public Mouse() {
        super(0.05, 1, 0.01, 100);
        mouseCount++;
    }

    @Override
    public void reproduce() {
        System.out.println(getIcon() + " gave birth.");
    }

    @Override
    public String getIcon() {
        return "🐁";
    }
}
