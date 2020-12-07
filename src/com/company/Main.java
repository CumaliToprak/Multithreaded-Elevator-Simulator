package com.company;


import java.util.HashMap;
import java.util.LinkedList;

import java.util.Map;
import java.util.Queue;

public class Main {
    public static int[] floorCustomersAmount = new int[]{12, 32, 14, 23};
    public static Queue<CustomerTransformationInfo>[] elevatorQueue = new LinkedList[5];
    private static Object lock1 = new Object();
    private static Object lock2 = new Object();
    /* this queue array holds 5 queue for customer transportation
     *   first index (0) : 1. flor
     *   second index(1) : 2. flor
     *           .
     *           .
     *           .
     *   fifth index(4) : 5.flor */

    public static void main(String[] args) {
        for(int i =0 ;i<5;i++){
            elevatorQueue[i] =new LinkedList<>();
        }

        Thread loginThread = new Thread(new LoginThread());
        Thread exitThread = new Thread(new ExitThread());
        loginThread.start();
        exitThread.start();

        while (true) {
            System.out.println("------");

            for (Queue list : elevatorQueue
            ) {
                System.out.println(list);

            }

            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }
    }

    public static void addElevatorQueue (int customerAmount, int currentFloor, int destinationFloor) {
        synchronized (lock1){

            elevatorQueue[currentFloor].add(new CustomerTransformationInfo(customerAmount,destinationFloor));
        }

    }
    public static  Queue<CustomerTransformationInfo> getFloorsQueue(int index ){
        synchronized (lock2){
         return elevatorQueue[index];
        }
    }
}
