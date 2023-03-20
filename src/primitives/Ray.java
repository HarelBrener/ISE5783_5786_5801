package primitives;

public class Ray {
    final Point p0;
    final Vector dir;
    public Ray(Point p, Vector v) {
        p0=p;
        dir=v.normalize();
    }
}
