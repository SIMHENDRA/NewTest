package NewTest.graphics;

public class ver extends vec {
        
    public ver(float[] x) {
        super(x);
    }

    public ver() {
        super(new float[] {0,0,0});
    }

    public void add4() {
        float[] n = new float[] {x(),y(),z(),1};
        this.v = n;
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


}