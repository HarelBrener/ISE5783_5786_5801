package geometries;

import static primitives.Util.isZero;

import java.util.LinkedList;
import java.util.List;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/** Polygon class represents two-dimensional polygon in 3D Cartesian coordinate
 * system
 * @author Dan */
public class Polygon extends Geometry {
   /** List of polygon's vertices */
   protected final List<Point> vertices;
   /** Associated plane in which the polygon lays */
   protected final Plane       plane;
   private final int           size;

   /** Polygon constructor based on vertices list. The list must be ordered by edge
    * path. The polygon must be convex.
    * @param  vertices                 list of vertices according to their order by
    *                                  edge path
    * @throws IllegalArgumentException in any case of illegal combination of
    *                                  vertices:
    *                                  <ul>
    *                                  <li>Less than 3 vertices</li>
    *                                  <li>Consequent vertices are in the same
    *                                  point
    *                                  <li>The vertices are not in the same
    *                                  plane</li>
    *                                  <li>The order of vertices is not according
    *                                  to edge path</li>
    *                                  <li>Three consequent vertices lay in the
    *                                  same line (180&#176; angle between two
    *                                  consequent edges)
    *                                  <li>The polygon is concave (not convex)</li>
    *                                  </ul>
    */
   public Polygon(Point... vertices) {
      if (vertices.length < 3)
         throw new IllegalArgumentException("A polygon can't have less than 3 vertices");
      this.vertices = List.of(vertices);
      size          = vertices.length;

      // Generate the plane according to the first three vertices and associate the
      // polygon with this plane.
      // The plane holds the invariant normal (orthogonal unit) vector to the polygon
      plane         = new Plane(vertices[0], vertices[1], vertices[2]);
      if (size == 3) return; // no need for more tests for a Triangle

      Vector  n        = plane.getNormal();
      // Subtracting any subsequent points will throw an IllegalArgumentException
      // because of Zero Vector if they are in the same point
      Vector  edge1    = vertices[vertices.length - 1].subtract(vertices[vertices.length - 2]);
      Vector  edge2    = vertices[0].subtract(vertices[vertices.length - 1]);

      // Cross Product of any subsequent edges will throw an IllegalArgumentException
      // because of Zero Vector if they connect three vertices that lay in the same
      // line.
      // Generate the direction of the polygon according to the angle between last and
      // first edge being less than 180 deg. It is hold by the sign of its dot product
      // with
      // the normal. If all the rest consequent edges will generate the same sign -
      // the
      // polygon is convex ("kamur" in Hebrew).
      boolean positive = edge1.crossProduct(edge2).dotProduct(n) > 0;
      for (var i = 1; i < vertices.length; ++i) {
         // Test that the point is in the same plane as calculated originally
         if (!isZero(vertices[i].subtract(vertices[0]).dotProduct(n)))
            throw new IllegalArgumentException("All vertices of a polygon must lay in the same plane");
         // Test the consequent edges have
         edge1 = edge2;
         edge2 = vertices[i].subtract(vertices[i - 1]);
         if (positive != (edge1.crossProduct(edge2).dotProduct(n) > 0))
            throw new IllegalArgumentException("All vertices must be ordered and the polygon must be convex");
      }
   }

   /**

    Returns the normal vector to the polygon at the given point.
    @param point The point to find the normal vector at.
    @return The normal vector to the polygon at point.
    */
   @Override
   public Vector getNormal(Point point) { return plane.getNormal(); }

   /**
    Finds intersection points of a ray with a polygon in 3D space.
    If no intersection points exist, returns null.
    @param ray The ray to intersect with the polygon.
    @return A list of intersection GeoPoints (if they exist), otherwise null.
    */
   @Override
   public List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
      List<GeoPoint> l = plane.findGeoIntersectionsHelper(ray);
      if (l == null)
         return null;
      Point p = l.get(0).point;
      List<Vector> normals = null;
      Vector x = null;
      Vector y = null;
      Point p0 = vertices.get(0);
      Point pi = null;
      Point pi1 = null;
      for (int i = 0; i < size; i++) {
         if (normals == null)
            normals = new LinkedList<>();
         pi = vertices.get(i);
         if (i == size - 1) {
            if (p0.equals(pi) || pi.equals(p))
               return null;
            x = p0.subtract(pi);
            y = pi.subtract(p);
            if (x.normalize().equals(y.normalize()) || x.normalize().equals(y.normalize().scale(-1)))
               return null;
            normals.add(x.crossProduct(y));
         } else {
            pi1 = vertices.get(i + 1);
            if (pi.equals(pi1) || pi.equals(p))
               return null;
            x = pi1.subtract(pi);
            y = pi.subtract(p);
            if (x.normalize().equals(y.normalize()) || x.normalize().equals(y.normalize().scale(-1)))
               return null;
            else
               normals.add(x.crossProduct(y));
         }
      }
      for (int i = 0; i < size; i++) {
         for (int j = i + 1; j < size; j++) {
            if ((normals.get(i)).dotProduct(normals.get(j)) < 0)
               return null;
         }
      }
      return l;
   }
}
