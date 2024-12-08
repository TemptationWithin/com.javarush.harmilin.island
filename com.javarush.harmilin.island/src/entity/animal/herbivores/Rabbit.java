package entity.animal.herbivores;

public class Rabbit extends Herbivore{
    public static int rabbitCount = 0;

    public Rabbit(int initialEnergy) {
        super(initialEnergy);
        rabbitCount++;
    }

    @Override
    public void reproduce() {
        System.out.println(getIcon() + " gave birth.");
    }

    @Override
    public String getIcon() {
        return "🐇";
    }
}
