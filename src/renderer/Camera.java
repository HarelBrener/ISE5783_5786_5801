package renderer;
import org.w3c.dom.xpath.XPathResult;
import primitives.*;

import java.util.MissingResourceException;

/**
 * This class represents a camera in a 3D environment.
 */
public class Camera {

    /**
     * The camera's position.
     */
    private Point p0;

    /**
     * The right vector of the camera.
     */
    private Vector vRight;

    /**
     * The up vector of the camera.
     */
    private Vector vUp;

    /**
     * The "to" vector of the camera.
     */
    private Vector vTo;

    /**
     * The height of the viewPlane.
     */
    private double height;

    /**
     * The width of the viewPlane.
     */
    private double width;

    /**
     * The distance between the camera and the viewPlane.
     */
    private double distance;

    private RayTracerBase rayTracer;
    private ImageWriter imageWriter;

    /**
     * Returns the camera and initializes rayTracer.
     *
     * @param rayTracer the rayTracer to be set
     * @return The camera object
     */
    public Camera setRayTracer(RayTracerBase rayTracer) {
        this.rayTracer = rayTracer;
        return this;
    }

    /**
     * Sets the ImageWriter for the Camera.
     *
     * @param imageWriter the ImageWriter to be set
     * @return This Camera object
     */
    public Camera setImageWriter(ImageWriter imageWriter) {
        this.imageWriter = imageWriter;
        return this;
    }

    /**
     * Returns the camera's position.
     *
     * @return The camera's position.
     */
    public Point getP0() {
        return p0;
    }

    /**
     * Returns the right vector of the camera.
     *
     * @return The right vector of the camera.
     */
    public Vector getvRight() {
        return vRight;
    }

    /**
     * Returns the up vector of the camera.
     *
     * @return The up vector of the camera.
     */
    public Vector getvUp() {
        return vUp;
    }

    /**
     * Returns the "to" vector of the camera.
     *
     * @return The "to" vector of the camera.
     */
    public Vector getvTo() {
        return vTo;
    }

    /**
     * Returns the height of the viewPlane.
     *
     * @return The height of the viewPlane.
     */
    public double getHeight() {
        return height;
    }

    /**
     * Returns the width of the viewPlane.
     *
     * @return The width of the viewPlane.
     */
    public double getWidth() {
        return width;
    }

    /**
     * Returns the distance between the camera and the viewPlane.
     *
     * @return The distance between the camera and the viewPlane.
     */
    public double getDistance() {
        return distance;
    }

    /**
     * Constructs a new Camera object with the specified position, "to" vector, and up vector.
     *
     * @param p0  The position of the camera.
     * @param vTo The "to" vector of the camera.
     * @param vUp The up vector of the camera.
     * @throws IllegalArgumentException If the "to" and up vectors are not orthogonal.
     */
    public Camera(Point p0, Vector vTo, Vector vUp) throws IllegalArgumentException {
        this.vUp = vUp.normalize();
        this.vTo = vTo.normalize();
        this.p0 = p0;

        // Calculate the right vector of the camera
        if (vUp.dotProduct(vTo) == 0) {
            this.vRight = ((vUp.crossProduct(vTo)).normalize()).scale(-1);
        } else {
            throw new IllegalArgumentException("ERROR: the vectors aren't orthogonal");
        }
    }

    /**
     * Sets the size of the viewPlane.
     *
     * @param width  The width of the viewPlane.
     * @param height The height of the viewPlane.
     * @return This Camera object.
     */
    public Camera setVPSize(double width, double height) {
        this.width = width;
        this.height = height;
        return this;
    }

    /**
     * Sets the distance between the camera and the viewPlane.
     *
     * @param distance The distance between the camera and the viewPlane.
     * @return This Camera object.
     */

    public Camera setVPDistance(double distance) {
        this.distance = distance;
        return this;
    }

    /**
     * Constructs a Ray from the camera's viewpoint through a specified pixel on the view plane.
     *
     * @param nX the number of pixels in the x-direction
     * @param nY the number of pixels in the y-direction
     * @param j  the column number of the pixel on the view plane (0-based index)
     * @param i  the row number of the pixel on the view plane (0-based index)
     * @return the Ray passing through the specified pixel on the view plane from the camera's viewpoint
     */
    public Ray constructRay(int nX, int nY, int j, int i) {
        Point pc = p0.add(vTo.scale(distance));
        double Ry = height / nY;
        double Rx = height / nX;
        double yi = -(i - (double) (nY - 1) / 2) * Ry;
        double xj = (j - (double) (nX - 1) / 2) * Rx;
        Point pij = pc;
        if (xj != 0)
            pij = pij.add(vRight.scale(xj));
        if (yi != 0)
            pij = pij.add(vUp.scale(yi));
        return new Ray(p0, pij.subtract(p0));
    }


    /**
     * Renders the image based on the camera's settings and the provided resources.
     * Throws a MissingResourceException if any required field is empty.
     */
    public void renderImage() {
        if (p0 == null || vRight == null || vUp == null || vTo == null || rayTracer == null || imageWriter == null || width == 0 || height == 0 || distance == 0)
            throw new MissingResourceException("at least one field is empty", "Camera", "renderImage");
        for(int i = 0; i < imageWriter.getNy(); i++)
            for(int j = 0; j < imageWriter.getNx(); j++){
                imageWriter.writePixel(j,i,castRay(i,j));
            }
    }

    /**
     * Prints a grid pattern on the image at a specified interval using the provided color.
     * <p>
     * Throws a MissingResourceException if the imageWriter is null.
     *
     * @param interval the interval at which the grid pattern is printed
     * @param color    the color of the grid pattern
     */
    public void printGrid(int interval, Color color) {
        if (imageWriter == null)
            throw new MissingResourceException("imageWriter is null", "Camera", "printGrid");

        for (int i = 0; i < imageWriter.getNy(); i++) {
            for (int j = 0; j < imageWriter.getNx(); j++) {
                if ((i % interval) == 0 || (j % interval) == 0) {
                    imageWriter.writePixel(j, i, color);
                }
            }
        }
    }

    /**
     * Writes the image to the output file using the imageWriter.
     * Throws a MissingResourceException if the imageWriter is null.
     */
    public void writeToImage() {
        if (imageWriter == null)
            throw new MissingResourceException("imageWriter is null", "Camera", "writeToImage");
        imageWriter.writeToImage();
    }

    /**
     * Casts a ray through the specified pixel coordinates and traces it using the rayTracer.
     *
     * @param i the row number of the pixel (0-based index)
     * @param j the column number of the pixel (0-based index)
     * @return the color result of tracing the ray through the pixel
     */
    private Color castRay(int i, int j){
            Ray r = constructRay(imageWriter.getNx(), imageWriter.getNy(),j,i);
            return rayTracer.traceRay(r);
    }
}



