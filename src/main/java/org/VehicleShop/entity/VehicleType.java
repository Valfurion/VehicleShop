package org.VehicleShop.entity;

import java.util.Objects;

public class VehicleType {
    private Long VehicleTypeId;
    private String VehicleTypeTitle;

    public Long getVehicleTypeId() {
        return VehicleTypeId;
    }

    public void setVehicleTypeId(Long vehicleTypeId) {
        VehicleTypeId = vehicleTypeId;
    }

    public String getVehicleTypeTitle() {
        return VehicleTypeTitle;
    }

    public void setVehicleTypeTitle(String vehicleTypeTitle) {
        VehicleTypeTitle = vehicleTypeTitle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VehicleType that = (VehicleType) o;
        return Objects.equals(VehicleTypeId, that.VehicleTypeId) &&
                Objects.equals(VehicleTypeTitle, that.VehicleTypeTitle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(VehicleTypeId, VehicleTypeTitle);
    }
}
