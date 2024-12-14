package entity.island;

public class Day {
    private int day = 1;

    public synchronized int getDay() {
        return day;
    }

    public synchronized void increment(){
        day++;
    }
}
