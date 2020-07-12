/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gilbertjunior;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author abdur
 */
public class Wall {
    
    
    int x,y,w,h;
    
    Wall(){
        x=0;
        y=0;
        w=0;
        h=0;
    }
    
    public void clint(int x1,int y1){
        x=x1;
        y=y1;
    }
    public void rlint(int x1,int y1){
        w=x1;
        h=y1;
    }
    

    
    public void update(Graphics g){
//        int[] xPoints = new int[]{x,w};
//        int[] yPoints = new int[]{h,y};
//        g.setColor(new Color(0,255,255));
//        g.fillPolygon(xPoints, yPoints,2);
//        g.fillRect(x, y, w, h);
//        g.setColor(new Color(255,255,255,2));
    }
    
}
