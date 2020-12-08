package com.company;


import java.util.HashMap;
import java.util.LinkedList;

import java.util.Map;
import java.util.Queue;

public class Main {
    public static int[] floorCustomersAmount = new int[]{0, 0, 0, 0};
    public static Queue<CustomerTransformationInfo>[] elevatorQueue = new LinkedList[5];
    public static int exitCount =0 ;
    private static Object lock1 = new Object();
    private static Object lock2 = new Object();
    private static Object lock3 = new Object();
    private static Object lock4 = new Object();
    private static Object lock5 = new Object();
    private static Object lock6 = new Object();
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
        ElevatorThread elevatorThread1 = new ElevatorThread(10);
        loginThread.start();
        exitThread.start();
        elevatorThread1.start();




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
    public static void increaseExitCount(int amount){
        synchronized (lock3){
            exitCount+= amount;
        }
    }
    public static void increaseFloorCustomersAmount(int floor,int amount ) {
        synchronized (lock4){
            floorCustomersAmount[floor] += amount;
        }
    }
    public static void decreaseFloorCustomersAmount(int floor,int amount ) {
        synchronized (lock5){
            floorCustomersAmount[floor] -= amount;
        }
    }

}
