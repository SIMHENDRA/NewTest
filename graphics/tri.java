package NewTest.graphics;

public class tri {
    //todo: Don't do matr extension 
    matr verts;

    public tri() {
        verts = new matr(3,3);
    }
    
    public tri(vec a, vec b, vec c) {
        verts = new matr(3,a.v.length);
        verts.m[0] = a;
        verts.m[1] = b;
        verts.m[2] = c;
    }

    public tri(matr x) {
        verts = new matr(3,x.m[0].v.length);
        verts.m[0] = x.m[0];
        verts.m[0] = x.m[1];
        verts.m[0] = x.m[2];
    }

    public vec normal() {
        return vec.cp(verts.m[0],verts.m[1]);
    }

}