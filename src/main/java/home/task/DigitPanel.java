package home.task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DigitPanel {

    private static DigitPanel digitalPanelInstance = new DigitPanel();
    private int floors;
    private List<Integer> selectedFloors;

    private DigitPanel() {
        this.selectedFloors = Collections.synchronizedList(new ArrayList<>());
    }

    public static DigitPanel getDigitalPanelInstance() {
        return digitalPanelInstance;
    }

    public int getFloors() {
        return floors;
    }

    public void setFloors(int floors) {
        this.floors = floors;
    }

    public List<Integer> getSelectedFloors() {
        return selectedFloors;
    }

    public void removeFloor(int floor) {
        for (int i = 0; i < selectedFloors.size(); i++) {
            if (selectedFloors.get(i) == floor) {
                selectedFloors.remove(i);
            }
        }
    }

    public void removeFloor(List<Integer> removeFloors) {
        this.selectedFloors.removeAll(removeFloors);
    }

    public void selectFloor(int floor) {
        if (floor < 0 || floor > floors) {
            return;
        }
        if (this.selectedFloors.contains(floor)) {
            return;
        }
        this.selectedFloors.add(floor);
    }

    @Override
    public String toString() {
        return String.valueOf(selectedFloors);
    }
}
