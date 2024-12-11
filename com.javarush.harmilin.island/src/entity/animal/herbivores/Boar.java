package entity.animal.herbivores;


import entity.animal.Animal;

public class Boar extends Herbivore{
    public static int boarCount = 0;

    public Boar() {
        super(400, 2, 50, 100);
        setPreyChance(Mouse.class, 50);
        setPreyChance(Caterpillar.class, 90);
        boarCount++;
    }

    @Override
    public void reproduce() {
        System.out.println(getIcon() + " gave birth.");
    }

    @Override
    public String getIcon() {
        return "🐗";
    }

    @Override
    protected Animal createOffspring() {
        return new Boar();
    }
}
