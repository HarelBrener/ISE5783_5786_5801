package geometries;

import org.junit.jupiter.api.Test;
import primitives.Double3;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class TriangleTest {
    /**
     * Test method for {@link geometries.Triangle#getNormal(Point)}.
     */
    @Test
    void getNormal() {
        // ============ Equivalence Partitions Tests ==============
        // checks weather the getNormal function of Triangle works correctly
        Triangle trg = new Triangle(new Point(1, 0 ,1), new Point(4, 0 ,12), new Point(-1, 0 , 3));
        Vector nrml = new Vector(0,1,0);
        Vector res = trg.getNormal(new Point(1,0,1));
        assertTrue(nrml.equals(res), "ERROR: the function getNormal does not work proper");
        assertTrue(nrml.equals(res.scale(-1)), "ERROR: the function getNormal does not work proper");
    }
}