package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

class DirectionalLight extends Light implements LightSource{
    private Vector direction;

    public DirectionalLight(Color intensity, Vector direction) {
        super(intensity);
        this.direction = direction;
    }

    public Color getIntensity(Point p){
        return this.getIntensity();
    }
    public Vector getL(Point p){
        return this.direction;
    }
}
