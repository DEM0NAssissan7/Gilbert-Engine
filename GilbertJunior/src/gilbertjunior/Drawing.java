






//DO NOT COPY THIS CLASS
//If you want to see how to use the Gilbert Engine JR, go to example.java














package gilbertjunior;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;

public class Drawing extends Canvas{
    
    public static int width = 1000;
    public static int height = 1000;
    static Canvas canvas;
    static Example example = new Example();
    
    public static void main(String[] args) {       
        example.Init();
        JFrame frame = new JFrame("GilbertJR");
        canvas = new Drawing();
        canvas.setBackground(new Color(0,0,0,255));
        canvas.setSize(width,height);
        frame.add(canvas);
        frame.pack();
        frame.setVisible(true);
    }
    @Override
    public void paint(Graphics g) {
        example.Example(g);
    }
}