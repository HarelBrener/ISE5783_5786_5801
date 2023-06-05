package geometries;
import primitives.*;
import java.util.List;
import java.util.Objects;

/**
 * The Intersectable interface represents a geometrical object that can intersect with a ray in 3D space.
 */
public abstract class Intersectable{

    /**
     * Represents a geographical point with associated geometry and coordinates.
     */
    public static class GeoPoint {

        /**
         * The geometry of the geographical point.
         */
        public Geometry geometry;

        /**
         * The coordinates of the geographical point.
         */
        public Point point;

        /**
         * Creates a new GeoPoint object with the specified geometry and coordinates.
         *
         * @param geometry The geometry associated with the geographical point.
         * @param point    The coordinates of the geographical point.
         */
        public GeoPoint(Geometry geometry, Point point) {
            this.geometry = geometry;
            this.point = point;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof GeoPoint geoPoint)) return false;
            return this.geometry.equals(((GeoPoint) o).geometry) && this.point.equals(((GeoPoint) o).point);
        }

        @Override
        public String toString() {
            return "GeoPoint{" +
                    "geometry=" + geometry +
                    ", point=" + point +
                    '}';
        }
    }

    /**
     * Finds the intersections of the specified ray with this Intersectable object in 3D space.
     *
     * @param ray  The ray to intersect with this Intersectable object.
     * @return     A list of intersection points between the specified ray and this Intersectable object,
     *             or an empty list if there are no intersections.
     */
    public List<Point> findIntersections(Ray ray) {
        var geoList = findGeoIntersections(ray);
        return geoList == null ? null : geoList.stream().map(gp -> gp.point).toList();
    }
    public final List<GeoPoint> findGeoIntersections(Ray ray){
        return findGeoIntersectionsHelper(ray);
    }

    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray){
        return List.of(null);
    }
}
