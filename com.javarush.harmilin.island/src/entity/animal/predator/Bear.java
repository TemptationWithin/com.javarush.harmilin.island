package entity.animal.predator;

import entity.animal.Animal;
import entity.animal.CanSleepAtWinter;
import entity.animal.herbivore.*;
import entity.island.Island;
import lombok.Getter;

import java.util.concurrent.atomic.AtomicInteger;

@Getter
public class Bear extends Predator implements CanSleepAtWinter {
    public static AtomicInteger bearCount = new AtomicInteger(0);
    private boolean isSleep;

    public Bear(Island island) {
        super(island,500, 2, 80, 100);
        setPreyChance(Boa.class, 80);
        setPreyChance(Horse.class, 40);
        setPreyChance(Deer.class, 80);
        setPreyChance(Rabbit.class, 80);
        setPreyChance(Mouse.class, 90);
        setPreyChance(Goat.class, 70);
        setPreyChance(Sheep.class, 70);
        setPreyChance(Boar.class, 50);
        setPreyChance(Buffalo.class, 20);
        setPreyChance(Duck.class, 10);
        setSpeed(getMaxSpeed());
        bearCount.incrementAndGet();
    }

    public boolean isSleep() {
        return isSleep;
    }

    @Override
    public void sleep() {
        if (!isSleep()) {
            isSleep = true;
            System.out.println("💤💤💤"+ this + " went sleep in cell:" + this.coordinatesToString() + " 💤💤💤");
        }
    }

    @Override
    public void awake() {
        if (isSleep()){
            this.setEnergy(100);
            System.out.println("🌞🌞🌞 " + this + " awake after winter:" + this.coordinatesToString() + " 🌞🌞🌞");
        }
        isSleep = false;
    }

    @Override
    public String getIcon() {
        return "🐻";
    }

    @Override
    protected Animal createOffspring() {
        return new Bear(getIsland());
    }
}
