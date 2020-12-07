package com.company;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Elevator extends Thread {
    private int currentFloor;
    private int destination; //  amount of customer's destination
    private int capacity;
    private int countInside;
    private String direction;
    private boolean isWorking;
    private Queue<CustomerTransformationInfo> destinationQueue = new LinkedList();


    private final String UP = "UP"; // constant variable
    private final String DOWN = "DOWN"; // constant variable

    public Elevator(int capacity) {
        currentFloor = 0;
        destination = 0;
        this.capacity = capacity;
        countInside = 0;
        direction = "N";
        isWorking = false;
    }

    // theard start running
    @Override
    public void run() {
        //super.run();

        while (true) {

            if (destinationQueue.size() == 0) {
                //take customers
                takeCustomers();
            } else {
                // move customer between floors
            }


            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    private void addElevatorDestination(Queue<CustomerTransformationInfo> queue) {
        if (queue.size() > 0) {
            CustomerTransformationInfo destination = queue.peek();
            int customerAmount = destination.amount;
            while (capacity >= customerAmount + countInside && queue.size() > 0) {
                destinationQueue.add(destination);
                countInside += customerAmount;
                queue.remove(); // removing head
                destination = queue.peek();
                customerAmount = destination.amount;
            }
        }

    }

    private void takeCustomers() {
        if (currentFloor == 0) {
            goToFloor(0);
            direction = UP ;
        }
        else {
            for(int i=4;i>0;i--){
                goToFloor(i);
            }
        }
    }
    private void goToFloor(int floor){
        //  go to given floor then opening elvator's doors and customers getting to them .
        if(currentFloor > floor){
            direction = DOWN;
        }
        else{
            direction = UP;
        }
        currentFloor = floor;
        addElevatorDestination(Main.getFloorsQueue(floor));

    }
}
