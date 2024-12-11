package entity.animal.herbivores;

import entity.animal.Animal;

public class Mouse extends Herbivore{
    public static int mouseCount = 0;

    public Mouse() {
        super(0.05, 1, 0.01, 100);
        setPreyChance(Caterpillar.class, 90);
        mouseCount++;
    }

    @Override
    public String getIcon() {
        return "🐁";
    }

    @Override
    protected Animal createOffspring() {
        return new Mouse();
    }
}
