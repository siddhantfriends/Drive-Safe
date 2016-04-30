package com.safe.drive;

/**
 * Created by Milton on 30/04/2016.
 */
public class Vehicle {
    private String plateNumber;
    private String engineNumber;
    private boolean isEngineOn;

    public Vehicle(String plateNumber, String engineNumber) {
        this(plateNumber, engineNumber, false);
    }

    public Vehicle(String plateNumber, String engineNumber, boolean isEngineOn) {
        this.plateNumber = plateNumber;
        this.engineNumber = engineNumber;
        this.isEngineOn = isEngineOn;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public String getEngineNumber() {
        return engineNumber;
    }

    public boolean isEngineOn() {
        return isEngineOn;
    }

    public void setIsEngineOn(boolean isEngineOn) {
        this.isEngineOn = isEngineOn;
    }
}
