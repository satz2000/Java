package com.zobus.pojo;

public class UpdateBusFareInput {
    private double fare;
    private int busUID;

    public int getBusUID() {
        return busUID;
    }

    public void setBusUID(int busUID) {
        this.busUID = busUID;
    }

    public double getFare() {
        return fare;
    }
    public void setFare(double fare) {
        this.fare = fare;
    }
}
