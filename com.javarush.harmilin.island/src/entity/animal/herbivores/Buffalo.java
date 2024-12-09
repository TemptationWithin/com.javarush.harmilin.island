package entity.animal.herbivores;

public class Buffalo extends Herbivore{
    public static int buffaloCount = 0;

    public Buffalo() {
        super(700, 3, 100, 100);
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
