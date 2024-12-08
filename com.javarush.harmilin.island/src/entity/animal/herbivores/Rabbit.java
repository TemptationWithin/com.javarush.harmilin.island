package entity.animal.herbivores;

public class Rabbit extends Herbivore{
    public static int rabbitCount = 0;

    public Rabbit(int initialEnergy) {
        super(initialEnergy);
        rabbitCount++;
    }

    @Override
    public void reproduce() {
        System.out.println(getSymbol() + " gave birth.");
    }

    @Override
    public String getSymbol() {
        return "🐇";
    }
}
