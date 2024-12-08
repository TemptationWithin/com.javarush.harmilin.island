package entity.island;

import entity.animal.Animal;
import entity.cell.Cell;
import entity.plant.Plant;

import java.util.Random;

public class Island {
    private final int rows;
    private final int cols;
    private final Cell[][] grid;

    public Island(int rows, int cols){
        this.rows = rows;
        this.cols = cols;
        this.grid = new Cell[rows][cols];
        initializeGrid();
    }

    private void initializeGrid(){
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = new Cell();
            }
        }
    }

    public void placeAnimal(Animal animal){
        Random random = new Random();
        animal.setX_Coordinate(random.nextInt(rows));
        animal.setY_Coordinate(random.nextInt(cols));
        grid[animal.getX_Coordinate()][animal.getY_Coordinate()].addIcon(animal.getIcon());
    }

    public void placePlant(Plant plant){
        Random random = new Random();
        plant.setX_Coordinate(random.nextInt(rows));
        plant.setY_Coordinate(random.nextInt(cols));
        grid[plant.getX_Coordinate()][plant.getY_Coordinate()].addIcon(plant.getIcon());
    }

    private int calculateMaxCellWidth(){
        int maxWidth = 0;
        for(Cell[] row: grid){
            for (Cell cell : row){
                int cellContentWidth = cell.getFormattedContent().length();
                if (cellContentWidth > maxWidth){
                    maxWidth = cellContentWidth;
                }
            }
        }
        return maxWidth + 2;
    }

    public void display(){
        int cellWidth = calculateMaxCellWidth();
        String horizontalBorder = "+".repeat(cellWidth * cols + cols +1);
        System.out.println(horizontalBorder);
        for(Cell[] row : grid){
            StringBuilder rowContent = new StringBuilder("|");
            for (Cell cell : row){
                String content = cell.getFormattedContent();
                rowContent.append(String.format(" %-" + (cellWidth - 2) + "s |", content));
            }
            System.out.println(rowContent);
            System.out.println(horizontalBorder);
        }
    }
}
