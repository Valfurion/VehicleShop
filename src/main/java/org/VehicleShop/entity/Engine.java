package org.VehicleShop.entity;

public class Engine {
    private Long engineId;
    private String title;
    private String volume;
    private Long engineTypeId;

    public Long getEngineId() {
        return engineId;
    }

    public void setEngineId(Long engineId) {
        this.engineId = engineId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public Long getEngineTypeId() {
        return engineTypeId;
    }

    public void setEngineTypeId(Long engineTypeId) {
        this.engineTypeId = engineTypeId;
    }
}
