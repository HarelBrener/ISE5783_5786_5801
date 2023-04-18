package geometries;

import primitives.Point;

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
    Triangle(Point p1, Point p2,Point p3)
    {

        super(p1,p2,p3);
    }
}
