package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

class DirectionalLight extends Light implements LightSource{
    private Vector direction;

    public Color getIntensity(Point p){
        return Color.BLACK;
    }
    public Vector getL(Point p){
        return  null;
    }
}
