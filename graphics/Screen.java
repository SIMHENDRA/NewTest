package NewTest.graphics;

import NewTest.Thingy;



public class Screen {
    
    private int width, height;
    public int[] pixels;

    public Screen (int width, int height) {
        this.width = width;
        this.height = height;
        pixels = new int[width * height];
    }

    public void renderLn(vec a, vec b, int coll) {
        int x1 = (int)a.x();
        int y1 = (int)a.y();
        int x2 = (int)b.x();
        int y2 = (int)b.y();
        if (coll == -1) coll = 0xff00ff;
        
        int d = 0;
 
        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);
 
        int dx2 = 2 * dx; // slope scaling factors to
        int dy2 = 2 * dy; // avoid floating point
 
        int ix = x1 < x2 ? 1 : -1; // increment direction
        int iy = y1 < y2 ? 1 : -1;
 
        int x = x1;
        int y = y1;
 
        if (dx >= dy) {
            while (true) {
                if (withinBounds(x, y)) pixels[x + y*width] = coll;
                else return;
                if (x == x2)
                    break;
                x += ix;
                d += dy2;
                if (d > dx) {
                    y += iy;
                    d -= dx2;
                }
            }
        } else {
            while (true) {
                if (withinBounds(x, y)) pixels[x + y*width] = coll;
                else return;
                if (y == y2)
                    break;
                y += iy;
                d += dx2;
                if (d > dy) {
                    x += ix;
                    d -= dy2;
                }
            }
        }
        //System.out.println("Line Drawn.");
    }

    public void renderTri(matr x, int coll) {       
        renderLn(x.m[0],x.m[1], coll);
        renderLn(x.m[1], x.m[2], coll);
        renderLn(x.m[2], x.m[0], coll);
    }

    public void renderMesh(mesh x, int coll) {
        for (matr i : x.tris) renderTri(i,coll);
    }









    public void renderLn(vertex a, vertex b, int coll) {
        int x1 = (int)a.x;
        int y1 = (int)a.y;
        int x2 = (int)b.x;
        int y2 = (int)b.y;
        
        int d = 0;
 
        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);
 
        int dx2 = 2 * dx; // slope scaling factors to
        int dy2 = 2 * dy; // avoid floating point
 
        int ix = x1 < x2 ? 1 : -1; // increment direction
        int iy = y1 < y2 ? 1 : -1;
 
        int x = x1;
        int y = y1;
 
        if (dx >= dy) {
            while (true) {
                if (withinBounds(x, y)) pixels[x + y*width] = coll;
                else return;
                if (x == x2)
                    break;
                x += ix;
                d += dy2;
                if (d > dx) {
                    y += iy;
                    d -= dx2;
                }
            }
        } else {
            while (true) {
                if (withinBounds(x, y)) pixels[x + y*width] = coll;
                else return;
                if (y == y2)
                    break;
                y += iy;
                d += dx2;
                if (d > dy) {
                    x += ix;
                    d -= dy2;
                }
            }
        }
        //System.out.println("Line Drawn.");
    }

    public void renderTri(tria x) {       
        renderLn(x.verts[0],x.verts[1],x.coll);
        renderLn(x.verts[1], x.verts[2], x.coll);
        renderLn(x.verts[2], x.verts[0], x.coll);
    }

    public void prMesher(mesher x, float a, float f, float q, float zn, float znq, int scrH, int scrW) {

        mesher doer = new mesher(x);

        doer.addz((float)3);
        doer.projM(a, f, q, zn, znq, scrH, scrW);
        

        renderMesher(doer);

    }

    public void prMesher(mesher x, float[][] mat, float znq, int scrW, int scrH, float[][] rotz, float[][] rotx) {
        mesher doer = new mesher(x);

        doer.mmult(rotz);
        doer.mmult(rotx);
        doer.addz((float)3);
        doer.projM(mat, znq, scrW, scrH);
        
        renderMesher(doer);
    }

    public void renderMesher(mesher x) {
        for (tria i : x.trias) renderTri(i);
    }

    public void clear() { for (int i = 0;i<pixels.length;i++) pixels[i] = 0; }

    public void renderB(int coll) {
        for (int y = 0; y<height; y++) {
            for (int x = 0; x<width; x++) {                
                pixels[x + (y*width)] = coll;
            }
        }
    }
  
    public void render(Thingy ex) {
        int yy = ex.y;
        int xx = ex.x;
        int may = ex.y+ex.dim;
        int max = ex.x+ex.dim;
        int col = ex.coll.getRGB();

        for (int y = yy; y < may; y++) {
            for (int x = xx; x < max; x++) {
                pixels[x + y*width] = col;
            }
        }
    }

    public boolean withinBounds(int x, int y) {
        return (y>=0 && y<height)&&(x>=0 && x<width);
    }
}