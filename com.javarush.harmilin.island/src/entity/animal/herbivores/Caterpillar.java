package entity.animal.herbivores;

import entity.animal.Animal;

public class Caterpillar extends Herbivore{
    public static int caterpillarCount = 0;

    public Caterpillar() {
        super(0.01, 0, 0, 100);
        caterpillarCount++;
    }

    @Override
    public String getIcon() {
        return "🐛";
    }

    @Override
    protected Animal createOffspring() {
        return new Caterpillar();
    }
}
