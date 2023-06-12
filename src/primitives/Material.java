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
    public Material setkD(Double3 kD) {
        this.kD = kD;
        return this;
    }

    /**
     * Sets the specular coefficient of the material.
     *
     * @param kS The specular coefficient to be set for the material.
     * @return The current Material object.
     */
    public Material setkS(Double3 kS) {
        this.kS = kS;
        return this;
    }

    /**
     * Sets the diffuse coefficient of the material.
     *
     * @param kD The diffuse coefficient to be set for the material.
     * @return The current Material object.
     */
    public Material setkD(double kD) {
        this.kD = new Double3(kD);
        return this;
    }

    /**
     * Sets the specular coefficient of the material.
     *
     * @param kS The specular coefficient to be set for the material.
     * @return The current Material object.
     */
    public Material setkS(double kS) {
        this.kS = new Double3(kS);
        return this;
    }

    /**
     * Sets the shininess of the material.
     *
     * @param nShininess The shininess to be set for the material.
     * @return The current Material object.
     */
    public Material setnShininess(int nShininess) {
        this.nShininess = nShininess;
        return this;
    }

}

