package NewTest.graphics;

public class matr {
    public vec[] m;

    public matr() {
        m = new vec[3];
        m[0] = new vec(3);
        m[1] = new vec(3);
        m[2] = new vec(3);
    }
    
    public matr(vec a, vec b, vec c) {
        m = new vec[3];
        m[0] = a;
        m[1] = b;
        m[2] = c;
    }

    public matr(matr x) { //Requires certain dimensions: 3x3
        m = new vec[3];
        m[0] = x.m[0];
        m[0] = x.m[1];
        m[0] = x.m[2];
    }

    public vec normal() {
        return vec.cp(m[0],m[1]);
    }

    public matr(int r, int c) {
        m = new vec[r];
        for (int i = 0; i<m.length; i++) m[i] = new vec(c);
    }

    public float gete(int r, int c) {
        return this.m[r].v[c];
    }

    public void sete(int r, int c, float x) {
        this.m[r].v[c] = x;
    }

    public vec getrow(int y) {
        vec ret = new vec(m.length);
        for (int i = 0; i<ret.v.length;i++) ret.v[i] = this.gete(i,y);
        return ret;
    }
    
    public static matr mmultf(matr a, matr b) { //A * B
        int dimx = a.m[0].v.length;
        int dimy = b.m.length;
        matr ret = new matr(dimx, dimy);

        for (int i = 0;i<dimx;i++) {
            for (int j = 0;j<dimy;j++) {
                ret.sete(i, j, vec.dp(a.m[i],b.getrow(j)));
            }
        }
        return ret;       
    }

    

    public vec toVec() { //1 length matr to vec

        if (m.length == 1) {
            vec ret = new vec(m[0].v.length);
            for (int i = 0; i<ret.v.length;i++) ret.v[i] = m[0].v[i];
            return ret;
        }
        return null;
    }

    public matr mcpy() {
        int l = this.m.length;
        int w = this.m[0].v.length;
        matr ret = new matr(l,w);
        for (int i = 0;i<l;i++) ret.m[i] = this.m[i].vcpy();
        return ret;
    }
/* 
    public void tp() {
        vec[] nm = new vec[this.m[0].v.length];
        vec[] mcpy = new vec[this.m.length];
        for (int i = 0; i<this.m.length; i++) mcpy 


    } */

    static matr tp(matr x) { //transpose
        int nl = x.m[0].v.length;
        int nw = x.m.length;

        matr ret = new matr(nl, nw);

        for (int i = 0;i<nl;i++) ret.m[i] = x.getrow(i);
        
        return ret;

    }

    public vec tptoVec() {
        return tp(this).toVec();
    }

    public void printm() {
        for (vec a : this.m) a.printv();
        //System.out.printf("\n");
        System.out.println();                
        /* int x = m.length;
        int y = m[0].v.length;

        for (int i = 0; i<x;i++) {
            for (int j = 0; j<y;j++) {
                System.out.printf("%f  ", gete(i,j));
            }
            System.out.println();
        } */
    }

    public static void main(String[] args) {
        /*
        matr X = new matr(4,4);
        for (int i = 0;i<4;i++) {
            for (int j = 0;j<4;j++) {
                X.sete(i,j,((float)j+i*4));
            }
        }
        X.printm();
        System.out.println();

        matr II = new matr(4,4);
        for (int l = 0;l<4;l++) II.sete(l,l,1);
        II.printm();
        System.out.println();
        
        (mmultf(X, II)).printm();
        System.out.println();

        vec yy = new vec(3);
        matr YY = new matr(1,3);
        YY.m[0] = yy;
        YY.printm();
        System.out.println();

        (mmultf(tp(YY), X)).printm();
        System.out.println();

        tp(X).printm();
        System.out.println();

        X.mcpy().printm();
        */
        vec a = new vec(new float[] {2,2,2});
        matr B = new matr(new vec(new float[] {1,0,0}),new vec(new float[] {0,1,0}),new vec(new float[] {0,0,1}));
        a.printv();
        System.out.println("B:");
        B.printm();
        System.out.println("A:");

        matr A = a.tptoMatr();
        A.printm();
        
        System.out.println("PostMult:");
        mmultf(A,B).tptoVec().printv();
        //vec b = A.tptoVec();
        //b.printv();
    }

}