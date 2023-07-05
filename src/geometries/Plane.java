package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;

import java.util.List;
import primitives.Util.*;

public class Plane extends Geometry {

    // The base point of the plane
    final Point q0;

    // The normal vector of the plane
    final Vector normal;

    /**
     * Constructs a plane with a base point and a normal vector.
     *
     * @param q0     the base point of the plane
     * @param normal the normal vector of the plane
     */
    public Plane(Point q0, Vector normal) {
        this.q0 = q0;
        this.normal = normal.normalize();
    }

    /**
     * Constructs a plane from three points.
     *
     * @param p1 the first point on the plane
     * @param p2 the second point on the plane
     * @param p3 the third point on the plane
     */
    public Plane(Point p1, Point p2, Point p3) {
        if (p1.equals(p2) || p1.equals(p3) || p2.equals(p3)) { // check if two points are equal
            throw new IllegalArgumentException("ERROR: at least two from the points are the same");
        }
        Vector v1 = (p1.subtract(p2)).normalize();
        Vector v2 = (p2.subtract(p3)).normalize();
        if (v1.equals(v2) || (v1.scale(-1)).equals(v2)) { // check if all the points on the same line
            throw new IllegalArgumentException("ERROR: all the points are on the same line");
        }
        normal = (v1.crossProduct(v2)).normalize();
        q0 = p1;
    }

    /**
     * Returns the base point of the plane.
     *
     * @return the base point of the plane
     */
    public Point getQ0() {
        return q0;
    }

    /**
     * Returns the normal vector of the plane.
     *
     * @return the normal vector of the plane
     */
    public Vector getNormal() {
        return normal;
    }

    /**
     * Returns the normal vector of the plane at a specific point.
     * Since the plane is infinite, the normal vector is the same at all points.
     *
     * @param p a point on the plane
     * @return the normal vector of the plane
     */
    @Override
    public Vector getNormal(Point p) {

        return normal;
    }

    /**
     * Finds the intersections of the specified ray with this Plane object in 3D space.
     *
     * @param ray  The ray to intersect with this Plane object.
     * @return     A list of intersection points between the specified ray and this Plane object,
     *             or null if there are no intersections.
     */
    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        double nv = ray.getDir().dotProduct(normal);
        if (Util.isZero(nv))
            return null;
        if (ray.getP0().equals(q0)) {
            return null;
        }
        double t = (normal.dotProduct(q0.subtract(ray.getP0())) / nv);
        if (t > 0) {
            Point p = ((ray.getP0()).add((ray.getDir().scale(t))));
            return List.of(new GeoPoint(this,p));
        }
        return null;
    }
}
