package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

public class Cylinder extends Tube {

    // The height of the cylinder
    double height;

    /**
     * Constructs a cylinder with a radius, axis ray, and height.
     * @param r the radius of the cylinder
     * @param axisRay the axis ray of the cylinder
     * @param height the height of the cylinder
     */
    public Cylinder(double r, Ray axisRay, double height) {
        super(r, axisRay);
        this.height = height;
    }

    public Vector getNormal(Point p) {
        if ((p.equals(axisRay.getP0()))){ // p = P0
            return ((axisRay.getDir()).scale(-1));
        }
        if (p.equals(((axisRay.getP0()).add((axisRay.getDir()).scale(height))))){ //p = the center of the other base
            return axisRay.getDir();
        }
        if ((p.subtract(axisRay.getP0())).dotProduct(axisRay.getDir()) == 0){ //p-P0 and dir are orthogonal
            return ((axisRay.getDir()).scale(-1));
        }
        if ((p.subtract((axisRay.getP0()).add((axisRay.getDir()).scale(height)))).dotProduct(axisRay.getDir()) == 0){ //(p - the center of the other base) and dir are orthogonal
            return axisRay.getDir();
        }
        double t = ((p.subtract(axisRay.getP0())).dotProduct(axisRay.getDir()));
        Point o = (axisRay.getP0()).add((axisRay.getDir()).scale(t));
        return ((p.subtract(o)).normalize());
    }
}