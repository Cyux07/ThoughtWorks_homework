package ThoughtWorks.entry;

public class Drone {
    private final String id;
    volatile private Location location;

    public Drone(String id) {
        this.id = id;
    }

    public Drone(String id, int x, int y, int z) {
        this.id = id;
        this.location = new Location(x, y, z);
    }

    public String getId() {
        return id;
    }

    public int getX() {
        return location.x;
    }

    public int getY() {
        return location.x;
    }

    public int getZ() {
        return location.x;
    }

    public void setLocation(int x, int y, int z) {
        synchronized (this) {
            this.location.setX(x);
            this.location.setY(y);
            this.location.setZ(z);
        }
    }

    public boolean equalsLocation(int x, int y, int z) {
        return location.x == x
                && location.y == y
                && location.z == z;
    }
    public boolean equalsLocation(Location location) {
        return this.location.equals(location);
    }

    static class Location {
        private int x, y, z;

        Location() {
        }

        Location(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        public int getX() {
            return x;
        }

        void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        void setY(int y) {
            this.y = y;
        }

        public int getZ() {
            return z;
        }

        void setZ(int z) {
            this.z = z;
        }

        void setCoordinary(int x, int y, int z) {
            setX(x);
            setY(y);
            setZ(z);
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Location) {
                Location other = (Location) obj;
                return this.x == other.x
                        && this.y == other.y
                        && this.z == other.z;
            }

            return false;
        }
    }
}
