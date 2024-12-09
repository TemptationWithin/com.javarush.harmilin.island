package entity.animal.predators;

public class Wolf extends Predator{
    public static int wolfCount = 0;

    public Wolf() {
        super(50, 3, 8, 100);
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
