package sample;

public class Movement {

    // This class is Movement class for Spawnables.
    private double dx;
    private double dy;
    private double speed;

    public Movement(double dx , double dy, double speed) {
        this.dx = dx;
        this.dy = dy;
        this.speed = speed;
    }

    double getSpeed(){
        return speed;
    }

}
