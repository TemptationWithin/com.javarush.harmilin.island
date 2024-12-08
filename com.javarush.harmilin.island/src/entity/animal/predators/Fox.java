package entity.animal.predators;

public class Fox extends Predator{
    public static int foxCount = 0;

    public Fox(int initialEnergy) {
        super(initialEnergy);
        foxCount++;
    }

    @Override
    public void reproduce() {
        System.out.println(getIcon() + " gave birth.");
    }

    @Override
    public String getIcon() {
        return "🦊";
    }
}
