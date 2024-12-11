package entity.animal.herbivores;

import entity.animal.Animal;
import entity.island.Island;

public class Buffalo extends Herbivore{
    public static int buffaloCount = 0;

    public Buffalo(Island island) {
        super(island,700, 3, 100, 100);
        buffaloCount++;
    }

    @Override
    public String getIcon() {
        return "🐃";
    }

    @Override
    protected Animal createOffspring() {
        return new Buffalo(getIsland());
    }
}
