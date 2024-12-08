package entity.animal.predators;

public class Eagle extends Predator{
    public static int eagleCount = 0;

    public Eagle(int initialEnergy) {
        super(initialEnergy);
        eagleCount++;
    }

    @Override
    public void reproduce() {
        System.out.println(getSymbol() + " laid eggs.");
    }

    @Override
    public String getSymbol() {
        return "🦅";
    }
}
