package primitives;

/**
 * Represents a ray in a 3D space.
 * A ray is defined by a starting point and a direction vector.
 */
public class Ray {

    /** The starting point of the ray */
    final Point p0;

    /** The direction vector of the ray */
    final Vector dir;

    /**
     * Constructs a ray with the specified starting point and direction vector.
     * The direction vector is normalized to have a length of 1.
     * @param p The starting point of the ray.
     * @param v The direction vector of the ray.
     */
    public Ray(Point p, Vector v) {
        p0 = p;
        dir = v.normalize();
    }

    /**
     * Returns the starting point of the ray.
     * @return The starting point of the ray.
     */
    public Point getP0(){
        return  p0;
    }

    /**
     * Returns the direction vector of the ray.
     * @return The direction vector of the ray.
     */
    public Vector getDir() {
        return dir;
    }
}