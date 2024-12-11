package entity.animal.herbivores;

import entity.animal.Animal;

public class Rabbit extends Herbivore{
    public static int rabbitCount = 0;

    public Rabbit() {
        super(2, 2, 0.45, 100);
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

    @Override
    protected Animal createOffspring() {
        return new Rabbit();
    }
}
