package com.company;

public class CustomerTransformationInfo {
    public int amount ;
    public int floor;

    public CustomerTransformationInfo(int amount, int floor) {
        this.amount = amount;
        this.floor = floor;
    }

    @Override
    public String toString() {
        return amount + "-" + floor;
    }
}
