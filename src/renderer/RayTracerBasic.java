/**

 A basic implementation of the RayTracer interface for tracing rays in a scene.
 This class extends the RayTracerBase class.
 */
package renderer;
import lighting.LightSource;
import lighting.PointLight;
import primitives.*;
import scene.Scene;
import geometries.Intersectable.GeoPoint;

import java.lang.reflect.Member;
import java.util.LinkedList;
import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

public class RayTracerBasic extends RayTracerBase {

    /** The maximum times we will do the recurse */
    private static final int MAX_CALC_COLOR_LEVEL = 10;

    /** A small number where we will stop the recursion because the
       effect of the transparency is already too small */
    private static final double MIN_CALC_COLOR_K = 0.001;

    private static final Double3 INITIAL_K = Double3.ONE;

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
        GeoPoint closestGeoPoint = findClosestIntersection(ray);
        if (closestGeoPoint == null)
            return scene.background;
        return calcColor(closestGeoPoint,ray);
    }

    /**
     * Calculates the color at the specified point in the scene.
     *
     * @param gp the point at which to calculate the color
     * @return the calculated color at the point
     */
    private Color calcColor(GeoPoint gp, Ray ray) {
        return calcColor(gp,ray,MAX_CALC_COLOR_LEVEL,INITIAL_K).add(scene.ambientlight.getIntensity());
    }

    /**
     * Calculates the color of a point in the scene, considering both local and global effects.
     * This method recursively calculates global effects by tracing reflected and refracted rays.
     *
     * @param gp    The geometric point to calculate the color for.
     * @param ray   The incident ray.
     * @param level The recursion level.
     * @param k     The coefficient of the ray's path.
     * @return The color of the point.
     */
    private Color calcColor(GeoPoint gp, Ray ray, int level, Double3 k) {
        Color color = calcLocalEffects(gp, ray,k);
        if (level == 1) {
            return color;
        }
        else {
            Color globalEffects = calcGlobalEffects(gp, ray, level, k);
            return color.add(globalEffects);
        }
    }

    /**
     * Calculates the global effects (reflection and refraction) for a point in the scene.
     *
     * @param gp    The geometric point to calculate the effects for.
     * @param ray   The incident ray.
     * @param level The recursion level.
     * @param k     The coefficient of the ray's path.
     * @return The color of the global effects.
     */
    private Color calcGlobalEffects(GeoPoint gp, Ray ray, int level, Double3 k) {
        Color color = Color.BLACK;
        Material mat = gp.geometry.getMaterial();
        Double3 kr = mat.kR;
        Double3 kkr = k.product(kr);
        Ray reflectedRay = constructReflectedRay(gp.geometry.getNormal(gp.point), gp.point, ray);
        GeoPoint reflectedPoint = findClosestIntersection(reflectedRay);
        if (!(kkr.lowerThan(MIN_CALC_COLOR_K)) && reflectedPoint != null) {
            color = color.add(calcColor(reflectedPoint, reflectedRay, level - 1, kkr).scale(kr));
        }
        Double3 kt = mat.kT;
        Double3 kkt = k.product(kt);
        Ray refractedRay = constructRefractedRay(gp.geometry.getNormal(gp.point), gp.point, ray);
        GeoPoint refractedPoint = findClosestIntersection(refractedRay);
        if (!(kkt.lowerThan(MIN_CALC_COLOR_K)) && refractedPoint != null) {
            color = color.add(calcColor(refractedPoint, refractedRay, level - 1, kkt).scale(kt));
        }
        return color;
    }


    /**
     * Calculates the local effects (diffuse and specular reflection) for a point in the scene.
     *
     * @param gp  The geometric point to calculate the effects for.
     * @param ray The incident ray.
     * @return The color of the local effects.
     */
    private Color calcLocalEffects(GeoPoint gp, Ray ray,Double3 k) {
        Color color = gp.geometry.getEmission();
        Vector v = ray.getDir();
        Vector n = gp.geometry.getNormal(gp.point);
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0) {
            return color;
        }
        Material material = gp.geometry.getMaterial();
        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(gp.point);
            double nl = alignZero(n.dotProduct(l));
            if (nl * nv > 0) { // sign(nl) == sign(nv)
                Double3 ktr;
                 if(lightSource.getLenght() > 0 && lightSource instanceof PointLight) {
                    ktr = avgT(l,n,(PointLight) lightSource,gp);
                }
                else {
                    ktr = transparency(l, n, gp, lightSource);
                }
                if (!(ktr.product(k).lowerThan(MIN_CALC_COLOR_K))) {
                    Color iL = lightSource.getIntensity(gp.point).scale(ktr);
                    color = color.add(iL.scale(calcDiffusive(material, nl)),
                            iL.scale(calcSpecular(material, n, l, nl, v)));
                }
            }
        }
        return color;
    }

    /**
     * Calculates the diffuse reflection component for a given material and dot product of the normal and light vector.
     *
     * @param mat

    The material of the object.
     * @param nl  The dot product of the normal and light vector.
     * @return The diffuse reflection component.
     */
    private Double3 calcDiffusive(Material mat, double nl) {
        return mat.kD.scale(Math.abs(nl));
    }

    /**
     * Calculates the specular reflection component for a given material, normal vector, light vector,
     * dot product of the normal and light vector, and view vector.
     *
     * @param mat The material of the object.
     * @param n   The normal vector.
     * @param l   The light vector.
     * @param nl  The dot product of the normal and light vector.
     * @param v   The view vector.
     * @return The specular reflection component.
     */
    private Double3 calcSpecular(Material mat, Vector n, Vector l, double nl, Vector v) {
        Vector r = l.subtract(n.scale(nl * 2));
        double temp = Math.max(0, r.dotProduct(v.scale(-1)));
        return mat.kS.scale(Math.pow(temp, mat.nShininess));
    }

    private Double3 transparency(Vector l, Vector n, GeoPoint gp, LightSource lightSource) {
        Vector lightDirection = l.scale(-1); // from point to light source
        Ray lightRay = new Ray(gp.point, lightDirection, n);
        Point point = lightRay.getP0();
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay);
        if (intersections == null)
            return Double3.ONE;
        Double3 ktr = Double3.ONE;
        for (GeoPoint element : intersections) {
            if (lightSource.getDistance(point) > point.distance(element.point))
                ktr = ktr.product(element.geometry.getMaterial().kT);
        }
        return ktr;
    }

    /**
     * Constructs a reflected ray based on the given surface normal, point of intersection, and incident ray.
     *
     * @param n   The surface normal vector.
     * @param p   The point of intersection.
     * @param ray The incident ray.
     * @return The reflected ray.
     */
    public Ray constructReflectedRay(Vector n, Point p, Ray ray) {
        Vector r = ray.getDir().subtract(n.scale(ray.getDir().dotProduct(n) * 2));
        return new Ray(p, r, n);
    }

    /**
     * Constructs a refracted ray based on the given surface normal, point of intersection, and a ray to surface.
     *
     * @param n   The surface normal vector.
     * @param p   The point of intersection.
     * @param ray The ray to surface
     * @return The refracted ray.
     */
    public Ray constructRefractedRay(Vector n, Point p, Ray ray) {
        return new Ray(p, ray.getDir(), n);
    }

    /**
     * Finds the closest intersection between the given ray and the geometries in the scene.
     *
     * @param ray The ray to intersect with the geometries.
     * @return The closest intersection point on the geometries, if any.
     */
    private GeoPoint findClosestIntersection(Ray ray) {
        return ray.findClosestGeoPoint(this.scene.geometries.findGeoIntersections(ray));
    }
}
