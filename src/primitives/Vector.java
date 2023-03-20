package primitives;

import java.awt.* ;

public class Vector extends Point {

    Vector(Double3 p) {
        super(p);
        if (this.equals(p.ZERO)){
            throw new IllegalArgumentException("The vector is the zero vector");
        }
    }

    public Vector(double a, double b, double c) {
        super(a,b,c);
        if (this.equals(Double3.ZERO)){
            throw new IllegalArgumentException("The vector is the zero vector");
        }
    }
     public Vector add(Vector v){
        Double3 addV = this.xyz.add(v.xyz);
        return new Vector(addV);
    }
    Vector scale(double scl){
        Double3 res = xyz.scale(scl);
        return new Vector(res);
    }

    public double dotProduct(Vector v){
        double sum = (this.xyz.d1 * v.xyz.d1) + (this.xyz.d2 * v.xyz.d2) + (this.xyz.d3 * v.xyz.d3);
        return sum;
    }

    public Vector crossProduct(Vector v){
        double a = this.xyz.d2*v.xyz.d3-v.xyz.d2*this.xyz.d3;
        double b = v.xyz.d1*this.xyz.d3-this.xyz.d1*v.xyz.d3;
        double c = this.xyz.d1*v.xyz.d2-v.xyz.d1*this.xyz.d2;
        return new Vector(a,b,c);
    }
    public double lengthSquared(){
        return dotProduct(this);
    }
    public double length(){
        return Math.sqrt(this.lengthSquared());
    }
    public Vector normalize(){
        Double3 res = this.xyz.reduce(this.length());
        return new Vector(res);
    }


}
