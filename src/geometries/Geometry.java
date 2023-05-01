package geometries;
import primitives.*;

/**
 * The Geometry interface represents a geometrical object that can be intersected with a ray in 3D space,
 * and provides a method for calculating the normal vector at a specified point.
 */
public interface Geometry extends Intersectable {

    /**
     * Gets the normal vector to this Geometry object at the specified point in 3D space.
     *
     * @param p  The point to calculate the normal vector at.
     * @return   The normal vector to this Geometry object at the specified point in 3D space.
     */
    Vector getNormal(Point p);
}
