package entity.animal.predators;

public class Bear extends Predator{
    public static int bearCount = 0;

    public Bear(int initialEnergy) {
        super(initialEnergy);
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
