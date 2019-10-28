package simple;

public class Alert {
    private Conveyors conveyor;
    private Long time ;
    private String status;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private String description;

    public Conveyors getConveyor() {
        return conveyor;
    }

    public void setConveyor(Conveyors conveyor) {
        this.conveyor = conveyor;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
