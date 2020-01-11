package org.VehicleShop.entity;


public class Vehicle {

    private Long vehicleId;
    private String title;
    private Engine engineId;
    private VehicleType vehicleTypeId;

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

    public VehicleType getVehicleTypeId() {
        return vehicleTypeId;
    }

    public void setVehicleTypeId(VehicleType vehicleTypeId) {
        this.vehicleTypeId = vehicleTypeId;
    }
}
