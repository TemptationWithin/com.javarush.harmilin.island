package entity.cell;
import java.util.HashSet;
import java.util.Set;

public class Cell {
    private final Set<String> icons = new HashSet<>();


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
