package ThoughtWorks.entry;

public class Signal {
    private final String droneId;
    private Drone.Location origin;
    private Drone.Location offset;
    private boolean isError; //是否出错

    protected Signal() {
        droneId = "";
    }

    public Signal(String droneId) {
        this.droneId = droneId;
    }

    public Signal(String droneId, int orix, int oriy, int oriz) {
        this.droneId = droneId;
        this.origin = new Drone.Location(orix, oriy, oriz);
    }

    public Signal(String droneId, int orix, int oriy, int oriz, int offsetx, int offsety, int offsetz) {
        this.droneId = droneId;
        this.origin = new Drone.Location(orix, oriy, oriz);
        this.offset = new Drone.Location(offsetx, offsety, offsetz);
    }

    public String getDroneId() {
        return droneId;
    }

    public int getOriginX() {
        return origin.getX();
    }

    public int getOriginY() {
        return origin.getY();
    }

    public int getOriginZ() {
        return origin.getZ();
    }

    public void setOrigin(int x, int y, int z) {
        if (this.origin == null)
            this.origin = new Drone.Location(x, y, z);
        else
            this.origin.setCoordinary(x, y, z);
    }

    public int getOffsetX() {
        return offset.getX();
    }

    public int getOffsetY() {
        return offset.getY();
    }

    public int getOffsetZ() {
        return offset.getZ();
    }

    public void setOffset(int x, int y, int z) {
        if (this.offset == null)
            this.offset = new Drone.Location(x, y, z);
        else
            this.offset.setCoordinary(x, y, z);
    }

    public boolean isError() {
        return isError;
    }

    public void setError(boolean error) {
        isError = error;
    }
}
