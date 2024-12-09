package entity.animal.predators;

public class Bear extends Predator{
    public static int bearCount = 0;

    public Bear() {
        super(500, 2, 80, 100);
        bearCount++;
    }

    @Override
    public void reproduce() {
        System.out.println(getIcon() + " gave birth.");
    }

    @Override
    public String getIcon() {
        return "🐻";
    }
}
