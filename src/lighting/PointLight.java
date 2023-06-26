package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

public class PointLight extends Light implements LightSource {
    final private Point position;

    private double kC = 1;

    private double kL = 0;

    private double kQ = 0;

    /**
     * The radius of the light
     */
    //final private double radius;///////

    public PointLight(Color intensity, Point position) { //////////////////
        super(intensity);
        this.position = position;
    }

    public Color getIntensity(Point p){
        double d = this.position.distanceSquared(p);
        double denomentorIL = this.kC + this.kL* Math.sqrt(d) + this.kQ*d;
        if(denomentorIL == 0)
            throw new IllegalArgumentException("The denomentor is zero");
        return this.getIntensity().reduce(denomentorIL);
    }

    public Vector getL(Point p) {
        return (p.subtract(this.position)).normalize();
    }

    public PointLight setKc(double kC) {
        this.kC = kC;
        return this;
    }

    public PointLight setKl(double kL) {
        this.kL = kL;
        return this;
    }

    public PointLight setKq(double kQ) {
        this.kQ = kQ;
        return this;
    }

    @Override
    public double getDistance(Point point) {
        return point.distance(position);
    }
}
