package org.VehicleShop.entity;


public class Vehicle {

    private Long vehicleId;
    private String title;
    private Engine engineId;
    private VehicleType vehicleType;

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Engine getEngineId() {
        return engineId;
    }

    public void setEngineId(Engine engineId) {
        this.engineId = engineId;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }
}
