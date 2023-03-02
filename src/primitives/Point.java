package primitives;

public class Point {
    final Double3 P;

    public Point(Double3 p) {
        P = p;
    }

    Point(double a, double b, double c) {
        P = new Double3(a,b,c);
    }

    Vector subtract(Point p){
        Double3 subP = this.P.subtract(p.P);
        return new Vector(subP);
    }

    Point add(Vector v){
        Double3 addV = this.P.add(v.P);
        return new Vector(addV);
    }

    double distanceSquared(Point p1, Point p2){
        Point subP = p1.subtract(p2);
        return (P.d1*P.d1 + P.d2*P.d2 + P.d3*P.d3);
    }

    double distance(Point p1, Point p2){
        return math.sqrt(distanceSquared(p1, p2));
    }
}
