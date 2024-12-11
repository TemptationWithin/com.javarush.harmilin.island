package entity.animal.herbivores;

import entity.animal.Animal;

public class Duck extends Herbivore{
    public static int duckCount = 0;

    public Duck() {
        super(1, 4, 0.15, 100);
        setPreyChance(Caterpillar.class, 90);
        duckCount++;
    }

    @Override
    public String getIcon() {
        return "🦆";
    }

    @Override
    protected Animal createOffspring() {
        return new Duck();
    }
}
