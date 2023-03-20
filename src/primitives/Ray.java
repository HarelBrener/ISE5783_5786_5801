package primitives;

import java.security.PublicKey;

public class Ray {
    final Point p0;
    final Vector dir;
    public Ray(Point p, Vector v) {
        p0=p;
        dir=v.normalize();
    }
    public Point getP0(){
        return  p0;
    }
    public Vector getDir() {
        return dir;
    }
}
