package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * Represents a sphere in a 3D space.
 * A sphere is defined by a center point and a radius.
 */
public class Sphere extends RadialGeometry {

    /** The center point of the sphere */
    final Point center;

    /**
     * Constructs a sphere with the specified radius and center point.
     * @param r The radius of the sphere.
     * @param center The center point of the sphere.
     */
    public Sphere(double r, Point center) {
        super(r);
        this.center = center;
    }

    /**
     * Returns the center point of the sphere.
     * @return The center point of the sphere.
     */
    public Point getCenter() {
        return center;
    }

    /**
     * Returns the radius of the sphere.
     * @return The radius of the sphere.
     */
    public double getRadius() {
        return radius;
    }

    /**
     * Returns the normal vector to the sphere at a given point.
     * For a sphere, the normal vector is simply the vector from the center of the sphere
     * to the given point, normalized to have a length of 1.
     * @param p The point on the sphere for which to compute the normal vector.
     * @return The normal vector to the sphere at the given point.
     */
    @Override
    public Vector getNormal(Point p) {
        // The normal vector to a sphere is simply the normalized vector from the center
        // of the sphere to the given point.
        return p.subtract(center).normalize();
    }
}