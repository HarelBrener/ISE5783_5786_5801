/**
 *
 */
package renderer;

import static java.awt.Color.*;

import geometries.Plane;
import geometries.Polygon;
import org.junit.jupiter.api.Test;

import geometries.Sphere;
import geometries.Triangle;
import lighting.AmbientLight;
import lighting.SpotLight;
import primitives.*;
import primitives.Color;
import primitives.Point;
import renderer.*;
import scene.Scene;

import java.awt.*;

/** Tests for reflection and transparency functionality, test for partial
 * shadows
 * (with transparency)
 * @author dzilb */
public class ReflectionRefractionTests {
   private Scene scene = new Scene("Test scene");

   /** Produce a picture of a sphere lighted by a spot light */
   @Test
   public void twoSpheres() {
      Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
              .setVPSize(150, 150).setVPDistance(1000);

      scene.geometries.add( //
              new Sphere(50d, new Point(0, 0, -50)).setEmission(new Color(BLUE)) //
                      .setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100).setkT(0.3)),
              new Sphere(25d, new Point(0, 0, -50)).setEmission(new Color(RED)) //
                      .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)));
      scene.lights.add( //
              new SpotLight(new Color(1000, 600, 0), new Point(-100, -100, 500), new Vector(-1, -1, -2)) //
                      .setKl(0.0004).setKq(0.0000006));

      camera.setImageWriter(new ImageWriter("refractionTwoSpheres", 500, 500)) //
              .setRayTracer(new RayTracerBasic(scene)) //
              .renderImage() //
              .writeToImage();
   }

   /** Produce a picture of a sphere lighted by a spot light */
   @Test
   public void twoSpheresOnMirrors() {
      Camera camera = new Camera(new Point(0, 0, 10000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
              .setVPSize(2500, 2500).setVPDistance(10000); //

      scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0.1));

      scene.geometries.add( //
              new Sphere(400d, new Point(-950, -900, -1000)).setEmission(new Color(0, 50, 100)) //
                      .setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(20)
                              .setkT(new Double3(0.5, 0, 0))),
              new Sphere(200d, new Point(-950, -900, -1000)).setEmission(new Color(100, 50, 20)) //
                      .setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(20)),
              new Triangle(new Point(1500, -1500, -1500), new Point(-1500, 1500, -1500),
                      new Point(670, 670, 3000)) //
                      .setEmission(new Color(20, 20, 20)) //
                      .setMaterial(new Material().setkR(1)),
              new Triangle(new Point(1500, -1500, -1500), new Point(-1500, 1500, -1500),
                      new Point(-1500, -1500, -2000)) //
                      .setEmission(new Color(20, 20, 20)) //
                      .setMaterial(new Material().setkR(new Double3(0.5, 0, 0.4))));

      scene.lights.add(new SpotLight(new Color(1020, 400, 400), new Point(-750, -750, -150), new Vector(-1, -1, -4)) //
              .setKl(0.00001).setKq(0.000005));

      ImageWriter imageWriter = new ImageWriter("reflectionTwoSpheresMirrored", 500, 500);
      camera.setImageWriter(imageWriter) //
              .setRayTracer(new RayTracerBasic(scene)) //
              .renderImage() //
              .writeToImage();
   }

   /** Produce a picture of a two triangles lighted by a spot light with a
    * partially
    * transparent Sphere producing partial shadow */
   @Test
   public void trianglesTransparentSphere() {
      Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
              .setVPSize(200, 200).setVPDistance(1000);

      scene.setAmbientLight(new AmbientLight(new Color(WHITE), 0.15));

      scene.geometries.add( //
              new Triangle(new Point(-150, -150, -115), new Point(150, -150, -135),
                      new Point(75, 75, -150)) //
                      .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60)), //
              new Triangle(new Point(-150, -150, -115), new Point(-70, 70, -140), new Point(75, 75, -150)) //
                      .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60)), //
              new Sphere(30d, new Point(60, 50, -50)).setEmission(new Color(BLUE)) //
                      .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setkT(0.6)));

      scene.lights.add(new SpotLight(new Color(700, 400, 400), new Point(60, 50, 0), new Vector(0, 0, -1)) //
              .setKl(4E-5).setKq(2E-7));

      ImageWriter imageWriter = new ImageWriter("refractionShadow", 600, 600);
      camera.setImageWriter(imageWriter) //
              .setRayTracer(new RayTracerBasic(scene)) //
              .renderImage() //
              .writeToImage();
   }

   @Test
   public void photoWithObjects() {
      Camera camera = new Camera(new Point(0, 0, 70), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
              .setVPSize(400, 400).setVPDistance(1000);

      scene.setAmbientLight(new AmbientLight(new Color(123, 234, 23), 0.3));

      scene.geometries.add(//
                /*new Triangle(new Point(2, -5, 0), new Point(-2, -5, 0), new Point(0, 5, 5)) //ice cream corn
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60).setkT(0.4).setkR(0.6)).setEmission(new Color(16, 95, 28)),//
                new Triangle(new Point(-2, 5, 0), new Point(-2, -5, 0), new Point(0, 5, 5)) //ice cream corn
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60).setkR(0.4)).setEmission(new Color(186, 195, 128)),//
                new Triangle(new Point(2, -5, 0), new Point(2, 5, 0), new Point(0, 5, 5)) //ice cream corn
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60).setkR(0.4)).setEmission(new Color(186, 9, 2)), //
                new Triangle(new Point(2, 5, 0), new Point(-2, 5, 0), new Point(0, 5, 5)) //ice cream corn
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60).setkT(0.4)).setEmission(new Color(6, 95, 228)),//
                new Sphere(5d, new Point(0, 0, 0)).setEmission(new Color(12, 2, 220)) //ice cream melted
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setkT(0.6)));*/
              new Triangle(new Point(0, -3, 7), new Point(2, 5, 7), new Point(-2, 5, 7)) //ice cream corn
                      .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60).setkT(0.4)).setEmission(new Color(186, 95, 28)), //
              new Sphere(1.5d, new Point(0.5, 5.5, 0)).setEmission(new Color(245, 230, 220)) //ice cream
                      .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setkR(0.6)), //
              new Sphere(0.25d, new Point(0.3, 4.75, 9)).setEmission(new Color(245, 230, 220)) //ice cream melted
                      .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setkR(0.6)), //
              new Sphere(0.25d, new Point(0.35, 4.5, 9)).setEmission(new Color(245, 230, 220)) //ice cream melted
                      .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setkR(0.6)), //
              new Sphere(0.15d, new Point(0.3, 4.2, 9)).setEmission(new Color(245, 230, 220)) //ice cream melted
                      .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setkR(0.6)), //
              new Sphere(1d, new Point(-1.25, 5.75, 0)).setEmission(new Color(6, 200, 110)) //ice cream
                      .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setkT(0.6)), //
              new Sphere(0.25d, new Point(-1.75, 4.75, 9)).setEmission(new Color(6, 200, 110)) //ice cream melted
                      .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30)), //
              new Sphere(0.25d, new Point(-1.55, 4.45, 9)).setEmission(new Color(6, 200, 110)) //ice cream melted
                      .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setkR(0.6)), //
              new Sphere(0.15d, new Point(-1.5, 4.22, 9)).setEmission(new Color(6, 200, 110)) //ice cream melted
                      .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setkR(0.6)), //
              new Sphere(0.1d, new Point(-1.65, 4.1, 9)).setEmission(new Color(6, 200, 110)) //ice cream melted
                      .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setkR(0.6)), //
              new Sphere(0.1d, new Point(-1.65, 4, 9)).setEmission(new Color(6, 200, 110)) //ice cream melted
                      .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setkR(0.6)), //
              new Sphere(0.1d, new Point(-1.6, 3.9, 9)).setEmission(new Color(6, 200, 110)) //ice cream melted
                      .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setkR(0.6)), //
              new Sphere(0.5d, new Point(-0.3, 7, 0)).setEmission(new Color(230, 220, 7)) //ice cream
                      .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setkR(0.6)),//
              new Triangle(new Point(30, -30, -30), new Point(-30, 30, -30), new Point(-30, -30, -35))//base triangle down
                      .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60).setkR(0.6)).setEmission(new Color(34, 3, 123)),//
              new Triangle(new Point(30, -30, -30), new Point(-30, 30, -30), new Point(30, 30, -35)) //base triangle up
                      .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60).setkR(0.6)).setEmission(new Color(1, 123, 12)),
              new Sphere(2d, new Point(-8, -7, 6)).setEmission(new Color(255, 255, 70)) //melon
                      .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setkR(0.6)),
              new Sphere(3d, new Point(-7, -6, 0)).setEmission(new Color(0, 50, 0)) // water melon
                      .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setkR(0.6)), //
              new Triangle(new Point(-5.5, -8.5, 5), new Point(-5.2, -8.5, 5), new Point(-5.25, -7.8, 5))//tot
                      .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60).setkR(0.2)).setEmission(new Color(0, 50, 0)),
              new Triangle(new Point(-7, -9, 5), new Point(-5, -9, 5), new Point(-5.55, -7.8, 5))//tot
                      .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60).setkR(0.2)).setEmission(new Color(255, 0, 0)));


      scene.lights.add(new SpotLight(new Color(300, 400, 400), new Point(10, -10, 7),3d,10, new Vector(0, 0, -1)) //
              .setKl(4E-5).setKq(2E-7));

      ImageWriter imageWriter = new ImageWriter("iceBall", 600, 600);
      camera.setImageWriter(imageWriter) //
              .setRayTracer(new RayTracerBasic(scene)) //
              .renderImage() //
              .writeToImage();
   }
}
