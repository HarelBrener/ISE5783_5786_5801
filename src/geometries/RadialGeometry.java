package geometries;

public abstract class RadialGeometry implements Geometry {
    protected final double  radius;
    RadialGeometry(double r)
    {
        radius=r;
    }

}
