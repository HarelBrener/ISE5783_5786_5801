/**

 A basic implementation of the RayTracer interface for tracing rays in a scene.
 This class extends the RayTracerBase class.
 */
package renderer;
import lighting.LightSource;
import primitives.*;
import scene.Scene;
import geometries.Intersectable.GeoPoint;

import java.lang.reflect.Member;
import java.util.List;

import static primitives.Util.alignZero;

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
        return calcColor(cp,ray);
    }

    /**
     * Calculates the color at the specified point in the scene.
     *
     * @param gp the point at which to calculate the color
     * @return the calculated color at the point
     */
    private Color calcColor(GeoPoint gp, Ray r) {
        return scene.ambientlight.getIntensity().add(calcLocalEffects(gp,r));
    }

    private Color calcLocalEffects(GeoPoint gp, Ray ray) {
        Color color = gp.geometry.getEmission();
        Vector v = ray.getDir (); Vector n = gp.geometry.getNormal(gp.point);
        double nv = alignZero(n.dotProduct(v)); if (nv == 0) return color;
        Material material = gp.geometry.getMaterial();
        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(gp.point);
            double nl = alignZero(n.dotProduct(l));
            if (nl * nv > 0) { // sign(nl) == sing(nv)
                Color iL = lightSource.getIntensity(gp.point);
                color = color.add(iL.scale(calcDiffusive(material, nl)),
                        iL.scale(calcSpecular(material, n, l, nl, v)));
            }
        }
        return color;
    }
    private Double3 calcDiffusive(Material mat, double nl){
        return mat.kD.scale(Math.abs(nl));
    }

    private Double3 calcSpecular(Material mat, Vector n, Vector l, double nl, Vector v){
        Vector r = l.subtract(n.scale(nl*2));
        double temp = Math.max(0, r.dotProduct(v.scale(-1)));
        return mat.kS.scale(Math.pow(temp, mat.nShininess));
    }
}
