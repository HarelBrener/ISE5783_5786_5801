/**
 * Represents a scene in a computer graphics environment.
 * It contains information about the name, background color, ambient light, and geometries in the scene.
 */
package scene;

import geometries.Geometries;
import static lighting.AmbientLight.NONE;
import lighting.AmbientLight;
import primitives.*;

public class Scene {

    /**
     * The name of the scene.
     */
    public String name;

    /**
     * The background color of the scene.
     * Default value is BLACK.
     */
    public Color background = Color.BLACK;

    /**
     * The ambient light in the scene.
     * Default value is NONE.
     */
    public AmbientLight ambientlight = NONE;

    /**
     * The geometries present in the scene.
     */
    public Geometries geometries = new Geometries();

    /**
     * Constructs a new Scene object with the specified name.
     *
     * @param name The name of the scene.
     */
    public Scene(String name){
        this.name = name;
    }

    /**
     * Sets the name of the scene.
     *
     * @param name The name of the scene.
     * @return The current Scene object.
     */
    public Scene setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Sets the background color of the scene.
     *
     * @param background The background color of the scene.
     * @return The current Scene object.
     */
    public Scene setBackground(Color background) {
        this.background = background;
        return this;
    }

    /**
     * Sets the ambient light in the scene.
     *
     * @param ambientlight The ambient light in the scene.
     * @return The current Scene object.
     */
    public Scene setAmbientLight(AmbientLight ambientlight) {
        this.ambientlight = ambientlight;
        return this;
    }

    /**
     * Sets the geometries present in the scene.
     *
     * @param geometries The geometries present in the scene.
     */
    public void setGeometries(Geometries geometries) {
        this.geometries = geometries;
    }

}