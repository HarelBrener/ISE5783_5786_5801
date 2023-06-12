/**

 A basic implementation of the RayTracer interface for tracing rays in a scene.
 This class extends the RayTracerBase class.
 */
package renderer;
import primitives.Color;
import primitives.Point;
import primitives.Ray;
import scene.Scene;
import geometries.Intersectable.GeoPoint;

import java.util.List;

public class RayTracerBasic extends RayTracerBase {

    /**
    * Constructs a RayTracerBasic object with the specified scene.
    * @param scene the scene to be traced
    * Constructs a RayTracerBasic object with the specified scene.
    * @param scene the scene to be traced
    */
public RayTracerBasic(Scene scene) {
    super(scene);
}

    /**
     * Traces a ray in the scene and calculates the color result based on the intersection points.
     *
     * @param ray the ray to be traced
     * @return the color result of tracing the ray
     */
    @Override
    public Color traceRay(Ray ray) {
        List<GeoPoint> l = scene.geometries.findGeoIntersectionsHelper(ray);
        if (l == null)
            return scene.background;
        GeoPoint cp = ray.findClosestGeoPoint(l);
        return calcColor(cp);
    }

    /**
     * Calculates the color at the specified point in the scene.
     *
     * @param gp the point at which to calculate the color
     * @return the calculated color at the point
     */
    private Color calcColor(GeoPoint gp) {
        return scene.ambientlight.getIntensity().add(gp.geometry.getEmission());
    }
}
