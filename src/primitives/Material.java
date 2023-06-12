package primitives;

public class Material {
    public Double3 kD = new Double3(0);

    public Double3 kS = new Double3(0);

    public int nShininess = 0;

    public void setkD(Double3 kD) {
        this.kD = kD;
        return ;
    }

    public void setkS(Double3 kS) {
        this.kS = kS;
    }

    public void setnShininess(int nShininess) {
        this.nShininess = nShininess;
    }
}

