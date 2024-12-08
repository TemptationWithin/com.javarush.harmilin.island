package entity.animal.herbivores;

public class Horse extends Herbivore{
    public static int horseCount = 0;

    public Horse(int initialEnergy) {
        super(initialEnergy);
        horseCount++;
    }

    @Override
    public void reproduce() {
        System.out.println(getIcon() + " gave birth.");
    }

    @Override
    public String getIcon() {
        return "🐎";
    }
}
