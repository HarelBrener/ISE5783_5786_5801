package geometries;

import primitives.Ray;

public class Cylinder extends Tube {
    double height;

    public Cylinder(double r, Ray axisRay, double height) {
        super(r, axisRay);
        this.height = height;
    }
}

