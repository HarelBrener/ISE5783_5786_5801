package primitives;

import org.junit.jupiter.api.Test;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for primitives.Point class
 * @author Yedidya & Harel
 */
public class PointTests {

    Point p1 = new Point(1,2,3);
    /**
     * Test method for {@link primitives.Point#subtract(primitives.Point)}.
     */
    @Test
    void testSubtract() {
        // ============ Equivalence Partitions Tests ==============
        //checks weather the sub of point and vector works proper
        assertTrue(new Vector(1, 1, 1).equals(new Point(2, 3, 4).subtract(p1)),"ERROR: Point - Point does not work correctly");
    }

    /**
     * Test method for {@link primitives.Point#add(primitives.Vector)}.
     */
    @Test
    void testAdd() {
        // ============ Equivalence Partitions Tests ==============
        //checks weather the addition of point and vector works proper
        Vector v2 = new Vector(-1,-2,-3);
        assertTrue((p1.add(v2)).equals(new Point(0,0,0)),"ERROR: Point + Vector does not work correctly");
    }
    /**
     * Test method for {@link primitives.Point#distanceSquared(primitives.Point)}.
     */
    @Test
    void testDistanceSquared() {
        // ============ Equivalence Partitions Tests ==============
        //checks weather the DistanceSquared function between 2 points works proper
        Point p2 = new Point (2,3,4);
        double x = p1.distanceSquared(p2);
        assertEquals(3, x,"ERROR: distanceSquared between two points does not work correctly");

    }
    /**
     * Test method for {@link primitives.Point#distance(primitives.Point)}.
     */
    @Test
    void testDistance() {
        // ============ Equivalence Partitions Tests ==============
        //checks weather the distance function between 2 points works
        Point p2 = new Point (2,3,4);
        double x = p1.distance(p2);
        assertEquals(Math.sqrt(3), x,"ERROR: distance between two points does not work correctly");
    }
}