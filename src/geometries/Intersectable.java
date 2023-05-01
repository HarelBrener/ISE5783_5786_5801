package geometries;
import primitives.*;
import java.util.List;

/**
 * The Intersectable interface represents a geometrical object that can intersect with a ray in 3D space.
 */
public interface Intersectable {

    /**
     * Finds the intersections of the specified ray with this Intersectable object in 3D space.
     *
     * @param ray  The ray to intersect with this Intersectable object.
     * @return     A list of intersection points between the specified ray and this Intersectable object,
     *             or an empty list if there are no intersections.
     */
    public List<Point> findIntsersections(Ray ray);
}
