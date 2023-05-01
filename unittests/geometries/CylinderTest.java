package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class CylinderTest {
    /**
     * Test method for {@link geometries.Cylinder#getNormal(Point)}.
     */
    @Test
    void getNormal() {
        // ============ Equivalence Partitions Tests ==============
        // checks weather the getNormal function for Tube works correctly for P0 inside the cylinder
        Vector dir = new Vector (0, 0, 1);
        Ray r = new Ray(new Point(0, 0, 0),dir);
        Cylinder c = new Cylinder(1, r, 10);
        Point p = new Point(0, 1, 2);
        Vector nrl = new Vector(0, 1, 0);
        assertTrue(nrl.equals(c.getNormal(p)), "ERROR: getNormal(Point) of Cylinder does not work correctly");
        // ============ Equivalence Partitions Tests ==============
        // checks weather the getNormal function for Tube works correctly for P on one of the bases
        Point p1 = new Point(1, 0, 0);
        assertTrue((dir.scale(-1)).equals(c.getNormal(p1)),"ERROR: getNormal(Point) of Cylinder does not work correctly if P is on one of the bases");
        // ============ Equivalence Partitions Tests ==============
        // checks weather the getNormal function for Tube works correctly for P on the second base
        Point p2 = new Point(1,0,10);
        assertTrue(dir.equals(c.getNormal(p2)),"ERROR: getNormal(Point) of Cylinder does not work correctly if P is on one of the bases");
        // =============== Boundary Values Tests ==================
        //checks weather the getNormal function for Tube works correctly in case that P equals to one of the bases center
        assertTrue((dir.scale(-1)).equals(c.getNormal(new Point(0,0,0))),"ERROR: getNormal(Point) of Cylinder does not work correctly if P is on one of the bases center");
        // =============== Boundary Values Tests ==================
        //checks weather the getNormal function for Tube works correctly in case that P equals to the second base center
        assertTrue((dir).equals(c.getNormal(new Point(0,0,10))),"ERROR: getNormal(Point) of Cylinder does not work correctly if P is on the second base center");
    }
    /**
     * Test method for {@link Cylinder#findIntsersections(Ray)}.
     */
    @Test
    void testFindIntersectionPoints() {

    }
}