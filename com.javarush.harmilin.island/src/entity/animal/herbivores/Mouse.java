package entity.animal.herbivores;

import entity.animal.Animal;
import entity.island.Island;

public class Mouse extends Herbivore{
    public static int mouseCount = 0;

    public Mouse(Island island) {
        super(island,0.05, 1, 0.01, 100);
        setPreyChance(Caterpillar.class, 90);
        mouseCount++;
    }

    @Override
    public String getIcon() {
        return "🐁";
    }

    @Override
    protected Animal createOffspring() {
        return new Mouse(getIsland());
    }
}
