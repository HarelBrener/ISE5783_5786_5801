package geometries;

/**
 * An abstract class representing a radial geometry in 3D space.
 * This includes geometries such as spheres and cylinders, which are defined by a radius.
 * This class provides a common implementation for radius-based geometries, such as storing the radius value.
 * To create a specific type of radial geometry, you should extend this class and implement any necessary methods.
 */

public abstract class RadialGeometry implements Geometry {
    protected final double  radius;
    RadialGeometry(double r)
    {
        radius=r;
    }

}
