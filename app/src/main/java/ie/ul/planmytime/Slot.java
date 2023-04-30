package ie.ul.planmytime;

public class Slot {
    private String module;
    private String location;
    private String slotType;
    private String slotTime;


    public Slot(String module, String location, String slotType, String slotTime) {
        this.module = module;
        this.location = location;
        this.slotType = slotType;
        this.slotTime = slotTime;
    }

    public String getModule() {
        return module;
    }

    public String getLocation() {
        return location;
    }

    public String getType() {
        return slotType;
    }

    public String getTime() {
        return slotTime;
    }
}