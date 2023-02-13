package home.task;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Floor implements Runnable {

    private final int FIRST_FLOOR = 1;
    Random random = new Random();
    private int currentFloor;

    @Override
    public void run() {
        while (true) {
            DigitPanel digitPanel = DigitPanel.getDigitalPanelInstance();
            int lastFloor = digitPanel.getFloors();

            int randomFloor = ThreadLocalRandom.current().nextInt(FIRST_FLOOR, lastFloor+1);
            digitPanel.selectFloor(randomFloor);
            System.out.println("Random floor that was selected: " + randomFloor + " Thread name: " + Thread.currentThread().getName());
            
            waiting(9000);
        }

    }

    private void waiting(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
