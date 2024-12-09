package entity.cell;
import entity.animal.Animal;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Cell {
    private final Set<String> icons = new HashSet<>();
    private final Map<String, Integer> animals = new HashMap<>();
    private final Map<String, Integer> limits;

    public Cell(Map<String, Integer> limits) {
        this.limits = limits;
    }

    public void addIcon(String icon){
        synchronized (icons){
            icons.add(icon);
        }
    }

    public boolean isEmpty(){
        return icons.isEmpty();
    }

    public String getFormattedContent(){
        if (isEmpty()){
            return "";
        }
        return String.join(" ", icons);
    }
}
