package lighting;

import primitives.Color;
import primitives.Double3;

public class AmbientLight extends Light {
    public static AmbientLight NONE = new AmbientLight(Color.BLACK,Double3.ZERO);

    public AmbientLight(Color IA, Double3 KA){
       super(IA.scale(KA));
    }
    public AmbientLight(Color IA, double KA){
        super(IA.scale(KA));
    }
}
