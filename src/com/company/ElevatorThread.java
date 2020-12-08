package com.company;

import java.util.*;

public class ElevatorThread extends Thread {
    private int currentFloor;
    private int destination; //  amount of customer's destination
    private int capacity;
    private int countInside;
    private String direction;
    private boolean isWorking;
    private Queue<Integer> destinationFloorQueue = new LinkedList();
    private ArrayList<CustomerTransformationInfo> elevatorInside = new ArrayList<>();

    private final String UP = "UP"; // constant variable
    private final String DOWN = "DOWN"; // constant variable

    public ElevatorThread(int capacity) {
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
            checkElevatorQueues();
            if (!destinationFloorQueue.isEmpty())
                goToDestinationFloor();


            printElevator();

            /*if (destinationQueue.size() == 0) {
                //take customers
                takeCustomers();
            } else {
                // move customer between floors
            }


            */


        }

    }


    private void goToDestinationFloor() {
        //  go to given floor then opening elvator's doors and customers getting to them .
        int floor = destinationFloorQueue.peek();
        if (currentFloor == floor) {
            takeCustomerToElevatorInside(Main.getFloorsQueue(currentFloor));
            direction = "N";

        } else {
            direction = DOWN;
            //wait200ms();
            //currentFloor-=1;
            if (currentFloor > floor) {
                for (; currentFloor > floor; currentFloor--) {
                    //getOffElevator(currentFloor);
                    if (Main.elevatorQueue[currentFloor].size() != 0) {

                        takeCustomerToElevatorInside(Main.getFloorsQueue(currentFloor));

                    }
                    wait200ms();
                }
            }
            else{
                direction = UP;
                //wait200ms();
                //currentFloor+=1;
                for (; currentFloor < floor; currentFloor++) {
                   // getOffElevator(currentFloor);
                    if (Main.elevatorQueue[currentFloor].size() != 0) {
                        takeCustomerToElevatorInside(Main.getFloorsQueue(currentFloor));

                    }
                    wait200ms();
                }

            }

            takeCustomerToElevatorInside(Main.getFloorsQueue(currentFloor));
            getOffElevator(currentFloor);

        }
        destinationFloorQueue.remove();


    }

    private void takeCustomerToElevatorInside(Queue<CustomerTransformationInfo> queue) {
        // take a customers to elevator inside from queue
        if (queue.size() > 0) {
            CustomerTransformationInfo destination = queue.peek();
            int customerAmount = destination.amount;
            while (capacity >= customerAmount + countInside && queue.size() > 0) {
                elevatorInside.add(destination);
                countInside += customerAmount;
                if (queue.size() != 0 && destinationFloorIsNotExists(destination.floor)) {
                    destinationFloorQueue.add(queue.peek().floor);

                }
                queue.remove(); // removing head
                if (!queue.isEmpty()) {
                    destination = queue.peek();
                    customerAmount = destination.amount;
                }


            }
        }

    }

    private void getOffElevator(int floor) {
        if (elevatorInside.size() != 0) {

            for (int i = 0; i < elevatorInside.size(); i++
            ) {
                CustomerTransformationInfo customers = elevatorInside.get(i);
                if (customers.floor == floor) {
                    elevatorInside.remove(customers);
                    countInside -= customers.amount;
                    if (floor == 0) {
                        Main.increaseExitCount(customers.amount);// maindeki exit sayisini arttir.
                    } else {
                        Main.increaseFloorCustomersAmount(floor-1, customers.amount);

                    }
                }
            }
        }
    }

    private void checkElevatorQueues() {
        // this method select destination of elevator.
        //asansorun hedef katini belirler ve bunu kuyruga atar.
        if(destinationFloorQueue.size() == 0 ){
            destinationFloorQueue.add(0);
        }
        for (Queue<CustomerTransformationInfo> queue : Main.elevatorQueue) {
            if (queue.size() != 0 && destinationFloorIsNotExists(queue.peek().floor)) {
                destinationFloorQueue.add(queue.peek().floor);
                destination += 1;
                System.out.println(queue.peek().floor);
            }
        }
    }

    private boolean destinationFloorIsNotExists(Integer floor) {
        Iterator iter = destinationFloorQueue.iterator();
        if(destinationFloorQueue.size() == 0 ){
            return  true;
        }
        while (iter.hasNext()) {
            if (floor.equals(iter.next())) {
                return false;
            }
        }
        return true;
    }

    private void wait200ms() {
        try {
            Thread.sleep(200);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void printElevator() {
        Queue<CustomerTransformationInfo> list;
        System.out.println("***************************");
        for (int i = 0; i < 4; i++) {
            System.out.println((i + 2) + "all :" + Main.floorCustomersAmount[i]);
        }
        System.out.println("--------");
        System.out.println("\t floor:" + currentFloor);
        System.out.println("\t destinaton Amount:" + destinationFloorQueue.size());
        System.out.println("\t direction:" + direction);
        System.out.println("\t capacity:" + capacity);
        System.out.println("\t countInside:" + countInside);
        System.out.println("hedef kuyrugu : " + destinationFloorQueue);
        System.out.print("\t inside:[");

        for (CustomerTransformationInfo customer : elevatorInside) {
            System.out.print("(" + customer.floor + "," + customer.amount + ")");
        }
        System.out.println("]");
        System.out.println("--------");
        for (int i = 0; i < Main.elevatorQueue.length; i++
        ) {
            list = Main.elevatorQueue[i];
            System.out.println(i + ".kat : " + list);

        }
        System.out.println("***************************");
    }
}
