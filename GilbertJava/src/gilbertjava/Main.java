//WARNING:
//DO NOT COPY THIS FILE
//THIS FILE IS INTENDED FOR EXECUTION OF THE EXAMPLE AND SHOULD NOT BE IN YOUR PROJECT
//
package gilbertjava;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;

public class Main extends Canvas {

    static int width = 1000;
    static int height = 1000;
    
    public static int mouseX;
    public static int mouseY;
    
    static boolean init = false;
    
    public static Canvas canvas;
    
    Example example = new Example();
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("Gilbert Engine Example");
        canvas = new Main();
        canvas.setSize(width, height);
        canvas.setBackground(Color.white);
        frame.add(canvas);
        frame.pack();
        frame.setVisible(true);
        canvas.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                mouseX = e.getPoint().x;
                mouseY = e.getPoint().y;
            }
        });
    }
    
    @Override
    public void paint(Graphics g) {
        if(!init){
            init=true;
            example.init();
        }
        example.RunExample(g);
    }
}

