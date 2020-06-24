package NewTest;

import java.awt.Canvas;
import javax.swing.JFrame;

import NewTest.graphics.*;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.ArrayList;


class Game extends Canvas implements Runnable {
    private static final long serialVersionUID = 1L;

    public static int width = 1500;
    public static int height = 1400;
    public static int scale = 1;
    public static int msdel = 10; // delay for pseudo fps control
    public String windowTitle = "NewTest";

    float a = (float)height / (float)width;
    float fov = (float)(1.5708-.5); //radians
    float f = (float)1.0 / (float)(Math.tan(fov*0.5));
    float zn = (float)0.1;
    float zf = 1000;
    float q = zf/(zf-zn);
    float znq = -zn*q;

    double thet;

    float[][] rotz = new float[][] {{0,0,0},{0,0,0},{0,0,0}};
    float[][] rotx = new float[][] {{0,0,0},{0,0,0},{0,0,0}};





    float[][] proj = new float[][] {{a*f, 0, 0},{0,f,0},{0,0,q}};

    private Thread thread;
    private JFrame frame;
    public boolean running = false;

    private Screen screen;

    private ArrayList<mesher> meshers;

    private BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
    private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();

    //private long iters = 0;
    

    public Game() {
        Dimension size = new Dimension(width * scale, height * scale);
        setPreferredSize(size);
        screen = new Screen(width,height);
        frame = new JFrame();
        meshers = new ArrayList<mesher>();
        thet = 0;
        meshers.add(new mesher());
        //meshers.add(new mesher());
        
    }

    public synchronized void start() {
        if (running)
            return;
        running = true;
        thread = new Thread(this, "Display");
        thread.start();
    }

    public synchronized void stop() {
        if (!running)
            return;
        running = false;
        try {thread.join();}
        catch (InterruptedException e) {}
    }

    public void initWindow() {
        this.frame.setResizable(false);
        this.frame.setTitle(this.windowTitle);
        this.frame.add(this);
        this.frame.pack();
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setVisible(true);
    }

    public void waiter() {
        long time = System.currentTimeMillis();
        long timer = time;
        while(timer<time+msdel) timer = System.currentTimeMillis();      
    }

    public void run() {
        while (running) {
            update();
            render();
            waiter();
        }

    }

    public void update() {
        if (thet<12.5664)thet+=.01;
        else thet = 0;
        
        float ct = (float)Math.cos(thet);
        float st = (float)Math.sin(thet);
        float ctt = (float)Math.cos(thet*.5);
        float stt = (float)Math.sin(thet*.5);
        rotz[0][0] = ct;
        rotz[0][1] = st;
        rotz[1][0] = -st;
        rotz[1][1] = ct;
        rotz[2][2] = 1;

        rotx[0][0] = 1;
        rotx[1][1] = ctt;
        rotx[1][2] = stt;
        rotx[2][1] = -stt;
        rotx[2][2] = ctt; 
        
        

    }

    public void render() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }
        
        screen.clear();
        //System.out.printf("a: %f f: %f q: %f zn: %f znq: %f height: %d width: %d \n", a, f, q, zn, znq, height, width);
        //screen.prMesher(meshers.get(0), a, f, q, zn, znq, height, width);

        for (mesher i : meshers) screen.prMesher(i, proj, znq, width, height, rotz, rotx);
        


        for (int i = 0; i < pixels.length; i++) pixels[i] = screen.pixels[i];        
        Graphics g = bs.getDrawGraphics();
        g.drawImage(image,0,0, getWidth(),getHeight(), null);
        g.dispose();
        bs.show();
        
    }
    
    public static void main(String args[]) {

        Game game = new Game();
        game.initWindow();
        game.start();

    }

    
    
}