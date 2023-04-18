package geometries;

import org.junit.jupiter.api.Test;
import primitives.*;

import static org.junit.jupiter.api.Assertions.*;

class PlaneTest {

    /**
     * Test method for {@link Plane#Plane(Point, Point, Point)}.
     */
    @Test
    void testConstructor(){
        // =============== Boundary Values Tests ==================
        // checks weather the Plane constructor works correctly
        assertThrows(IllegalArgumentException.class, () -> new Plane(new Point(1,2,4),new Point(1,2,4), new Point(2,3,4) ), "ERROR: plane's constructor does not throw an exception for 2 same point");
        // =============== Boundary Values Tests ==================
        // checks weather the Plane constructor works correctly
        assertThrows(IllegalArgumentException.class, () -> new Plane(new Point(1,2,4),new Point(2,4,8), new Point(-1,-2,-4) ), "ERROR: plane's constructor does not throw an exception case that all the three points are on the same line");
    }

    /**
     * Test method for {@link Plane#getNormal()}.
     */
    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // checks weather the getNormal function works correctly
        Plane pln = new Plane(new Point(1, 0 ,1), new Point(4, 0 ,12), new Point(-1, 0 , 3));
        Vector nrml = new Vector(0,1,0);
        Vector res = pln.getNormal(new Point(1,0,1));
        assertTrue(nrml.equals(res) || nrml.equals(res.scale(-1)), "ERROR: the function getNormal does not work proper");
    }
}