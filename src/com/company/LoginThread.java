package com.company;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class LoginThread implements Runnable {

    private Random random = new Random();

    private static Queue<HashMap<Integer, Integer>> customers = new LinkedList<>(); //gelen musterileri tutan kuyruk

    public static synchronized Queue<HashMap<Integer, Integer>> getCustomers() {
        // only one thread can be execute this method
        return customers;
    }

    @Override
    public void run() {

        while (true){

            int floorNumber = random.nextInt(5) + 1;

            int customerAmount = random.nextInt(10) + 1;
            Main.addElevatorQueue(customerAmount,0,floorNumber);

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // System.out.println(customers);

        }

    }
}
