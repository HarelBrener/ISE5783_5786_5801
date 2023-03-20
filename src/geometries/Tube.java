package geometries;
import primitives.*;
public class Tube extends RadialGeometry{
    final Ray axisRay;

    public Tube(double r, Ray axisRay) {
        super(r);
        this.axisRay = axisRay;
    }

    @Override
    public Vector getNormal(Point p) {
        return null;
    }
}
