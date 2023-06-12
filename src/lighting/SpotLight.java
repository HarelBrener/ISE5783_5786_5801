package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

public class SpotLight extends PointLight{
    private Vector direction;

    public SpotLight(Color intensity, Point position, Vector direction) {
        super(intensity, position);
        this.direction = direction;
    }

    @Override
    public Color getIntensity(Point p){
        double max = Math.max(0, getL(p).dotProduct(this.direction));
        double d = this.position.distanceSquared(p);
        double denomentorIL = this.kC + this.kL* Math.sqrt(d) + this.kQ*d;
        if(denomentorIL == 0)
            throw new IllegalArgumentException("The denomentor is zero");
        return this.getIntensity().reduce(denomentorIL);
    }

    @Override
    public Vector getL(Point p) {
        return (p.subtract(position)).normalize();
    }
}
