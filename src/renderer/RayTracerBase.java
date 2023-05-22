/**

 The RayTracerBase class is an abstract class that serves as the base for a ray tracer in a rendering package.
 It provides the basic functionality for tracing a ray and returning the color of the intersected object.
 **/

package renderer;

import primitives.*;
import scene.Scene;

public abstract class RayTracerBase {
    protected Scene scene;

    /**
     * Constructs an object with the specified scene.
     * @param scene the scene to be rendered
     */
    public RayTracerBase(Scene scene){
        this.scene = scene;
    }

    /**
     * Traces a ray and returns the color of the intersected object.
     * @return the color of the intersected object
     */
    abstract public Color traceRay(Ray ray);
}
