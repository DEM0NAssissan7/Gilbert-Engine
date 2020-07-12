//You need to change the package to the name of your package
//In this case, my package was gilbertjunior
//package (your package name here)
package gilbertjunior;

//The imports necessary for the engine to function
import java.awt.Color;
import java.awt.Graphics;
import static java.lang.Math.PI;
import java.util.ArrayList;

public class GilbertJunior {
    
    //Part 1: Settings for the engine
        
        //Zone 1: Initialize
    
        //The variable for quality
        double q = 1;
        
        //Color initializer
        Color rayColor = new Color(255,255,255,2);
        
        //ArrayList initializer
        ArrayList<int[]> lights = new ArrayList<>();
        ArrayList<Boolean> randoms = new ArrayList<>();
        private ArrayList<int[]> wallsx = new ArrayList<>();
        private ArrayList<int[]> wallsy = new ArrayList<>();
        
        
        //Zone 2: Functions
        
        //Setting caster quality
        public void setQuality(int quality){
            //The quality will be how many rays will be rendered *360
            //For example, a quality of 1 will make 360 rays
            //A quality of 10 will make 3,600 rays
            //A quality of 0.1 will make only 36 rays
            
            //Quality conversion to double
            double rq = quality;
            
            //Setting math for for loop
            q=1/rq;
        }
        
        //Setting ray color
        public void setColor(Color color){
            
            rayColor=color;
        }
    
    //Part 2: The ray caster
    
    public void update(Graphics g, boolean renderPolygons){
            
        //Zone 1: Initializer        
                
        //Block renderer
        //This is not recommended for normal use
        //Check if update has the renderPolygons boolean set to true
        if(renderPolygons){

            //If so, render polygons
            for(var i = 0; i < wallsx.size(); i++){
                g.fillPolygon(wallsx.get(i), wallsy.get(i), wallsx.get(i).length);
            }
        }
        
        //Get all lights
        for(int a = 0; a < lights.size();a++){
            
            //Temporary getLight function to extract the x and y
            int[] lcache = lights.get(a);
            
            //Initialize coordinates
            int x = lcache[0];
            int y = lcache[1];
            
            //Check if light source has random lights
            boolean randomLines = randoms.get(a);
            
            //Cast rays in a circle
            for(double i = 0; i < 360; i+=q){
            //Calculate the angle of the rays
            double lx = Math.sin((i+0.0302)*(PI/180));
            double ly = Math.cos((i+0.0302)*(PI/180));
            
            //Detect if the user wants to have the lines uniform or random
            if(randomLines){
            lx=Math.random()*2-1;
            ly=Math.random()*2-1;   
            }
            
            //Initialize variables necessary to detect nearest wall
            double[] closest = new double[2];
            int rclose = 1000*1000;

            //Multiply path and put it in integer to the ray can be casted
            int rlx = (int) (lx*(10000));
            int rly = (int) (ly*(10000));
            
            
            //Zone 2: Get lowest distance 
            //The reason I need this is because if this doesn't exist, the program will render lines through walls. 
            //I also need it for diffusion.
            
            //Detect all walls
            for(var l = 0; l < wallsx.size(); l++){
            
                //Test all points in wall
                for(int f = 0; f < wallsx.get(l).length; f++){
                
                //Put the wall locations in a variable
                int[] wallx = wallsx.get(l);
                int[] wally = wallsy.get(l);
                
                //Put ray position in a function so we can use it's info for casting
                double[] raypos = castRay(x,y,rlx,rly,wallx,wally,f);
                
                
                //Get distance
                double dist = Math.sqrt(Math.pow(raypos[0]-x,2)+Math.pow(raypos[1]-y,2));
            
                //Check which one is lowest distance
                if(dist<rclose&&dist!=0.0){
                    
                    //Update the record lowest distance
                    rclose=(int) dist;
                    closest=raypos;
                    }         
                }
            }
            
            
            //Zone 3: Drawing

            //Convert closest to int so Graphics can see it
            int clx = (int)closest[0];
            int cly = (int)closest[1];
                        
            //This right here controls the color of the rays
            g.setColor(rayColor);
            
            //And finally, draw the ray path
            g.drawLine(x, y, clx, cly);
            }
        }
    }
    
    //Part 3: The rays themselves
    
        public static double[] castRay(int x, int y, double dirx ,double diry, int[] wallx, int[] wally, int i){
            
                //Initialize the wall coordinates
                double x1 = wallx[i];
                double y1 = wally[i];
                double x2;
                double y2;
                
                //Loop the lines around
                if(i==wallx.length-1){
                    x2=wallx[0];
                    y2=wally[0];
                }else{
                    x2 = wallx[i+1];
                    y2 = wally[i+1];
                }
                
                
                

                //Intialize the ray coordinates
                double x3 = x;
                double y3 = y;
                double x4 = (x + dirx);
                double y4 = (y + diry);
                
                //Make a blank variable so if the function doesn't get a result for the trace then it will just return the coordinates of the origin
                double[] blank = {x,y};
                
                //Initialize den
                double den = (x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4);
                if (den==0.0){
                    return blank;
                }
                
                //Get values to see if a collision happened with a wall
                double t = ((x1 - x3) * (y3 - y4) - (y1 - y3) * (x3 - x4)) / den;
                double u = -((x1 - x2) * (y1 - y3) - (y1 - y2) * (x1 - x3)) / den;
                
                //Test to see if there was a collision
                if(t > 0.0 && t < 1.0 && u > 0.0){    
                    
                    //If there was, make the x and y coordinates
                    double ptx = x1 + t * (x2 - x1);
                    double pty = y1 + t * (y2 - y1);
                    
                    //Put them in an array
                    double[] pt = new double[]{ptx,pty};
                    
                    //And return them
                    return pt;
                }
                
            //If there was no collision, just return blank
            return blank;
        }
        
        
        
        //Part 4: Walls
        
        //Add a wall so that you can have the light show up and interact with it's surroundings
        public void addWall(int[] x, int[] y){
            wallsx.add(x);
            wallsy.add(y);
        }
        
        //Update the position of a given wall
        public void wallPos(int[] x, int[] y, int i){
            wallsx.set(i, x);
            wallsy.set(i, y);
        }
        
        
        
        //Part 5: Lights
        
        //Add lights to cast rays out from
        public void addLight(int x, int y, boolean randomLines){
            int[] example = {x,y};
            lights.add(example);
            randoms.add(randomLines);
        }
        
        //Update a given light's position
        public void lightPos(int x, int y, int i){
            int[] example = {x,y};
            lights.set(i, example);
        }
}
