package entity.animal.predators;

public class Wolf extends Predator{
    public static int wolfCount = 0;

    public Wolf(int initialEnergy) {
        super(initialEnergy);
        wolfCount++;
    }

    @Override
    public void reproduce() {
        System.out.println(getIcon() + " gave birth.");
    }

    @Override
    public String getIcon() {
        return "🐺";
    }
}
