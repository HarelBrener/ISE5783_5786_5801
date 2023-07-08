package primitives;

import geometries.Intersectable;
import lighting.LightSource;

import java.util.Objects;
import java.util.Random;

/**
 * Represents a point in a 3D space by three coordinates.
 */
public class Point {

    // The coordinates of the point
    final Double3 xyz;

    /** Zero triad (0,0,0) */
    public static final Point ZERO = new Point(0, 0, 0);

    /**
     * Constructs a point with coordinates given by a Double3 object.
     * @param p the Double3 object representing the coordinates of the point
     */
    Point(Double3 p) {
        xyz = p;
    }

    /**
     * Gets the X coordinate of this point.
     * @return The X coordinate of this point.
     */
    public double getX(){
        return xyz.d1;
    }

    /**
     * Gets the Y coordinate of this point.
     * @return The Y coordinate of this point.
     */
    public double getY(){
        return xyz.d2;
    }

    /**
     * Gets the Z coordinate of this point.
     * @return The Z coordinate of this point.
     */
    public double getZ(){
        return xyz.d3;
    }

    /**
     * Constructs a point with the given coordinates.
     *
     * @param a the x-coordinate of the point
     * @param b the y-coordinate of the point
     * @param c the z-coordinate of the point
     */
    public Point(double a, double b, double c) {
        xyz = new Double3(a, b, c);
    }

    /**
     * Computes the vector between this point and another point.
     *
     * @param p the other point
     * @return the vector between this point and the other point
     */
    public Vector subtract(Point p) {
        Double3 subP = this.xyz.subtract(p.xyz);
        return new Vector(subP);
    }

    /**
     * Computes the point obtained by adding a vector to this point.
     *
     * @param v the vector to add
     * @return the point obtained by adding the vector to this point
     */
    public Point add(Vector v) {
        Double3 addV = this.xyz.add(v.xyz);
        return new Point(addV);
    }

    /**
     * Computes the square of the distance between accepted point and the exited point.
     *
     * @param p1 the accepted point
     * @return the square of the distance between the two points
     */
    public double distanceSquared(Point p1) {
        Point subP = p1.subtract(this);
        return (subP.xyz.d1 * subP.xyz.d1 + subP.xyz.d2 * subP.xyz.d2 + subP.xyz.d3 * subP.xyz.d3);
    }

    /**
     * Computes the distance between accepted point and the exited point.
     *
     * @param p1 the accepted point
     * @return the distance between the two points
     */
    public double distance(Point p1) {
        return Math.sqrt(distanceSquared(p1));
    }

    /**
     * compares between the points and returns the answer
     * @param  o the object to compare
     * **/
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Point point)) return false;
        return this.xyz.equals(((Point) o).xyz);
    }

    /*public Point randomPoint(Vector l,double lenght, double widght /*double r){
        Vector vRight = new Vector(l.getY()*-1,l.getX(),0).normalize();
        Vector vUp = l.crossProduct(vRight).normalize();
        Random rand = new Random();
        double k,t; // k -- vUp, t -- vRight
        k = rand.nextDouble(-r,r);
        double bound = Math.sqrt(r*r-k*k);
        t = rand.nextDouble( -bound ,bound);
        return this.add(vRight.scale(k)).add(vUp.scale(t));
    }*/
}
