package geometries;
import primitives.*;

import java.util.LinkedList;
import java.util.List;

public class Geometries {

    /**
     * The list of intersectable geometries in the collection.
     */
    private List<Intersectable> intrsc;

    /**
     * Constructs an empty collection of intersectable geometries.
     */
    public Geometries() {
        intrsc = new LinkedList<Intersectable>();
    }

    /**
     * Constructs a collection of intersectable geometries from an array of geometries.
     *
     * @param geometries An array of geometries to add to the collection.
     */
    public Geometries(Intersectable... geometries) {
        intrsc = new LinkedList<Intersectable>(List.of(geometries));
    }

    /**
     * Adds a list of geometries to the collection.
     *
     * @param geometries A list of geometries to add to the collection.
     */
    public void add(Intersectable... geometries) {
        for (int i = 0; i < geometries.length; i++) {
            intrsc.add(geometries[i]);
        }
    }

    /**
     * Filters a list of intersection points to remove duplicates.
     *
     * @param result The original list of intersection points.
     * @param fi     The new list of intersection points to check for duplicates.
     * @return The filtered list of intersection points, or null if no intersection points remain.
     */
    List<Point> filter(List<Point> result, List<Point> fi) {
        for (Point p : fi) {
            if (result.contains(p)) {
                if (fi.size() == 1)
                    return null;
                fi.remove(p);
            }
        }
        return fi;
    }

    /**
     * Finds the intersection points of a given ray with the geometries in the collection.
     *
     * @param ray The ray to intersect with the geometries.
     * @return A list of intersection points with the geometries, or null if no intersection points exist.
     */
    List<Point> findIntersections(Ray ray) {
        List<Point> result = null;
        for (Intersectable i : intrsc) {
            List<Point> fi = i.findIntsersections(ray);
            if (fi != null) {
                if (result == null)
                    result = new LinkedList<Point>(fi);
                else {
                    List<Point> l = this.filter(result, fi);
                    if (l != null)
                        result.addAll(l);
                }
            }
        }
        return result;
    }
}