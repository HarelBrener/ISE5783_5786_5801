package geometries;
import primitives.*;

/**
 * The Geometry abstract class represents a geometrical object that can be intersected with a ray in 3D space,
 * and provides a method for calculating the normal vector at a specified point.
 */
public abstract class Geometry extends Intersectable {

    // emission light
    protected Color emission = Color.BLACK;


    /**
     * Returns the emission color of the geometry.
     * @return the emission color
     */
    public Color getEmission(){
        return this.emission;
    }

    /**
     * Sets the emission color of the geometry.
     * @param emission the emission color to be set
     * @return the updated geometry object
     */
    public Geometry setEmission(Color emission) {
        this.emission = emission;
        return this;
    }

    /**
     * Gets the normal vector to this Geometry object at the specified point in 3D space.
     * @param p  The point to calculate the normal vector at.
     * @return   The normal vector to this Geometry object at the specified point in 3D space.
     */
    public abstract Vector getNormal(Point p);
}
