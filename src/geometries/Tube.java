package geometries;

import primitives.*;

/**
 * The Tube class represents a tube in 3D space defined by a radius and an axis ray.
 */
public class Tube extends RadialGeometry {

    /**
     * The axis ray of the tube.
     */
    final Ray axisRay;

    /**
     * Constructs a new tube with the specified radius and axis ray.
     *
     * @param r        The radius of the tube.
     * @param axisRay  The axis ray of the tube.
     */
    public Tube(double r, Ray axisRay) {
        super(r);
        this.axisRay = axisRay;
    }

    /**
     * Gets the normal vector to the tube at the specified point.
     *
     * @param p  The point to calculate the normal at.
     * @return   The normal vector to the tube at the specified point.
     */
    @Override
    public Vector getNormal(Point p) {
        if ((p.subtract(axisRay.getP0())).dotProduct(axisRay.getDir()) == 0){ //p-P0 and dir are orthogonal
            return p.subtract(axisRay.getP0());//P-P0 is the normal
        }
        double t = ((p.subtract(axisRay.getP0())).dotProduct(axisRay.getDir()));
        Point o = (axisRay.getP0()).add((axisRay.getDir()).scale(t));
        return ((p.subtract(o)).normalize());
    }
}
