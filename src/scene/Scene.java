package scene;
import geometries.Geometries;
import static lighting.AmbientLight.NONE;
import lighting.AmbientLight;
import primitives.*;


public class Scene {
    public String name;
    public Color background = Color.BLACK;
    public AmbientLight ambientlight = NONE;

    public Geometries geometries = new Geometries();
    public Scene(String name){
        this.name = name;
    }

    public Scene setName(String name) {
        this.name = name;
        return this;
    }

    public Scene setBackground(Color background) {
        this.background = background;
        return this;
    }

    public Scene setAmbientLight(AmbientLight ambientlight) {
        this.ambientlight = ambientlight;
        return this;
    }

    public void setGeometries(Geometries geometries) {
        this.geometries = geometries;
    }

}
