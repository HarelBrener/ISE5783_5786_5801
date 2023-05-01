package geometries;

import org.junit.jupiter.api.Test;

import java.util.List;
import primitives.*;
import geometries.*;

import static org.junit.jupiter.api.Assertions.*;

class GeometriesTest {

    /**
     * Test method for {@link Ge#findIntsersections(Ray)}.
     */
    @Test
    void findIntersections() {
        Geometries intrsc;
        Triangle trngl = new Triangle(new Point(1, 0 ,0), new Point(0, 1 ,0), new Point(0, -1 , 0));
        Plane pln = new Plane(new Point(1, 0 ,0), new Point(0, 1 ,0), new Point(0, 0.5 , 0));
        Sphere sphere = new Sphere(1, new Point (1, 0.5, 0));
        intrsc = new Geometries(trngl,pln,sphere);
        Vector dir = new Vector(0,0,-1);
        // ============ Equivalence Partitions Tests ==============
        //TC01: Some shapes are intersected, but not all of them
        Ray r = new Ray(new Point(1,1,2), dir);
        Point p = new Point(1,1,0);
        assertEquals(intrsc.findIntersections(r).size(),3,"Some shapes are intersected, but not all of them");
        // =============== Boundary Values Tests ==================
        //TC11: Shapes collection is empty
        intrsc = new Geometries();
        assertNull(intrsc.findIntersections(r), "Shapes collection is empty");
        //TC12: No shape is intersected
        intrsc = new Geometries(trngl,pln,sphere);
        r = new Ray(new Point(3,3,3),new Vector(1,0,0));
        assertNull(intrsc.findIntersections(r),"No shape is intersected");
        //TC13: One shape is intersected
        r = new Ray(new Point(3,-3,3), dir);
        p = new Point(3,-3,0);
        assertEquals(intrsc.findIntersections(r).size(),1,"One shape is intersected");
        //TC14: All the shapes are intersected
        r = new Ray(new Point(0.3,0.3,0.1),dir);
        p = new Point(0.3,0.3,0);
        assertEquals(intrsc.findIntersections(r).size(),2,"All the shapes are intersected");
    }
}