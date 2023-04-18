package primitives;

/**
 * Represents a 3D vector.
 * A vector is a point in 3D space that represents a magnitude and direction.
 * It can be used to represent things such as position, velocity, and acceleration.
 */
public class Vector extends Point {

    /**
     * Constructs a vector from a Double3 object.
     * If the vector is the zero vector, an IllegalArgumentException is thrown.
     *
     * @param p The Double3 object representing the vector.
     * @throws IllegalArgumentException If the vector is the zero vector.
     */
    Vector(Double3 p) {
        super(p);
        if (xyz.equals(p.ZERO)) {
            throw new IllegalArgumentException("The vector is the zero vector");
        }
    }

    /**
     * Constructs a vector from its x, y, and z components.
     * If the vector is the zero vector, an IllegalArgumentException is thrown.
     *
     * @param a The x component of the vector.
     * @param b The y component of the vector.
     * @param c The z component of the vector.
     * @throws IllegalArgumentException If the vector is the zero vector.
     */
    public Vector(double a, double b, double c) {
        super(a, b, c);
        if (xyz.equals(Double3.ZERO)) {
            throw new IllegalArgumentException("The vector is the zero vector");
        }
    }

    /**
     * Adds another vector to this vector and returns the result.
     *
     * @param v The vector to add to this vector.
     * @return The sum of this vector and the given vector.
     */
    public Vector add(Vector v) {
        Double3 addV = this.xyz.add(v.xyz);
        return new Vector(addV);
    }

    /**
     * Scales this vector by a scalar value and returns the result.
     *
     * @param scl The scalar value to multiply this vector by.
     * @return The scaled vector.
     */
    public Vector scale(double scl) {
        Double3 res = xyz.scale(scl);
        return new Vector(res);
    }

    /**
     * Computes the dot product of this vector and another vector.
     * <p>
     * Calculates the dot product of this vector with another vector.
     *
     * @param v the other vector to calculate dot product with
     * @return the dot product of this vector and the other vector
     */
    public double dotProduct(Vector v) {
        // Calculate the dot product of this vector and the other vector
        double sum = (this.xyz.d1 * v.xyz.d1) + (this.xyz.d2 * v.xyz.d2) + (this.xyz.d3 * v.xyz.d3);
        return sum;
    }

    /**
     * Calculates the cross product of this vector with another vector.
     *
     * @param v the other vector to calculate cross product with
     * @return a new vector representing the cross product of this vector and the other vector
     */
    public Vector crossProduct(Vector v) {
        // Calculate the cross product of this vector and the other vector
        double a = this.xyz.d2 * v.xyz.d3 - v.xyz.d2 * this.xyz.d3;
        double b = v.xyz.d1 * this.xyz.d3 - this.xyz.d1 * v.xyz.d3;
        double c = this.xyz.d1 * v.xyz.d2 - v.xyz.d1 * this.xyz.d2;
        // Return a new vector representing the cross product
        return new Vector(a, b, c);
    }

    /**
     * Calculates the square of the length of this vector.
     *
     * @return the square of the length of this vector
     */
    public double lengthSquared() {
        // Calculate the dot product of this vector with itself to get the square of the length
        return dotProduct(this);
    }

    /**
     * Calculates the length of this vector.
     *
     * @return the length of this vector
     */
    public double length() {
        // Calculate the square root of the square of the length to get the length
        return Math.sqrt(this.lengthSquared());
    }

    /**
     * Returns a new vector that is the normalized version of this vector.
     *
     * @return a new vector that is the normalized version of this vector
     */
    public Vector normalize() {
        // Calculate the normalization factor by dividing the length of this vector
        // with the vector itself and create a new vector that is the normalized version
        Double3 res = this.xyz.reduce(this.length());
        return new Vector(res);
    }
    /**
     * compares between the vectors and returns the answer
     * @param  o the object to compare
     * */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vector vector)) return false;
        return super.equals(o);
    }
}