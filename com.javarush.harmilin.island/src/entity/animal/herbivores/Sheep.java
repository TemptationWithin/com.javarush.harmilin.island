package entity.animal.herbivores;

public class Sheep extends Herbivore{
    public static int sheepCount = 0;

    public Sheep() {
        super(70, 3, 15, 100);
        sheepCount++;
    }

    @Override
    public void reproduce() {
        System.out.println(getIcon() + " gave birth.");
    }

    @Override
    public String getIcon() {
        return "🐑";
    }
}
