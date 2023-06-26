package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

public class SpotLight extends PointLight{
    private Vector direction;

    public SpotLight(Color intensity, Point position, Vector direction) { /////////////////
        super(intensity, position);
        this.direction = direction.normalize(); ////////////////
    }

    @Override
    public Color getIntensity(Point p){
        double max = Math.max(0, getL(p).dotProduct(this.direction));
        return super.getIntensity(p).scale(max);
    }

    @Override
    public Vector getL(Point p) {
        return super.getL(p);
    }

    @Override
    public double getDistance(Point point) {
        return super.getDistance(point);
    }
}
