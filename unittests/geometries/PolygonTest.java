package geometries;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNull;
import static primitives.Util.isZero;

import org.junit.jupiter.api.Test;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

/** Testing Polygons
 * @author Dan */
public class PolygonTest {

   /**
    * Test method for {@link geometries.Polygon#Polygon(primitives.Point...)}.
    */
   @Test
   public void testConstructor() {
      // ============ Equivalence Partitions Tests ==============

      // TC01: Correct concave quadrangular with vertices in correct order
      try {
         new Polygon(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0), new Point(-1, 1, 1));
      } catch (IllegalArgumentException e) {
         fail("Failed constructing a correct polygon");
      }

      // TC02: Wrong vertices order
      assertThrows(IllegalArgumentException.class, //
              () -> new Polygon(new Point(0, 0, 1), new Point(0, 1, 0), new Point(1, 0, 0), new Point(-1, 1, 1)), //
              "Constructed a polygon with wrong order of vertices");

      // TC03: Not in the same plane
      assertThrows(IllegalArgumentException.class, //
              () -> new Polygon(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0), new Point(0, 2, 2)), //
              "Constructed a polygon with vertices that are not in the same plane");

      // TC04: Concave quadrangular
      assertThrows(IllegalArgumentException.class, //
              () -> new Polygon(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0),
                      new Point(0.5, 0.25, 0.5)), //
              "Constructed a concave polygon");

      // =============== Boundary Values Tests ==================

      // TC10: Vertex on a side of a quadrangular
      assertThrows(IllegalArgumentException.class, //
              () -> new Polygon(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0),
                      new Point(0, 0.5, 0.5)),
              "Constructed a polygon with vertix on a side");

      // TC11: Last point = first point
      assertThrows(IllegalArgumentException.class, //
              () -> new Polygon(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0), new Point(0, 0, 1)),
              "Constructed a polygon with vertice on a side");

      // TC12: Co-located points
      assertThrows(IllegalArgumentException.class, //
              () -> new Polygon(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0), new Point(0, 1, 0)),
              "Constructed a polygon with vertice on a side");

   }

   /**
    * Test method for {@link geometries.Polygon#getNormal(primitives.Point)}.
    */
   @Test
   public void testGetNormal() {
      // ============ Equivalence Partitions Tests ==============
      // TC01: There is a simple single test here - using a quad
      Point[] pts =
              {new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0), new Point(-1, 1, 1)};
      Polygon pol = new Polygon(pts);
      // ensure there are no exceptions
      assertDoesNotThrow(() -> pol.getNormal(new Point(0, 0, 1)), "");
      // generate the test result
      Vector result = pol.getNormal(new Point(0, 0, 1));
      // ensure |result| = 1
      assertEquals(1, result.length(), 0.00000001, "Polygon's normal is not a unit vector");
      // ensure the result is orthogonal to all the edges
      for (int i = 0; i < 3; ++i)
         assertTrue(isZero(result.dotProduct(pts[i].subtract(pts[i == 0 ? 3 : i - 1]))),
                 "Polygon's normal is not orthogonal to one of the edges");
   }

   /**
    * Test method for {@link Polygon#findIntsersections(Ray)}.
    */
   @Test
   void testFindIntersectionPoints() {
      Polygon trngl = new Polygon(new Point(1, 0 ,0), new Point(0, 1 ,0), new Point(0, -1 , 0));
      Point p0 = new Point(0,0,1);//Ray's (and vector's) start point
      Point p;//intersection point
      Vector v;//Ray's direction vector
      List<Point> result;
      // ============ Equivalence Partitions Tests ==============
      //TC01: The ray intersects the triangle (1 point)
      p = new Point(0.3,0.3,0);
      v = new Vector(0.3,0.3,-1);
      result = trngl.findIntersections(new Ray(p0,v));
      assertEquals(result,List.of(p),"The ray intersected the triangle");
      //TC02: The ray intersects the plane in front of the edge (!!but not the triangle!!)
      v = new Vector(1,1,-1);
      result = trngl.findIntersections(new Ray(p0,v));
      assertNull(result,"The ray intersects the plane in front of the edge");
      //TC03: The ray intersects the plane in front of the vertex (!!but not the triangle!!)
      v = new Vector(2,0,-1);
      result = trngl.findIntersections(new Ray(p0,v));
      assertNull(result,"The ray intersects the plane in front of the vertex");
      // =============== Boundary Values Tests ==================
      //TC11: The intersection point is on edge
      v = new Vector(0.5,0.5,-1);
      result = trngl.findIntersections(new Ray(p0,v));
      assertNull(result,"The intersection point is on edge");
      //TC12: The intersection point is on vertex
      v = new Vector(1,0,-1);
      result = trngl.findIntersections(new Ray(p0,v));
      assertNull(result,"The intersection point is on vertex");
      //TC13: The intersection point is on edge continuation
      v = new Vector(1.5,0.5,-1);
      result = trngl.findIntersections(new Ray(p0,v));
      assertNull(result,"The intersection point is on edge continuation");
   }
}
