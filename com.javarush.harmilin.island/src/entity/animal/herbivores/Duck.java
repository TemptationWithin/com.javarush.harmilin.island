package entity.animal.herbivores;

public class Duck extends Herbivore{
    public static int duckCount = 0;

    public Duck() {
        super(1, 4, 0.15, 100);
        duckCount++;
    }

    @Override
    public void reproduce() {
        System.out.println(getIcon() + " laid eggs.");
    }

    @Override
    public String getIcon() {
        return "🦆";
    }
}
