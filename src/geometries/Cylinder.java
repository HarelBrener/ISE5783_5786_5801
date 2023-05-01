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

    /**
     * Calculates the normal vector of a point on a cylinder.
     * If the point is at the top or bottom base of the cylinder, returns the normal vector of the corresponding face.
     * If the point is on the curved surface of the cone, returns the normal vector of the surface at that point.
     *
     * @param p The point to calculate the normal vector at.
     * @return The normal vector of the point on the cylinder.
     */
    public Vector getNormal(Point p) {
        if ((p.equals(axisRay.getP0()))) { // if p is equal to the center of the bottom base
            return ((axisRay.getDir()).scale(-1)); // return the normal vector of the bottom face
        }
        if (p.equals(((axisRay.getP0()).add((axisRay.getDir()).scale(height))))) { // if p is equal to the center of the top base
            return axisRay.getDir(); // return the normal vector of the top face
        }
        if ((p.subtract(axisRay.getP0())).dotProduct(axisRay.getDir()) == 0) { // if p is orthogonal to the axisRay at the bottom base
            return ((axisRay.getDir()).scale(-1)); // return the normal vector of the bottom face
        }
        if ((p.subtract((axisRay.getP0()).add((axisRay.getDir()).scale(height)))).dotProduct(axisRay.getDir()) == 0) { // if p is orthogonal to the axisRay at the top base
            return axisRay.getDir(); // return the normal vector of the top face
        }
        return super.getNormal(p); // if p is on rest of the cylinder, call the Tube's method to calculate the normal vector
    }

}