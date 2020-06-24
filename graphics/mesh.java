package NewTest.graphics;

public class mesh {
    public matr[] tris;
    public int coll = 0xff00ff;

    public mesh(int sz) {
        tris = new matr[sz];
    }

    public mesh() { //cube constructer
        vec a = new vec(0, 0, 0);
        vec b = new vec(0, 1, 0);
        vec c = new vec(1, 0, 0);
        vec d = new vec(1, 1, 0);
        vec e = new vec(0, 0, 1);
        vec f = new vec(0, 1, 1);
        vec g = new vec(1, 0, 1);
        vec h = new vec(1, 1, 1); 

        matr ta = new matr(a,b,d);
        matr tb = new matr(a,d,c);
        matr tc = new matr(c,d,h);
        matr td = new matr(c,h,g);
        matr te = new matr(g,h,f);
        matr tf = new matr(g,f,e);
        matr tg = new matr(e,f,b);
        matr th = new matr(e,b,a);
        matr ti = new matr(e,a,c);
        matr tj = new matr(e,c,g);
        matr tk = new matr(h,d,b);
        matr tl = new matr(h,b,f);

        tris = new matr[] {ta,tb,tc,td,te,tf,tg,th,ti,tj,tk,tl};
    }

    public mesh meshcpy() {
        int tl = tris.length;
        mesh ret = new mesh(tl);
        for (int i = 0; i<tl ; i++) ret.tris[i] = new matr(this.tris[i].mcpy());
        return ret;
    }

    public mesh transcpy(matr A) {
        mesh ret = this.meshcpy();
        
        for (matr i : ret.tris) {
            for (vec j : i.m) {
                j = matr.mmultf(j.tptoMatr(), A).tptoVec();
            }
        }
        return ret;
    }

    public void transform(matr A) {                
        for (matr i : this.tris) {
            for (vec j : i.m) {
                j = matr.mmultf(j.tptoMatr(), A).tptoVec();
            }
        }        
    }

    public void transhelp(float znq, float h, float w) {
        for (matr i : this.tris) {
            for (vec j : i.m) {
                j.z(j.z() + znq);
                j.x(((j.x()/j.z()) + 1)*(float).5*w);
                j.y(((j.y()/j.z()) + 1)*(float).5*h);

            }
        }
    }

    public void printmesh() {
        for (matr x : tris) x.printm();
    }

    public static void main(String[] args) {
        mesh AA = new mesh();
        AA.printmesh();
    }


}