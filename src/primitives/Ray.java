package primitives;

import java.util.List;
import geometries.Intersectable.GeoPoint;
import primitives.*;

import static primitives.Util.alignZero;

/**
 * Represents a ray in a 3D space.
 * A ray is defined by a starting point and a direction vector.
 */
public class Ray {

    private static final double DELTA = 0.1;

    /** The starting point of the ray */
    final Point p0;

    /** The direction vector of the ray */
    final Vector dir;

    /**
     * Constructs a ray with the specified starting point and direction vector.
     * The direction vector is normalized to have a length of 1.
     * @param p The starting point of the ray.
     * @param v The direction vector of the ray.
     */
    public Ray(Point p, Vector v) {
        p0 = p;
        dir = v.normalize();
    }

    public Ray(Point head, Vector direction, Vector normal){
        double nl = alignZero(normal.dotProduct(direction));
        if(nl == 0)
            this.p0 = head;
        else
            p0 = head.add(normal.scale(nl < 0 ? -DELTA: DELTA));
        dir = direction.normalize();
    }

    /**
     * Returns the starting point of the ray.
     * @return The starting point of the ray.
     */
    public Point getP0(){
        return  p0;
    }

    /**
     * Returns the direction vector of the ray.
     * @return The direction vector of the ray.
     */
    public Vector getDir() {
        return dir;
    }

    /**
     * compares between the rays and returns the answer
     * @param  o the object to compare
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ray ray)) return false;
        return this.p0.equals(((Ray) o).p0) && this.dir.equals(((Ray) o).dir) ;
    }
    /**

     Finds the closest point from a given list of points.
     @param points the list of points from which to find the closest point
     @return the closest point from the list, or null if the list is empty
     */
    public Point findClosestPoint(List<Point> points) {
        return points == null || points.isEmpty() ? null
                : findClosestGeoPoint(points.stream().map(p -> new GeoPoint(null, p)).toList()).point;
    }


    public GeoPoint findClosestGeoPoint(List<GeoPoint> list){
        if(list == null)
            return null;
        double minD = getP0().distance(list.get(0).point);//min distance (for the "for")
        GeoPoint minDP = list.get(0);//Point with min distance from P0
        for (GeoPoint gp : list){
            double temp = gp.point.distance(getP0());
            if (temp<minD) {
                minD = gp.point.distance(getP0());
                minDP = gp;
            }
        }
        return minDP;
    }
}