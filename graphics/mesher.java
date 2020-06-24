package NewTest.graphics;



public class mesher {
    tria[] trias;

    public mesher(int sz) {
        trias = new tria[sz];
    }

    public mesher() { //cube
        int coll = 0xff00ff;
        /*
        vertex a = new vertex((float)-.5, (float)-.5, 0);
        vertex b = new vertex((float)-.5, (float).5, 0);
        vertex c = new vertex((float).5, (float)-.5, 0);
        vertex d = new vertex((float).5, (float).5, 0);
        vertex e = new vertex((float)-.5, (float)-.5, 1);
        vertex f = new vertex((float)-.5, (float).5, 1);
        vertex g = new vertex((float).5, (float)-.5, 1);
        vertex h = new vertex((float).5, (float).5, 1);
        */

        vertex a = new vertex(0, 0, 0);
        vertex b = new vertex(0, 1, 0);
        vertex c = new vertex(1, 0, 0);
        vertex d = new vertex(1, 1, 0);
        vertex e = new vertex(0, 0, 1);
        vertex f = new vertex(0, 1, 1);
        vertex g = new vertex(1, 0, 1);
        vertex h = new vertex(1, 1, 1); 

        tria ta = new tria(a, b, d, coll);
        tria tb = new tria(a,d,c,coll);
        tria tc = new tria(c,d,h,coll);
        tria td = new tria(c,h,g,coll);
        tria te = new tria(g,h,f,coll);
        tria tf = new tria(g,f,e,coll);
        tria tg = new tria(e,f,b,coll);
        tria th = new tria(e,b,a,coll);
        tria ti = new tria(e,a,c,coll);
        tria tj = new tria(e,c,g,coll);
        tria tk = new tria(h,d,b,coll);
        tria tl = new tria(h,b,f,coll);

        trias = new tria[] {ta,tb,tc,td,te,tf,tg,th,ti,tj,tk,tl};

    }

    public mesher(mesher x) {
        this.trias = new tria[x.trias.length];
        for (int i = 0; i<this.trias.length;i++) this.trias[i] = new tria(x.trias[i]);
    }

    public void addtri(tria x, int ind) {
        trias[ind] = x;
    }

    public void addz(float z) {
        for (tria i : trias) i.addz(z);
    }
    
    public void projM(float a, float f, float q, float zn, float znq, int scrH, int scrW) {
        for (tria i : trias) i.projT(a, f, q, zn, znq, scrH, scrW);
    }

    public void projM(float[][] mat, float znq, int scrW, int scrH) {
        for (tria i : trias) i.projT(mat, znq, scrW, scrH);
    } 

    public void mmult(float[][] mat) {
        for (tria i : trias) i.mmult(mat);
    }

}