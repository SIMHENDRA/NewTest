package NewTest;

import java.awt.Color;

public class Thingy {

    public int x; 
    public int y;
    public int xvel;
    public int yvel; 
    public int dim;
    public Color coll;

    public Thingy(int xx, int yy, int dimm,int yyv, int xxv) {
        this.dim = dimm;
        this.x = xx;
        this.y = yy;
        this.xvel = xxv;
        this.yvel = yyv;
        this.coll = new Color(100,100,100);
    }
    
        public int getx() { return this.x;}

        public int gety() { return this.y;}

        public void setx(int xx) {this.x = xx;}

        public void sety(int yy) {this.y = yy;}

        public int getxv() { return this.xvel;}

        public int getyv() { return this.yvel;}

        public void setxv(int xx) {this.xvel = xx;}

        public void setyv(int yy) {this.yvel = yy;}

        public void setCol(int r, int g, int b) {this.coll = new Color(r,g,b);}

        public void updpos() {
            setx(this.getx() + this.getxv());
            sety(this.gety() + this.getyv());
        }


    
}