package primitives;

public class Point {

    // The coordinates of the point
    final Double3 xyz;

    /**
     * Constructs a point with coordinates given by a Double3 object.
     * @param p the Double3 object representing the coordinates of the point
     */
    Point(Double3 p) {
        xyz = p;
    }

    /**
     * Constructs a point with the given coordinates.
     * @param a the x-coordinate of the point
     * @param b the y-coordinate of the point
     * @param c the z-coordinate of the point
     */
    public Point(double a, double b, double c) {
        xyz = new Double3(a,b,c);
    }

    /**
     * Computes the vector between this point and another point.
     * @param p the other point
     * @return the vector between this point and the other point
     */
    public Vector subtract(Point p){
        Double3 subP = this.xyz.subtract(p.xyz);
        return new Vector(subP);
    }

    /**
     * Computes the point obtained by adding a vector to this point.
     * @param v the vector to add
     * @return the point obtained by adding the vector to this point
     */
    public Point add(Vector v){
        Double3 addV = this.xyz.add(v.xyz);
        return new Point(addV);
    }

    /**
     * Computes the square of the distance between two points.
     * @param p1 the first point
     * @param p2 the second point
     * @return the square of the distance between the two points
     */
    double distanceSquared(Point p1, Point p2){
        Point subP = p1.subtract(p2);
        return (subP.xyz.d1*subP.xyz.d1 + subP.xyz.d2*subP.xyz.d2 + subP.xyz.d3*subP.xyz.d3);
    }

    /**
     * Computes the distance between two points.
     * @param p1 the first point
     * @param p2 the second point
     * @return the distance between the two points
     */
    double distance(Point p1, Point p2){
        return Math.sqrt(distanceSquared(p1,p2));
    }
}