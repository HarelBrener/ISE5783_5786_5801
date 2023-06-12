package geometries;

import primitives.*;

import java.util.List;

/**
 * The Triangle class represents a triangle in 3D space defined by three vertices.
 * It extends the Polygon class, which represents a polygon in 3D space.
 */
public class Triangle extends Polygon {
    /** Constructs a Triangle by 3 points
     * @param p1 the first point
     * @param p2 the second point
     * @param p3 the third point
    * */
    public Triangle(Point p1, Point p2,Point p3)
    {
        super(p1,p2,p3);
    }

    /**
     Finds intersection points of a ray with a triangle in 3D space.
     If no intersection points exist, returns null.
     @param ray The ray to intersect with the triangle.
     @return A list of intersection Geooints (if they exist), otherwise null.
     */
    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        List<GeoPoint> l = plane.findGeoIntersectionsHelper(ray);
        if (l == null)
            return null;
        Point p = l.get(0).point;
        Point p0 = this.vertices.get(0);
        Point p1 = this.vertices.get(1);
        Point p2 = this.vertices.get(2);
        if(p.equals(p0)||p.equals(p1)||p.equals(p2))
            return null;
        Vector x1 = p1.subtract(p0);
        Vector x2 = p2.subtract(p1);
        Vector x3 = p0.subtract(p2);
        Vector y1 = p0.subtract(p);
        Vector y2 = p1.subtract(p);
        Vector y3 = p2.subtract(p);
        if(x1.normalize().equals(y1.normalize())||x1.normalize().scale(-1).equals(y1.normalize())
                ||(x2.normalize().equals(y2.normalize())||x2.normalize().scale(-1).equals(y2.normalize()))
                ||(x3.normalize().equals(y3.normalize())||x3.normalize().scale(-1).equals(y3.normalize())))
            return null;
        Vector n1 = x1.crossProduct(y1);
        Vector n2 = x2.crossProduct(y2);
        Vector n3 = x3.crossProduct(y3);
        if(n1.dotProduct(n2)>0 && n1.dotProduct(n3)>0 && n3.dotProduct(n2)>0)
            return List.of(new GeoPoint(this,p));
        return null;
    }
}
