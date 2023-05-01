package geometries;

import org.junit.jupiter.api.Test;
import primitives.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlaneTest {
    /**
     * Test method for {@link Plane#Plane(Point, Point, Point)}.
     */
    @Test
    void testConstructor(){
        // =============== Boundary Values Tests ==================
        //TC11: checks weather the Plane constructor works correctly
        assertThrows(IllegalArgumentException.class, () -> new Plane(new Point(1,2,4),new Point(1,2,4), new Point(2,3,4) ), "ERROR: plane's constructor does not throw an exception for 2 same point");
        //TC12: checks weather the Plane constructor works correctly
        assertThrows(IllegalArgumentException.class, () -> new Plane(new Point(1,2,4),new Point(2,4,8), new Point(-1,-2,-4) ), "ERROR: plane's constructor does not throw an exception case that all the three points are on the same line");
    }

    /**
     * Test method for {@link Plane#getNormal()}.
     */
    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        //TC01: checks weather the getNormal function works correctly
        Plane pln = new Plane(new Point(1, 0 ,1), new Point(4, 0 ,12), new Point(-1, 0 , 3));
        Vector nrml = new Vector(0,1,0);
        Vector res = pln.getNormal(new Point(1,0,1));
        assertTrue(nrml.equals(res) || nrml.equals(res.scale(-1)), "ERROR: the function getNormal does not work proper");
    }

    /**
     * Test method for {@link Plane#findIntsersections(Ray)}.
     */
    @Test
    void testFindIntersectionPoints(){
        // ============ Equivalence Partitions Tests ==============
        Plane pln = new Plane(new Point(1, 0 ,0), new Point(0, 1 ,0), new Point(0, 0.5 , 0));
        Point p0;//Ray's (and vector's) start point
        Point p;//intersection point
        Vector v;//Ray's direction vector
        //TC01: Ray starts outside the plane, isn't parallel or orthogonal to the plane (1 point)
        p0 = new Point(0,0,2);
        p = new Point(1,1,0);
        v = new Vector(1,1,-2);
        List<Point> result = pln.findIntsersections(new Ray(p0,v));
        assertEquals(List.of(p),result,"Ray starts outside the plane and there is intersection point");
        //TC02: Ray starts outside the plane, isn't parallel or orthogonal to the plane (0 points)
        //p = new Point(0,1,3);
        v = new Vector(0,1,1);
        result = pln.findIntsersections(new Ray(p0,v));
        assertNull(result,"Ray starts outside the sphere and there is no intersection point");
        // =============== Boundary Values Tests ==================
        // **** Group: Ray is parallel to the plane (all tests 0 points)
        //TC11: Ray is parallel to the plane and isn't inside it
        v = new Vector(0,1,0);
        assertNull(pln.findIntsersections(new Ray(p0,v)),
                "Ray is parallel to the plane and isn't inside it");
        //TC12: Ray is parallel to the plane and inside it
        p0 = new Point(0,-1,0);
        assertNull(pln.findIntsersections(new Ray(p0,v)),
                "Ray is parallel to the plane and inside it");
        // **** Group: Ray is orthogonal to the plane
        //TC13: Ray is orthogonal to the plane and goes towards it (1 point)
        p0 = new Point(0.5,0.5,-2);
        p = new Point(0.5,0.5,0);
        v = new Vector(0,0,1);
        assertEquals(List.of(p),pln.findIntsersections(new Ray(p0,v)),
                "Ray is orthogonal to the plane and goes towards it");
        //TC14: Ray is orthogonal to the plane starts on it (0 points)
        p0 = p;
        assertNull(pln.findIntsersections(new Ray(p0,v)),
                "Ray is orthogonal to the plane starts on it");
        //TC15: Ray is orthogonal to the plane but directed against it (0 points)
        p0 = new Point (0.5, 1, 3);
        assertNull(pln.findIntsersections(new Ray(p0,v)),
                "Ray is orthogonal to the plane but directed against it");
        // **** Group: The other cases
        //TC16: Ray starts at the plane and isn't parallel or orthogonal to it
        p0 = new Point(0.5, 0.5,0);
        v = new Vector(0,3,3);
        assertNull(pln.findIntsersections(new Ray(p0,v)),
                "Ray starts at the plane and isn't parallel or orthogonal to it");
        //TC17: Ray starts at q0 (field, point on the plane) and isn't parallel or orthogonal to the plane
        p0 = new Point (1,0,0);
        assertNull(pln.findIntsersections(new Ray(p0,v)),
                "Ray starts at q0 and isn't parallel or orthogonal to it");
    }

}