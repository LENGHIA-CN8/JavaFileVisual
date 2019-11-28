package analyst;
public class Point {
    private double x;
    private double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() { return x; }
    public void   setX(double x) { this.x = x; }
    public double getY() { return y; }
    public void   setY(double y) { this.y = y; }

    public double distance(Point other) {
        return Math.sqrt((x - other.x) * (x - other.x) + (y - other.y) * (y - other.y));
    }

    public String toString() {
        return "(" + String.format("%.1f", x) + "," + String.format("%.1f", y) + ")";
    }

    public boolean equals(Object o) {
        if (!(o instanceof Point)) return false;
        if (this == o) return true;
        Point _o = (Point) o;
        return Math.abs(x - _o.getX()) <= 0.001 &&
                Math.abs(y - _o.getY()) <= 0.001;
    }
}