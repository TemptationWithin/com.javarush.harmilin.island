package entity.animal.predators;

public class Boa extends Predator{
    public static int boaCount = 0;

    public Boa(int initialEnergy) {
        super(initialEnergy);
        boaCount++;
    }

    @Override
    public void reproduce() {
        System.out.println(getSymbol() + " laid eggs.");
    }

    @Override
    public String getSymbol() {
        return "🐍";
    }
}
