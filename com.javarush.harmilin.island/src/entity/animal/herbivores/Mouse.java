package entity.animal.herbivores;

public class Mouse extends Herbivore{
    public static int mouseCount = 0;

    public Mouse(int initialEnergy) {
        super(initialEnergy);
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
