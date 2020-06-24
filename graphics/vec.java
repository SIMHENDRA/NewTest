package NewTest.graphics;

public class vec {
    public float[] v;

    public vec(float[] x) {
        v = new float[x.length];
        for (int i = 0;i<x.length;i++) v[i] = x[i];  
    }

    public vec(float x, float y, float z) {
        v = new float[3];
        v[0] = x;
        v[1] = y;
        v[2] = z;
    }

    public vec(int len) {
        v = new float[len];
    }

    public static float dp(vec a, vec b) {
        float ret = 0;
        for (int i = 0;i<a.v.length;i++) ret+= a.v[i]*b.v[i];
        return ret;
    }

    public static vec cp(vec a, vec b) {
        float a1 = a.x();
        float a2 = a.y();
        float a3 = a.z();
        float b1 = b.x();
        float b2 = b.y();
        float b3 = b.z();

        return new vec(new float[]{(a2*b3 - a3*b2),(a3*b1 - a1*b3),(a1*b2 - a2*b1)});
    }

    public static matr vmmult(vec a, matr b) {
        matr aa = new matr(1,a.v.length);
        aa.m[0] = a;
        return matr.mmultf(aa,b);
    }

    public static matr vvmult(vec a, vec b) {
        matr aa = new matr(1,a.v.length);
        aa.m[0] = a;
        matr bb = new matr(1,b.v.length);
        bb.m[0] = b;
        return matr.mmultf(aa,bb);
    }

    public void printv() {
        for (float i : this.v) System.out.printf("%.2f  ", i);
        System.out.println();
    }

    public vec vcpy() {
        int len = this.v.length;
        vec ret = new vec(len);
        for (int i = 0; i<len;i++) ret.v[i] = this.v[i];
        return ret;
    }

    public matr tptoMatr() {
        matr ret = new matr(this.v.length,1);
        for (int i = 0; i<this.v.length;i++) ret.m[i] = new vec(new float[]{this.v[i]});
        return ret;
    }

    public float x() {
        return v[0];
    }

    public float y() {
        return v[1];
    }

    public float z() {
        return v[2];
    }

    public void x(float i) {
        v[0] = i;
    }

    public void y(float i) {
        v[1] = i;
    }

    public void z(float i) {
        v[2] = i;
    }

    

}