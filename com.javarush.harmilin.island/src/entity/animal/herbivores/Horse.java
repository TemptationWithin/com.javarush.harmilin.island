package entity.animal.herbivores;

import entity.animal.Animal;

public class Horse extends Herbivore{
    public static int horseCount = 0;

    public Horse() {
        super(400, 4, 60, 100);
        horseCount++;
    }

    @Override
    public String getIcon() {
        return "🐎";
    }

    @Override
    protected Animal createOffspring() {
        return new Horse();
    }
}
