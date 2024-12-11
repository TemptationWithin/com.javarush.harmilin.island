package entity.animal.herbivores;

import entity.animal.Animal;

public class Buffalo extends Herbivore{
    public static int buffaloCount = 0;

    public Buffalo() {
        super(700, 3, 100, 100);
        buffaloCount++;
    }

    @Override
    public String getIcon() {
        return "🐃";
    }

    @Override
    protected Animal createOffspring() {
        return new Buffalo();
    }
}
