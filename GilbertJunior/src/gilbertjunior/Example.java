package gilbertjunior;

import java.awt.Color;
import java.awt.Graphics;

//This is an example class
//You do not need to copy this class
//This class is supposed to be a list of things you can do with Gilbert Junior

public class Example {
    
    //I'm just defining width and height
    //You don't need to copy this
    int width = 1000;
    int height = 1000;
    
    
    //Put these in your intializer
    
    //Here is how you can intialize the Engine
    GilbertJunior gilbert = new GilbertJunior();

    public void Init(){
        
        //Here is how you add a wall
        int[] xpoints = {100,200};
        int[] ypoints = {100,100};
        gilbert.addWall(xpoints,ypoints);
        xpoints = new int[]{400,400,300,300};
        ypoints = new int[]{700,800,800,700};
        gilbert.addWall(xpoints,ypoints);
        xpoints = new int[]{200,300,300,200};
        ypoints = new int[]{400,400,300,300};
        gilbert.addWall(xpoints,ypoints);
        xpoints = new int[]{600,700,600};
        ypoints = new int[]{300,400,400};
        gilbert.addWall(xpoints,ypoints);
        
        //Here is how to change the position of a given wall
        //The last parameter is the index of the wall you wish to change
        //Please note that you can place this in your paint function
        gilbert.wallPos(xpoints, ypoints, 0);
        
        //Here is how you add a light
        //It is recommended to have walls on the edges, however if you only want the lights casted on one location, you don't need it
        gilbert.addLight(100,400,false);
        gilbert.addLight(400,400,false);
        gilbert.addLight(700,100,false);
        
        //This is how to change the position of a given light
        //The last parameter is the index of the light you want to move
        //This can also be placed in your paint function
        gilbert.lightPos(200, 700, 0);
        
        //This is how to change the quality
        //By default, it is on 1 but can be adjusted to be higher or lower
        //Values above 100 cause gamma artifacting. The gamma breaks after 100 and all lights become the same gamma
        gilbert.setQuality(100);
        
        //Here is how to change the color of the rays
        gilbert.setColor(new Color(255,255,255,10));
        
        //Here is how to add walls on the sides. I did it for you so you don't have to
        xpoints = new int[]{0,0}; 
        ypoints = new int[]{0,height};
        gilbert.addWall(xpoints, ypoints);
        xpoints = new int[]{width,width}; 
        ypoints = new int[]{0,height};
        gilbert.addWall(xpoints, ypoints);
        xpoints = new int[]{0,width}; 
        ypoints = new int[]{height,height};
        gilbert.addWall(xpoints, ypoints);
        xpoints = new int[]{0,width}; 
        ypoints = new int[]{0,0};
        gilbert.addWall(xpoints, ypoints);
    }
    
    //Put these in your paint function

    public void Example(Graphics g){
        
        //Here is how to update the engine so it can run
        gilbert.update(g,true);
        //You need to put this in your paint function
        //This is necessary for the engine to do anything
    }
}
