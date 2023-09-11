package com.zobus.pojo;

public class AddBusInput {

    private int busUID;
    private String busType;
    private String seatType;
    private int seatCapacity;
    private double fare;

    public int getBusUID() {
        return busUID;
    }

    public void setBusUID(int busUID) {
        this.busUID = busUID;
    }
    public String getBusType() {
        return busType;
    }

    public void setBusType(String busType) {
        this.busType = busType;
    }

    public String getSeatType() {
        return seatType;
    }

    public void setSeatType(String seatType) {
        this.seatType = seatType;
    }

    public int getSeatCapacity() {
        return seatCapacity;
    }

    public void setSeatCapacity(int seatCapacity) {
        this.seatCapacity = seatCapacity;
    }

    public double getFare() {
        return fare;
    }

    public void setFare(double fare) {
        this.fare = fare;
    }
}
