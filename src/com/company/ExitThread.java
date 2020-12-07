package com.company;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;


public class ExitThread implements Runnable {

    private Random random = new Random();

    private static Queue[] elevatorQueue = new LinkedList[4];

    int[] customerAmountOnFloors = Main.floorCustomersAmount;

    public ExitThread() {
        for (int i = 0; i < 4; i++) {
            Queue queue = new LinkedList();
            elevatorQueue[i] = queue;
        }

    }

    public static synchronized Queue[] getElevatorQueue() {
        // only one thread can be executed this method
        return elevatorQueue;
    }

    @Override
    public void run() {

        while (true) {

            int floorNumber = random.nextInt(4);
            int floor = Main.floorCustomersAmount[floorNumber];

            int customerAmount = floor >= 10 ? random.nextInt(10) + 1 : (floor != 0 ? random.nextInt(floor) + 1 : 0);

            if (customerAmount != 0) {

                Main.addElevatorQueue(customerAmount,floorNumber+1,0);
                Main.floorCustomersAmount[floorNumber] -= customerAmount;
            }


            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //for test
            /*
            if (floorNumber == 0)
                System.out.println(elevatorQueue[floorNumber]);*/
        }
    }
}


