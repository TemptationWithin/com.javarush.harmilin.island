package entity.animal.herbivores;

public class Sheep extends Herbivore{
    public static int sheepCount = 0;

    public Sheep(int initialEnergy) {
        super(initialEnergy);
        sheepCount++;
    }

    @Override
    public void reproduce() {
        System.out.println(getSymbol() + " gave birth.");
    }

    @Override
    public String getSymbol() {
        return "🐑";
    }
}
