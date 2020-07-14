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
    
    public static Canvas canvas = new Main();
    
    Example example = new Example();
    
    static JFrame frame = new JFrame("Gilbert Engine Example");
    
    public static void main(String[] args){
        canvas.setSize(width, height);
        
        canvas.setBackground(Color.gray);
        
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
            example.init();
            init=true;
        }else{
            boolean hasGlobal = (boolean) example.gilbert.getOptions()[0];
            if(hasGlobal){
                canvas.setBackground(Color.black);
            }else{
                canvas.setBackground(Color.white);
            }
        }
        
        example.RunExample(g);
    }
}

