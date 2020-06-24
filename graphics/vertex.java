package NewTest.graphics;


//CREATE MATRIX/VECTOR CLASS

public class vertex {
    public float x;
    public float y;
    public float z;

    public vertex(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public vertex(vertex x) {
        this.x = x.x;
        this.y = x.y;
        this.z = x.z;
    }

    public void addz(float z) {
        this.z += z;
    }

    public void projV(float a, float f, float q, float zn, float znq, int scrH, int scrW) {

        
        //System.out.printf("PRE x: %f y: %f z: %f  ", this.x, this.y, this.z);

        this.x = (a*f*(this.x));
        this.y = (f*(this.y));
        this.z = (this.z)*q + znq;

        if (this.z!=0) {
            this.x /= this.z;
            this.y /= this.z;
        } 

        this.x += (float)1;
        this.y += (float)1;

        this.x *= (float).5 * (float)scrW;      
        this.y *= (float).5 * (float)scrH;

        //System.out.printf("  POST x: %f y: %f z: %f\n", this.x, this.y, this.z);

    }
    
    public void projV(float[][] mat, float znq, int scrW, int scrH) {
        mmult(mat);
        this.z += znq;
        this.x /= this.z;
        this.y /= this.z;
        this.x += (float)1;
        this.y += (float)1;
        this.x *= (float).5 * (float)scrW;      
        this.y *= (float).5 * (float)scrH;
        
    }

    public void mmult(float[][] mat) {
        float xx = this.x;
        float yy = this.y;
        float zz = this.z;

        this.x = xx*mat[0][0] + yy*mat[1][0] + zz*mat[2][0];
        this.y = xx*mat[0][1] + yy*mat[1][1] + zz*mat[2][1];
        this.z = xx*mat[0][2] + yy*mat[1][2] + zz*mat[2][2];

    }


}