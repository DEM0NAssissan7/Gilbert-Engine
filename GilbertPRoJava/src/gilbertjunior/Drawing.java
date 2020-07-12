/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gilbertjunior;

/**
 *
 * @author abdur
 */
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import static java.lang.Math.PI;
import javax.swing.JFrame;

public class Drawing extends Canvas implements MouseListener{
    
    // Recommended dimensions:
    //
    // width = 1000
    // height = 1000
    public static int width = 1000;
    public static int height = 1000;
    public static void main(String[] args) {
        JFrame frame = new JFrame("GilbertPRoJ");
        Drawing canvas = new Drawing();
        canvas.setBackground(new Color(0,0,0,0));
        canvas.setSize(width,height);
        frame.add(canvas);
        frame.pack();
        frame.setVisible(true);
    }


    


    
    
    
    
    
int wcount = 0;
boolean randomLines = false;
    
//Values over 100 are not recommended
int subpxprcsn = 100;
float awsm = (float) 1/subpxprcsn;
boolean aexec = false;


    Wall walls = new Wall();

Point p = MouseInfo.getPointerInfo().getLocation();
        
        
        int mouseX = p.x;
        int mouseY = p.y;
        
@Override
    public void paint(Graphics g) {
        this.addMouseListener(this);
        
        



        
        //Define points for wall
        
        walls.x=40;
        walls.y=140;
        walls.w=100;
        walls.h=500;
        
        
            walls.update(g);
//            System.out.print(walls.x);
            goog(g);
        }
    
    
    
            Ray ray = new Ray();
    @SuppressWarnings("static-access")
    public void goog(Graphics g){
            walls.update(g);
            ray.addWalls(walls.x,walls.y,walls.w,walls.h);
        int xcount = 0;
        for(double i = 0; i < 360; i+=awsm){
            double lx = Math.sin((i)*(PI/180));
            double ly = Math.cos((i)*(PI/180));
            
            if(randomLines){
            lx=Math.random()*2-1;
            ly=Math.random()*2-1;
            }
            
//            System.out.println("===> "+lx+"  |====|  1y "+ly+"   |====| i= "+i);
//            int rlx = (int) (lx*(width*10));
//            int rly = (int) (ly*(height*10));
//            g.drawLine(width/2, height/2, rlx, rly);
            g.setColor(new Color(255,255,255,2));
            ray.castRay(width/2,height/2,lx,ly,g);
        }
//        System.out.print(spp);
    
            walls.update(g);
//        awsm/=2;
    }
    

    
    @Override
    public void mousePressed(MouseEvent e){
        
        Wall cwal = walls;
        
        cwal.clint(mouseX,mouseY);
            System.out.print(" x-> "+walls.x+"  y-> "+walls.y);
        
    }


    @Override
    public void mouseClicked(MouseEvent e) {
    }


    @Override
    public void mouseReleased(MouseEvent e) {        
        Wall cwal = walls;
        Graphics g = null;
        
        cwal.rlint(mouseX,mouseY);
            System.out.print(" w-> "+ walls.w+"  h-> "+walls.h + " |========| ");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }


    @Override
    public void mouseExited(MouseEvent e) {
    }
    
    
    
    

}