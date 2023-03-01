package primitives;

import java.awt.* ;

public class Vector extends Point {
    Point V;

    public Vector(Point v) {
        V = v;
    }

    public Vector getV() {
        return (Vector)V;
    }

    public void setP(Vector p) {
        V = p;
    }
}
