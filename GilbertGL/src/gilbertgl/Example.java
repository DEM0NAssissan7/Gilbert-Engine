package gilbertgl;
//This file is meant to be a tutorial on how to use the engine

import static gilbertgl.Main.height;
import static gilbertgl.Main.width;
import static gilbertgl.Main.window;
import static org.lwjgl.glfw.GLFW.glfwGetCursorPos;

public class Example{
    //The example function
    
    //Here is the way you can initialize the engine
    GilbertEngine gilbert = new GilbertEngine();
    
    //Initializer
    public void init(){
        
        //Here is how to add a wall or object to shade
        //You need to specify the location of the points in space. Order matters.
        //This order of points makes a triangle
        int objectx[] = {100,100,200};
        int objecty[] = {100,200,200};
        //Then put it in the function
        gilbert.addObj(objectx, objecty);
        
        //You can do this a couple times
        
        //Square
        objectx = new int[]{400,500,500,400};
        objecty = new int[]{600,600,700,700};
        gilbert.addObj(objectx, objecty);
        
        //Octagon
        objectx = new int[]{600,650,700,700,650,600,550,550};
        objecty = new int[]{500,500,550,600,650,650,600,550};
        gilbert.addObj(objectx, objecty);
        
        //Here is how you set the options for the engine
        //The first two are for dimensions
        //The third is for whether the program is in renderObjects mode which draws the polygon. This can be useful for debugging
        gilbert.setOptions(width, height, false);
        
        //Here is how you can add lights to the scene
        gilbert.addLgt(width/2,height);
        gilbert.addLgt(80,height/2);
        gilbert.addLgt(600,350);
        
        //This is how you can change a given light's position
        //The last parameter is the index of the light you want to change
        gilbert.chgLgt(width/2, height/2, 0);
        
        //Here is how you can change a given objects's points
        objectx = new int[]{100,100,200};
        objecty = new int[]{100,200,200};
        //The last parameter is the index of the object you want to change
        gilbert.chgObj(objectx , objecty, 0);
        
        
    }
    
    public void RunExample(){
        //Here is how you can run the engine
        //Be advised: This is required to be in your code for the engine to render anything
        //This must be in your paint function
        gilbert.update();
        double[] mouseX = new double[1];
        double[] mouseY = new double[1];
                glfwGetCursorPos(window, mouseX, mouseY);
        gilbert.chgLgt((int)mouseX[0],(int)mouseY[0],0);
    }
}
