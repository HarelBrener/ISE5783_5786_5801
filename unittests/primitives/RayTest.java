package primitives;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RayTest {

    /**
     * Test method for {@link primitives.Ray#findClosestPoint(List)}.
     */
    @Test
    void findClosestPoint() {
        List<Point> l = null;
        Ray ray = new Ray(new Point(0,0,0),new Vector(0,0,1));
        // ============ Equivalence Partitions Tests ==============
        //TC01: point in the middle point of the list
        Point p1 = new Point(2,2,2);
        Point p2 = new Point(1,1,1);
        Point p3 = new Point(3,3,3);
        l = List.of(p1, p2, p3);
        assertEquals(ray.findClosestPoint(l),p2,"point in the middle point of the list");
        // =============== Boundary Values Tests ==================
        //TC11: empty list (return null)
        l = null;
        assertNull(ray.findClosestPoint(l),"list is empty");
        //TC12: the first point is the closest to P0
        l = List.of(p2,p1,p3);
        assertEquals(ray.findClosestPoint(l),p2,"The first point is the closest to P0");
        //TC13: the last point is the closest to P0
        l = null;
        l = List.of(p3,p1,p2);
        assertEquals(ray.findClosestPoint(l),p2, "The last point is the closest to P0");
    }
}