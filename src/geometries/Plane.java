package geometries;

import primitives.Point;
import primitives.Vector;

public class Plane implements Geometry{

    // The base point of the plane
    final Point q0;

    // The normal vector of the plane
    final Vector normal;

    /**
     * Constructs a plane with a base point and a normal vector.
     * @param q0 the base point of the plane
     * @param normal the normal vector of the plane
     */
    public Plane(Point q0, Vector normal) {
        this.q0 = q0;
        this.normal = normal.normalize();
    }

    /**
     * Constructs a plane from three points.
     * @param p1 the first point on the plane
     * @param p2 the second point on the plane
     * @param p3 the third point on the plane
     */
    public Plane (Point p1, Point p2, Point p3){
        if (p1.equals(p2) || p1.equals(p3) || p2.equals(p3)){ // check if two points are equal
            throw new IllegalArgumentException("ERROR: at least two from the points are the same");
        }
        Vector v1 = (p1.subtract(p2)).normalize();
        Vector v2 = (p2.subtract(p3)).normalize();
        if (v1.equals(v2) || (v1.scale(-1)).equals(v2)){ // check if all the points on the same line
            throw new IllegalArgumentException("ERROR: all the points are on the same line");
        }
        normal = (v1.crossProduct(v2)).normalize();
        q0 = p1;
    }

    /**
     * Returns the base point of the plane.
     * @return the base point of the plane
     */
    public Point getQ0() {
        return q0;
    }

    /**
     * Returns the normal vector of the plane.
     * @return the normal vector of the plane
     */
    public Vector getNormal() {
        return normal;
    }

    /**
     * Returns the normal vector of the plane at a specific point.
     * Since the plane is infinite, the normal vector is the same at all points.
     * @param p a point on the plane
     * @return the normal vector of the plane
     */
    @Override
    public Vector getNormal(Point p) {
        return normal;
    }

}