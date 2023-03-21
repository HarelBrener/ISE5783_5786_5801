package geometries;

import primitives.Ray;

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
}