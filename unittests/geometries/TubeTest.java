package geometries;

import org.junit.jupiter.api.Test;
import primitives.*;

import static org.junit.jupiter.api.Assertions.*;

class TubeTest {
    /**
     * Test method for {@link geometries.Tube#getNormal(Point)}.
     */
    @Test
    void getNormal() {
        // ============ Equivalence Partitions Tests ==============
        //TC01: checks weather the getNormal function for Tube works correctly
        Ray r = new Ray(new Point(0, 0, 0), new Vector(0, 0, 1));
        Tube t = new Tube(1, r);
        Point p = new Point(0, 1, 2);
        Vector nrl = new Vector(0, 1, 0);
        assertTrue(nrl.equals(t.getNormal(p)), "ERROR: getNormal(Point) of Tube does not work correctly");
        // =============== Boundary Values Tests ==================
        //TC11: in case that the point P is orthogonal to P0 the dotProduct between t and axisRay will throw exception
        //therefor, in the getNormal function we set the normal as (P-P0) insteadof the original calculation
        //and here we check that it works
        assertTrue(nrl.equals(t.getNormal(new Point(0, 1, 0))), "ERROR: getNormal(Point) of Tube does not work correctly in case that P and P0 are orthogonal");
    }

    /**
     * Test method for {@link Tube#findIntsersections(Ray)}.
     */
    @Test
    void testFindIntersectionPoints() {

    }
}