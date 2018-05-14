package sample;

public class Location {
    private double x;
    private double y;
    public Location(double xx,double yy){
        x=xx;
        y=yy;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    // This methods returns the distance between wanted coordinations.
    double getDistance(Location a, Location b)
    {
        double x_square = (a.x - b.x) * (a.x - b.x);
        double y_square = (a.y - b.y) * (a.y - b.y);
        return Math.sqrt(x_square + y_square);
    }
}
