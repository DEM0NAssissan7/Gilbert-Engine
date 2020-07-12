/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gilbertjunior;

import static gilbertjunior.Drawing.height;
import static gilbertjunior.Drawing.width;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author abdur
 */
public class Ray {
    
    
        static int wx,wy,ww,wh;
        static boolean bdef=false;
        static boolean dfs = true;
        public static void addWalls(int x, int y, int w, int h){
            bdef=true;
            wx=x;
            wy=y;
            ww=w+x;
            wh=h+y;
        }
        static float rdf = (float) 1.5;
        static float raydev = (float) 1;
        @SuppressWarnings("UnusedAssignment")
        public static void castRay(int x, int y, double x1,double y1,Graphics g){
        int ogx = x;
        int ogy = y;
        double rx = x1;
        double ry = y1;
        
        double rogx = (double) ogx;
        double rogy = (double) ogy;
        if(bdef){
        float rcount=255;
        while(rogx>0&&rogx<width&&rogy>0&&rogy<height){
            rcount-=rdf;
            rcount=6;
            if(rcount<0){
                rcount=0;
                rogx=-44;
            }
            g.setColor(new Color(255,255,255,(int) rcount));
            if(rogx>wx&&rogx<ww&&rogy>wy&&rogy<wh){
                if(dfs){
                    
                    rx-=(Math.random()/raydev)-(0.5/raydev);
                    ry-=(Math.random()/raydev)-(0.5/raydev);
                    
                }else{
//                    rx=-rx;
                    ry=-ry;
                }
                
            }
            
            if(rx>1){
                rx=1;
            }
            if(ry>1){
                ry=1;
            }
            if(rx<-1){
                rx=-1;
            }
            if(ry<-1){
                ry=-1;
            }
            rogx+=rx;
            rogy+=ry;
            int fogx = (int) rogx;
            int fogy = (int) rogy;
//            System.out.print(ogx + "  " + ogy);
            g.drawLine(fogx, fogy, fogx, fogy);
            
            
        }
        }else{
        while(rogx>0&&rogx<width&&rogy>0&&rogy<height){
            rogx+=x1;
            rogy+=y1;
            
            int fogx = (int) rogx;
            int fogy = (int) rogy;
//            System.out.print(ogx + "  " + ogy);
            g.drawLine(fogx, fogy, fogx, fogy);
            
            
        }
        }
               
        
        
    }
        
        
}
