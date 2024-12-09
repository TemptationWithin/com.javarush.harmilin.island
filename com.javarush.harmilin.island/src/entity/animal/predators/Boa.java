package entity.animal.predators;

public class Boa extends Predator{
    public static int boaCount = 0;

    public Boa() {
        super(15, 1, 3, 100);
        boaCount++;
    }

    @Override
    public void reproduce() {
        System.out.println(getIcon() + " laid eggs.");
    }

    @Override
    public String getIcon() {
        return "🐍";
    }
}
