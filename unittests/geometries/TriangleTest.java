package geometries;

import org.junit.jupiter.api.Test;
import primitives.Double3;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TriangleTest {
    /**
     * Test method for {@link geometries.Triangle#getNormal(Point)}.
     */
    @Test
    void getNormal() {
        // ============ Equivalence Partitions Tests ==============
        //TC01: checks weather the getNormal function of Triangle works correctly
        Triangle trg = new Triangle(new Point(1, 0 ,1), new Point(4, 0 ,12), new Point(-1, 0 , 3));
        Vector nrml = new Vector(0,1,0);
        Vector res = trg.getNormal(new Point(1,0,1));
        assertTrue(nrml.equals(res) || nrml.equals(res.scale(-1)), "ERROR: the function getNormal does not work proper");
    }

    /**
     * Test method for {@link Triangle#findIntsersections(Ray)}.
     */
    @Test
    void testFindIntersectionPoints(){
        Triangle trngl = new Triangle(new Point(1, 0 ,0), new Point(0, 1 ,0), new Point(0, -1 , 0));
        Point p0 = new Point(0,0,1);//Ray's (and vector's) start point
        Point p;//intersection point
        Vector v;//Ray's direction vector
        List<Point> result;
        // ============ Equivalence Partitions Tests ==============
        //TC01: The ray intersects the triangle (1 point)
        p = new Point(0.3,0.3,0);
        v = new Vector(0.3,0.3,-1);
        result = trngl.findIntsersections(new Ray(p0,v));
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