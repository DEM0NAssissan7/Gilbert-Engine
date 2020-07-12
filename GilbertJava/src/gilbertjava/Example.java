package gilbertjava;

//Import all the window data like mouse location and the dimensions
//DO NOT COPY THIS FILE
//This file is meant to be an example of what you should do

import static gilbertjava.Main.height;
import static gilbertjava.Main.width;
import java.awt.Color;
import java.awt.Graphics;

public class Example{
    
    //The example function
    //You don't need to copy this
    
    
    //Here is the way you can initialize the engine
    GilbertEngine gilbert = new GilbertEngine();
    public void init(){
        
        //Here is how to add a wall or object to shade
        //You need to specify the location of the points in space. Order matters.
        int objectx[] = {100,100,200};
        int objecty[] = {100,200,200};
        //Then put it in the function
        gilbert.addObj(objectx, objecty);
        
        //You can do this a couple times
        objectx = new int[]{400,500,500,400};
        objecty = new int[]{600,600,700,700};
        gilbert.addObj(objectx, objecty);
        objectx = new int[]{600,700,700,600};
        objecty = new int[]{500,500,600,600};
        gilbert.addObj(objectx, objecty);
        
        //Here is how you set the options for the engine
        //The first two are for dimensions
        //The third is for whether the program is in renderObjects mode which draws the polygon. This can be useful for debugging
        gilbert.setOptions(width, height, false);
        
        //This is how you set the global illumination settings
        //It is recommended to set this to false
        //You can set the color of the light, which you cannot do without this
        //This feature could be buggy so use at your own risk
        gilbert.setGlobalIllumination(false, new Color(0,0,0,10));
        
        //Here is how you can add lights to the scene
        gilbert.addLgt(width/2,height);
        gilbert.addLgt(80,height/2);
        gilbert.addLgt(600,350);
        
    }
    
    public void RunExample(Graphics g){
        
        //This is how you can change a given light's position
        gilbert.chgLgt(width/2, height/2, 0);
        
        //Here is how you can change a given objects's points
        int objectx[] = {100,100,200};
        int objecty[] = {100,200,200};
        gilbert.chgObj(objectx , objecty, 0);
        
        //Here is how you can run the engine
        //Be advised: This is required to be in your code for the engine to render anything
        //This must be in your paint function
        gilbert.update(g);
    }
}
