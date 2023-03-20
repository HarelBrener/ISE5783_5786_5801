package geometries;

import primitives.Point;
import primitives.Vector;

public class Sphere extends RadialGeometry{

    final Point center;

    final double radius;

    public Sphere(double r, Point center, double radius) {
        super(r);
        this.center = center;
        this.radius = radius;
    }

    public Point getCenter() {
        return center;
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public Vector getNormal(Point p) {
        return null;
    }
}
