package entity.animal.herbivores;

public class Duck extends Herbivore{
    public static int duckCount = 0;

    public Duck(int initialEnergy) {
        super(initialEnergy);
        duckCount++;
    }

    @Override
    public void reproduce() {
        System.out.println(getSymbol() + " laid eggs.");
    }

    @Override
    public String getSymbol() {
        return "🦆";
    }
}
