package geometries;

import primitives.*;
import static primitives.Util.*;
import java.util.List;
import java.lang.Math;

/**
 * Represents a sphere in a 3D space.
 * A sphere is defined by a center point and a radius.
 */
public class Sphere extends RadialGeometry {

    /**
     * The center point of the sphere
     */
    final Point center;


    /**
     * Constructs a sphere with the specified radius and center point.
     *
     * @param r      The radius of the sphere.
     * @param center The center point of the sphere.
     */
    public Sphere(double r, Point center) {
        super(r);
        this.center = center;
    }

    /**
     * Returns the center point of the sphere.
     *
     * @return The center point of the sphere.
     */
    public Point getCenter() {
        return center;
    }

    /**
     * Returns the radius of the sphere.
     *
     * @return The radius of the sphere.
     */
    public double getRadius() {
        return radius;
    }

    /**
     * Returns the normal vector to the sphere at a given point.
     * For a sphere, the normal vector is simply the vector from the center of the sphere
     * to the given point, normalized to have a length of 1.
     *
     * @param p The point on the sphere for which to compute the normal vector.
     * @return The normal vector to the sphere at the given point.
     */
    @Override
    public Vector getNormal(Point p) {
        // The normal vector to a sphere is simply the normalized vector from the center
        // of the sphere to the given point.
        return p.subtract(center).normalize();
    }

    /**
     * Finds the intersections of the specified ray with this Sphere object in 3D space.
     *
     * @param ray The ray to intersect with this Sphere object.
     * @return A list of intersection GeoPoints between the specified ray and this Sphere object,
     * or null if there are no intersections.
     */

    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        //Point p1 = null, p2 = null;
        if (ray.getP0().equals(center)) {
            return List.of(new GeoPoint(this, center.add(ray.getDir().scale(radius))));
        }
        Vector u = center.subtract(ray.getP0());
        double tm = u.dotProduct(ray.getDir());
        double d = Math.sqrt((u.length() * u.length()) - (tm * tm));
        if (d >= radius)
            return null;
        double th = Math.sqrt(radius * radius - d * d);
        double t1 = alignZero(tm - th);
        double t2 = alignZero(tm + th);
        Point p1, p2;
        try {
            if (t1 > 0) {
                p1 = ray.getP0().add(ray.getDir().scale(t1));
                if (t2 > 0) {
                    p2 = ray.getP0().add(ray.getDir().scale(t2));
                    return List.of(new GeoPoint(this, p1), new GeoPoint(this, p2));
                }
                return List.of(new GeoPoint(this, p1));
            }
            if (t2 > 0) {
                p2 = ray.getP0().add(ray.getDir().scale(t2));
                return List.of(new GeoPoint(this, p2));
            }
        }catch (Exception e){}
        return null;
    }


    /*@Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        //double t = 0;
        //Point p = ray.getP0().add(ray.getDir().scale(t));
        if (this.center.equals(ray.getP0())) { // if the p0 point of the ray is the center point of the sphere
            Point p = ray.getP0().add(ray.getDir().scale(this.radius));
            return List.of(new GeoPoint(this, p));
        } else {
            Vector u = this.center.subtract(ray.getP0());
            double tm = ray.getDir().dotProduct(u);
            double d = Math.sqrt(u.lengthSquared() - tm * tm);
            if (d >= this.radius)
                return null;
            else {
                double th = Math.sqrt((this.radius * this.radius) - (d * d));
                double t1 = alignZero(tm - th);
                double t2 = alignZero(tm + th);
                Point p1, p2;
                if (t1 <= 0 && t2 <= 0)
                    return null;
                else if (t1 > 0 && t2 <= 0) {
                    p1 = ray.getP0().add(ray.getDir().scale(t1));

                    return List.of(new GeoPoint(this, p1));
                } else if (t1 <= 0 && t2 > 0) {
                    p2 = ray.getP0().add(ray.getDir().scale(t2));
                    return List.of(new GeoPoint(this, p2));
                } else {
                    p1 = ray.getP0().add(ray.getDir().scale(t1));
                    p2 = ray.getP0().add(ray.getDir().scale(t2));
                    return List.of(new GeoPoint(this, p1), new GeoPoint(this, p2));
                }
            }
        }
    }*/
}