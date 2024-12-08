package entity.animal.herbivores;

public class Buffalo extends Herbivore{
    public static int buffaloCount = 0;

    public Buffalo(int initialEnergy) {
        super(initialEnergy);
        buffaloCount++;
    }

    @Override
    public void reproduce() {
        System.out.println(getIcon() + " gave birth.");
    }

    @Override
    public String getIcon() {
        return "🐃";
    }
}
