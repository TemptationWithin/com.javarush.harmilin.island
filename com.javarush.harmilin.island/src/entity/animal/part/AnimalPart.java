package entity.animal.part;

import entity.animal.Animal;
import entity.island.Cell;
import entity.island.Island;
import lombok.Data;

import java.util.concurrent.atomic.AtomicInteger;

@Data
public abstract class AnimalPart {
    public static AtomicInteger animalPartsCount = new AtomicInteger(0);

    private double foodAmount;
    private Cell currentCell;
    private int x_Coordinate, y_Coordinate;
    private Island island;
    private String icon;
    private Animal animal;

    public AnimalPart(Animal animal) {
        this.island = animal.getIsland();
        this.animal = animal;
        this.setCurrentCell(animal.getCurrentCell());
        this.getCurrentCell().updateIcons();
        this.getCurrentCell().getAnimalParts().add(this);
        island.getAnimalParts().add(this);
        animalPartsCount.incrementAndGet();
    }

    public void setCurrentCell(Cell currentCell) {
        this.currentCell = currentCell;
        this.x_Coordinate = currentCell.getX_Coordinate();
        this.y_Coordinate = currentCell.getY_Coordinate();
    }

    @Override
    public String toString() {
        return this.getIcon() + "(" + this.getClass().getSimpleName() + " of " + animal + ")" +
                " laying at cell: "
                + this.animal.coordinatesToString();
    }

    public void erase() {
        if (this instanceof Meat) {
            Meat.animalMeatCount.decrementAndGet();
        }
        if (this instanceof Bone) {
            Bone.animalBonesCount.decrementAndGet();
        }
        this.getCurrentCell().getAnimalParts().remove(this);
        AnimalPart.animalPartsCount.decrementAndGet();
        this.getCurrentCell().updateIcons();
        this.getIsland().getAnimalParts().remove(this);
    }
}
