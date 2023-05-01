package geometries;

import org.junit.jupiter.api.Test;
import primitives.Double3;
import primitives.Point;
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
        // checks weather the getNormal function of Sphere works correctly
        Sphere sp = new Sphere(2, new Point(0,0,0));
        Point p = new Point(2,0,0);
        Vector nrl = new Vector(1,0,0);
        assertTrue(nrl.equals(sp.getNormal(p)),"ERROR: getNormal(Point) of Sphere does not work correctly");
    }
}