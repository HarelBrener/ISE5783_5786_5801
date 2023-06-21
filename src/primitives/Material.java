package primitives;

import java.lang.reflect.Member;

public class Material {
    /**
     * The diffuse coefficient of the material.
     * Default value is (0, 0, 0).
     */
    public Double3 kD = new Double3(0);

    /**
     * The specular coefficient of the material.
     * Default value is (0, 0, 0).
     */
    public Double3 kS = new Double3(0);

    /**
     * The shininess of the material.
     * Default value is 0.
     */
    public int nShininess = 0;

    /**
     * Sets the diffuse coefficient of the material.
     *
     * @param kD The diffuse coefficient to be set for the material.
     * @return The current Material object.
     */
    public Material setKd(Double3 kD) {
        this.kD = kD;
        return this;
    }

    /**
     * Sets the specular coefficient of the material.
     *
     * @param kS The specular coefficient to be set for the material.
     * @return The current Material object.
     */
    public Material setKs(Double3 kS) {
        this.kS = kS;
        return this;
    }

    /**
     * Sets the diffuse coefficient of the material.
     *
     * @param kD The diffuse coefficient to be set for the material.
     * @return The current Material object.
     */
    public Material setKd(double kD) {
        this.kD = new Double3(kD);
        return this;
    }

    /**
     * Sets the specular coefficient of the material.
     *
     * @param kS The specular coefficient to be set for the material.
     * @return The current Material object.
     */
    public Material setKs(double kS) {
        this.kS = new Double3(kS);
        return this;
    }

    /**
     * Sets the shininess of the material.
     *
     * @param nShininess The shininess to be set for the material.
     * @return The current Material object.
     */
    public Material setShininess(int nShininess) {
        this.nShininess = nShininess;
        return this;
    }

    /**
     * Sets the transparency coefficient of the material.
     *
     * @param kT The transparency coefficient to be set for the material.
     * @return The current Material object.
     */
    public Material setkT(Double3 kT) {
        this.kT = kT;
        return this;
    }

    /**
     * Sets the reflection coefficient of the material.
     *
     * @param kR The reflection coefficient to be set for the material.
     * @return The current Material object.
     */
    public Material setkR(Double3 kR) {
        this.kR = kR;
        return this;
    }

    /**
     * Sets the transparency coefficient of the material.
     *
     * @param kT The transparency coefficient to be set for the material.
     * @return The current Material object.
     */
    public Material setkT(double kT) {
        this.kT = new Double3(kT);
        return this;
    }

    /**
     * Sets the reflection coefficient of the material.
     *
     * @param kR The reflection coefficient to be set for the material.
     * @return The current Material object.
     */
    public Material setkR(double kR) {
        this.kR = new Double3(kR);
        return this;
    }
}

