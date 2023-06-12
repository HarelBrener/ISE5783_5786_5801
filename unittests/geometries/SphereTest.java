package geometries;

import org.junit.jupiter.api.Test;
import primitives.Double3;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class SphereTest {
    /**
     * Test method for {@link geometries.Sphere#getNormal(Point)}.
     */
    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: checks weather the getNormal function of Sphere works correctly
        Sphere sp = new Sphere(2, new Point(0,0,0));
        Point p = new Point(2,0,0);
        Vector nrl = new Vector(1,0,0);
        assertTrue(nrl.equals(sp.getNormal(p)),"ERROR: getNormal(Point) of Sphere does not work correctly");
    }

    /**
     * Test method for {@link Sphere#findIntersections(Ray)}.
     */
    @Test
    void testFindIntersectionPoints(){
        Sphere sphere = new Sphere(1, new Point (1, 0.5, 0));
        Vector v = new Vector(-1,0,0);//ray's direction vector
        Point p0 = new Point(3,0,0);//the beginning of the ray
        // ============ Equivalence Partitions Tests ==============
        // TC01: Ray's line is outside the sphere (0 points)
        assertNull(sphere.findIntersections(new Ray(new Point(-1, 0, 0), new Vector(1, 1, 0))), "Ray's line out of sphere");
        // TC02: Ray starts before and crosses the sphere (2 points)
        Point p1 = new Point(0.13397459621556163,0,0);//first intersection point
        Point p2 = new Point(1.8660254037844386,0,0);//second intersection point
        List<Point> result = sphere.findIntersections(new Ray(p0,v));
        assertEquals(2,result.size(), "Wrong number of points");
        if (result.get(0).getX() > result.get(1).getX())
            result = List.of(result.get(1), result.get(0));
        assertEquals(List.of(p1, p2), result, "Ray starts before the sphere");
        // TC03: Ray starts inside the sphere (1 point)
        p0 = new Point(0.5,0,0);
        result = sphere.findIntersections(new Ray(p0,v));
        assertEquals(List.of(p1),result, "Ray starts inside the sphere");
        // TC04: Ray starts after the sphere (0 points)
        p0 = new Point(-1,0,0);
        assertNull(sphere.findIntersections(new Ray(p0, v)), "Ray starts after the sphere");
        // =============== Boundary Values Tests ==================
        // **** Group: Ray's line crosses the sphere (but not the center)
        // TC11: Ray starts at the sphere and goes inside (1 point)
        result = sphere.findIntersections(new Ray(p2,v));
        assertEquals(List.of(p1),result,"Ray starts at the sphere and goes inside");
        // TC12: Ray starts at the sphere and goes outside (0 points)
        assertNull(sphere.findIntersections(new Ray(p1, v)), "Ray starts at the sphere and goes outside");
        // **** Group: Ray's line goes through the center
        sphere = new Sphere(1, new Point (2, 0, 0));
        p1 = new Point(1,0,0);
        p2 = new Point(3,0,0);
        p0 = new Point(4,0,0);
        // TC13: Ray starts before the sphere (2 points)
        result = sphere.findIntersections(new Ray(p0,v));
        assertEquals(2,result.size(), "Wrong number of points");
        if (result.get(0).getX() > result.get(1).getX())
            result = List.of(result.get(1), result.get(0));
        assertEquals(List.of(p1, p2), result, "Ray starts before sphere and goes through the center");
        // TC14: Ray starts at the sphere and goes inside (1 point)
        result = sphere.findIntersections(new Ray(p2,v));
        assertEquals(List.of(p1),result,"Ray starts at the sphere and goes inside through the center");
        // TC15: Ray starts inside (1 point)
        p0 = new Point(1.5,0,0);
        result = sphere.findIntersections(new Ray(p0,v));
        assertEquals(List.of(p1),result,"Ray starts inside and goes through the center");
        // TC16: Ray starts at the center (1 point)
        p0 = new Point(2,0,0);
        result = sphere.findIntersections(new Ray(p0,v));
        assertEquals(List.of(p1),result,"Ray starts at the center");
        // TC17: Ray starts at the sphere and goes outside (0 points)
        assertNull(sphere.findIntersections(new Ray(p1, v)),
                "Ray starts at the sphere and goes outside when the continuation to the second side goes through the center");
        // TC18: Ray starts after sphere (0 points)
        p0 = new Point(-1,0,0);
        assertNull(sphere.findIntersections(new Ray(p0, v)),
                "Ray starts after sphere when the continuation to the second side goes through the center");
        // **** Group: Ray's line is tangent to the sphere (all tests 0 points)
        // TC19: Ray starts before the tangent point
        p0 = new Point(3,1,0);
        assertNull(sphere.findIntersections(new Ray(p0,v)),
                "Ray's line is tangent to the sphere and starts before the tangent point");
        // TC20: Ray starts at the tangent point
        p0 = new Point(2,1,0);
        assertNull(sphere.findIntersections(new Ray(p0,v)),
                "Ray's line is tangent to the sphere and starts at the tangent point");
        // TC21: Ray starts after the tangent point
        p0 = new Point(0,1,0);
        assertNull(sphere.findIntersections(new Ray(p0,v)),
                "Ray's line is tangent to the sphere and starts after the tangent point");
        // **** Group: Special cases
        // TC22: Ray's line is outside, the ray is orthogonal to the line that starts at Ray's start and goes to the sphere's center
        v = new Vector(0,1,0);
        p0 = new Point(4,0,0);
        assertNull(sphere.findIntersections(new Ray(p0,v)),"special case");
    }
}