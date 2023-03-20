package primitives;

public class Point {
    final Double3 xyz;

    Point(Double3 p) {
        xyz = p;
    }
    public Point(double a, double b, double c) {
        xyz = new Double3(a,b,c);
    }
    Vector subtract(Point p){
        Double3 subP = this.xyz.subtract(p.xyz);
        return new Vector(subP);
    }
    Point add(Vector v){
        Double3 addV = this.xyz.add(v.xyz);
        return new Point(addV);
    }
    double distanceSquared(Point p1, Point p2){
        Point subP = p1.subtract(p2);
        return (xyz.d1*xyz.d1 + xyz.d2*xyz.d2 + xyz.d3*xyz.d3);
    }
    double distance(Point p1, Point p2){
        return Math.sqrt(distanceSquared(p1,p2));
    }
}
