package entity.animal.herbivores;

import entity.animal.Animal;
import entity.island.Island;

public class Duck extends Herbivore{
    public static int duckCount = 0;

    public Duck(Island island) {
        super(island,1, 4, 0.15, 100);
        setPreyChance(Caterpillar.class, 90);
        duckCount++;
    }

    @Override
    public String getIcon() {
        return "🦆";
    }

    @Override
    protected Animal createOffspring() {
        return new Duck(getIsland());
    }
}
