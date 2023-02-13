package home.task;

// House has 25 floors and only 1 big elevator (1-25, without floors below zero)
// Housemates come into the lift and press floor number. Someone can press already selected floor once again.
// Elevator's software remembers all pressed numbers (even repeated).
// New elevator also handles requests, when housemate clicked on some floor except 1.

// Base task - implement logic in method calcFloorSequence.
// 1 Elevator should have 2 directions : UP and DOWN.
// 2 On the floor we have only one button - call elevator without direction
// Short summary - base on direction calculate correct floor sequence. Also provide simple unit tests.
// Extended task
// 3 Each floor is a separate thread. Each thread generates (or not generates) 1 digit (1-N, N=number of floors) and stores it to the digit panel
// 4 Elevator checks if any new request came and depending on direction add new floors to the floor sequence
// Short summary
// Implement multithreading - each floor has own thread and produces a number, elevator has also separate threads
// All threads are working with a single Digit Panel (you can event implement singleton, if you want)
// All threads are starting from ElevatorSyncRunner

// NO UI NEEDED

// Output example
// Digit Panel    : [1,3] - value is stored in the panel if it's doesn't suite direction
// Direction      : UP
// Current floor  : 4
// Sequence Floors: [5,6,7,8]

// Output example
// Digit Panel    : [5] - value is stored in the panel if it's doesn't suite direction
// Direction      : DOWN
// Current floor  : 4
// Sequence Floors: [3,2,1]

// Upload your implementation to the github
// If you have any questions - contact me via email andrei_matys@atlantconsult.com


public class ElevatorSyncRunner {

    public static void main(String[] args) {

        // Create digit panel for elevator
        DigitPanel digitPanel = DigitPanel.getDigitalPanelInstance();
        digitPanel.setFloors(25);
        
        digitPanel.selectFloor(9);
        digitPanel.selectFloor(2);
        digitPanel.selectFloor(4);
        digitPanel.selectFloor(6);
        digitPanel.selectFloor(9);
        digitPanel.selectFloor(23);
        digitPanel.selectFloor(3);
        digitPanel.selectFloor(10);
        digitPanel.selectFloor(18);
        
        // Create new floors 

        for (int i = 20; i < digitPanel.getFloors(); i++) {
            Thread floor = new Thread(new Floor());
            floor.start();
        }

        // Create new elevator
        
        Thread elevatorThread = new Thread(new ElevatorSync(25, digitPanel));
        System.out.println("Elevator thread: " + elevatorThread.getName());
        
        // Ok, all guys are inside, let's go
        elevatorThread.start();
        
    }

}

