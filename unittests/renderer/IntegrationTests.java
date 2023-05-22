/**
 The renderer package contains classes for rendering and image creation
 */
        package renderer;
        import geometries.*;
        import org.junit.jupiter.api.Test;
        import primitives.Point;
        import primitives.Vector;

        import java.util.List;

        import static org.junit.jupiter.api.Assertions.*;

/**
 The IntegrationTests class contains unit tests for integrations with shapes
 @author Yedidya & Harel
 */
class IntegrationTests {

    final int num = 3;

    /**
     The checkFISize() method performs intersection calculations and asserts the result is equal to the expected number of intersections
     @param object The Geometry object to calculate intersections with
     @param camera The Camera object to use for ray creation
     @param exSum The expected number of intersections
     */
    void checkFISize(Geometry object, Camera camera , int exSum){
        int sum = 0;
        List<Point> l = null;
        for (int i=0;i<num;i++){
            for (int j=0; j<num;j++) {
                l = object.findIntsersections(camera.constructRay(num, num, j, i));
                if ( l != null)
                    sum += l.size();
            }
        }
        assertEquals(sum,exSum,"number of intersections with shapes");
    }

    //Tests the intersection of a camera with spheres.
    @Test
    void CameraSphereIntersections(){
        //TC01: Checks whether the number of intersections with sphere is correct (should be 2).
        Camera camera = new Camera(new Point(0,0,0),new Vector(0,0,-1),new Vector(0,1,0)).setVPDistance(1).setVPSize(3,3);
        Sphere sphere = new Sphere(1,new Point(0,0,-3));
        checkFISize(sphere,camera,2);

        //TC02: Checks whether the number of intersections with sphere is correct (should be 9).
        sphere = new Sphere(4,new Point(0,0,-1));
        checkFISize(sphere,camera,9);

        //TC03: Checks whether the number of intersections with sphere is correct (should be zero).
        sphere = new Sphere(0.5,new Point(0,0,1));
        checkFISize(sphere,camera,0);

        //TC04: Checks whether the number of intersections with sphere is correct (should be 18).
        camera = new Camera(new Point(0,0,0.5),new Vector(0,0,-1),new Vector(0,1,0)).setVPDistance(1).setVPSize(3,3);
        sphere = new Sphere(2.5,new Point(0,0,-2.5));
        checkFISize(sphere,camera,18);

        //TC05: Checks whether the number of intersections with sphere is correct (should be 10).
        sphere = new Sphere(2,new Point(0,0,-2));
        checkFISize(sphere,camera,10);
    }

    //Tests the intersection of a camera with planes.
    @Test
    void CameraPlaneIntersections(){
        //TC01: Checks whether the number of intersections with plane is correct (should be 9).
        Camera camera = new Camera(new Point(0,0,0),new Vector(0,0,-1),new Vector(0,1,0)).setVPDistance(1).setVPSize(3,3);
        Plane p = new Plane(new Point(0,0,-3),new Vector(0,0,1));
        checkFISize(p,camera,9);

        //TC02: Checks whether the number of intersections with plane is correct (should be 9).
        p = new Plane(new Point(0,-8,0),new Vector(0,1,4));
        checkFISize(p,camera,9);

        //TC03: Checks whether the number of intersections with plane is correct (should be 6).
        p = new Plane(new Point(0,-8,0),new Vector(0,1,1));
        checkFISize(p,camera,6);
    }

    //Tests the intersection of a camera with triangles.
    @Test
    void CameraTriangleIntersections(){
        //TC01: Checks whether the number of intersections with triangle is correct (should be 1).
        Camera camera = new Camera(new Point(0,0,0),new Vector(0,0,-1),new Vector(0,1,0)).setVPDistance(1).setVPSize(3,3);
        Triangle tr = new Triangle(new Point(0,1,-2),new Vector(-1,-1,-2),new Point(1,-1,-2));
        checkFISize(tr,camera,1);

        //TC02: Checks whether the number of intersections with triangle is correct (should be 2).
        tr = new Triangle(new Point(0,20,-2),new Vector(-1,-1,-2),new Point(1,-1,-2));
        checkFISize(tr,camera,2);
    }
}
