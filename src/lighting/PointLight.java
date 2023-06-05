package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

public class PointLight extends Light implements LightSource {
    private Point position;

    private double kC;

    private double kL;

    private double kQ;

    public Color getIntensity(Point p){
        return null;
    }
    public Vector getL(Point p) {
        return null;
    }

    public double setkC(double kC) {
        this.kC = kC;
        return this.kC;
    }

    public double setkL(double kL) {
        this.kL = kL;
        return this.kL;
    }

    public double setkQ(double kQ) {
        this.kQ = kQ;
        return this.kQ;
    }
}
