package entity.animal.predators;

public class Eagle extends Predator{
    public static int eagleCount = 0;

    public Eagle() {
        super(6, 3, 1, 100);
        eagleCount++;
    }

    @Override
    public void reproduce() {
        System.out.println(getIcon() + " laid eggs.");
    }

    @Override
    public String getIcon() {
        return "🦅";
    }
}
