package NewTest.graphics;

public class tria {
    
    public vertex[] verts = new vertex[3];
    public int coll = 0xff00ff;

    public tria(vertex a, vertex b, vertex c, int coll) {
        this.verts[0] = a;
        this.verts[1] = b;
        this.verts[2] = c;
        this.coll = coll;
    }

    public tria(tria x) {
        this.verts[0] = new vertex(x.verts[0]);
        this.verts[1] = new vertex(x.verts[1]);
        this.verts[2] = new vertex(x.verts[2]);
    }

    public void projT(float a, float f, float q, float zn, float znq, int scrH, int scrW) {
        for (vertex i : verts) i.projV(a, f, q, zn, znq, scrH, scrW);
    }

    public void addz(float z) {
        for (vertex i : verts) i.addz(z);
    }

    public void projT(float[][] mat, float znq, int scrW, int scrH) {
        for (vertex i : verts) i.projV(mat, znq, scrW, scrH);
    }

    public void mmult(float[][] mat) {
        for (vertex i : verts) i.mmult(mat);
    }
        
    

}