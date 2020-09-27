//I named my package "gilbertjava" but you need to put your package name here
//package (insert package name here);
package gilbertgl;

//Imports
import java.util.ArrayList;
import org.lwjgl.opengl.GL11;

public class GilbertEngine extends Thread{
    //Array that stores the object points
    ArrayList<int[]> objectsx = new ArrayList<>();
    ArrayList<int[]> objectsy = new ArrayList<>();
    ArrayList<Integer> lightsx = new ArrayList<>();
    ArrayList<Integer> lightsy = new ArrayList<>();
    
    //Setting default values so the program doesn't break
    int width = 400;
    int height = 400;
    boolean renderObjects=false;
    
    
    //The function to add objects to the array
    public void addObj(int[] xpoints,int[] ypoints){
        objectsx.add(xpoints);
        objectsy.add(ypoints);
    }

    //The function to change the points on an object
    public void chgObj(int[] xpoints,int[] ypoints,int i){
        objectsx.set(i, xpoints);
        objectsy.set(i, ypoints);
    }
    
    //The function to add lights to the scene
    public void addLgt(int x, int y){
        lightsx.add(x);
        lightsy.add(y);
    }
    
    //The function to change a given light's position in the scene
    public void chgLgt(int x, int y, int i){
        lightsx.set(i, x);
        lightsy.set(i, y);
    }
    
    //Set canvas height to allow the program to properly work
    public void setOptions(int canvasWidth, int canvasHeight, Boolean willRenderObjects){
        width=canvasWidth;
        height=canvasHeight;
        renderObjects=willRenderObjects;
    }
    
    //Get options to help with scene adjustment
    public Object[] getOptions(){
        return new Object[]{renderObjects, width, height};
        
    }
    
    //This renders all the points and shades them
    public void update(){ 
                //Have every light render every object
                for(var aa = 0; aa < lightsx.size(); aa++){
                    int lightx = lightsx.get(aa);
                    int lighty = lightsy.get(aa);
                    
        //Check if global illumination is on
        

        for (int i = 0; i < objectsx.size(); i++) {
            
            //Index object and make it a readable variable
            int[] objectx = objectsx.get(i);
            int[] objecty = objectsy.get(i);
            
            //Initializing variables to be used outside of loop
            
                //Get every point to shade
                for(var a = 0; a < objectx.length; a++){
                    
                    //Calculate next a
                    int na = a+1;
                    if(na>=objectx.length){
                        na=0;
                    }
                    
                    //Initializing points array
                    int shadeX[] = new int[5];
                    int shadeY[] = new int[5];
                    
                    //Initialize the points
                    int pointx = objectx[a];
                    int pointy = objecty[a];
                    int pointx2 = objectx[na];
                    int pointy2 = objecty[na];

                    //Calculate end point for polygon at the edge of the window
                    int endRayX = (int) ((pointx-lightx)*width);
                    int endRayY = (int) ((pointy-lighty)*height);
                    int endRayX2 = (int) ((pointx2-lightx)*width);
                    int endRayY2 = (int) ((pointy2-lighty)*height);
                    
                    //Render shadows
                        
                    //Calculate points for polygon and put them in an array so they render correctly
                    shadeX[0] = pointx;
                    shadeX[1] = endRayX;
                    shadeX[2] = endRayX2;
                    shadeX[3] = pointx2;
                    shadeX[4] = pointx;
                    
                    shadeY[0] = pointy;
                    shadeY[1] = endRayY;
                    shadeY[2] = endRayY2;
                    shadeY[3] = pointy2;
                    shadeY[4] = pointy;
                    
                    //Set the color
                    GL11.glEnable(GL11.GL_BLEND);
                    GL11.glColor4f(0.0f, 0.0f, 0.0f, 0.2f);
                    GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
                        
//                    
//                    //Draw the polygon
                        GL11.glBegin(6);
                        for(int glin = 0; glin < 5;glin++){
                            float obx = shadeX[glin]-(width/2);
                            float oby = shadeY[glin]-(height/2);
                            
                            float suffx = (obx/(width/2));
                            float suffy = (-oby/(height/2));
                            GL11.glVertex2f(suffx, suffy);
                        }
                        
                        GL11.glEnd();
                        
                    //Global illumination is removed in GilbertGL due to global illumination being completely pointless given OpenGL's options
                    //On top of that, it doesn't even work
                    
                }
                //Fill in the points
                //Test to see if renderObjects is true
                if(renderObjects){

                    GL11.glColor3f(1.0f, 0f, 0f);
                        GL11.glBegin(objectx.length+1);
                        for(int glint = 0; glint < objectx.length+1;glint++){
                            int glin;
                            if(glint>=objectx.length){
                                glin=0;
                            }else{
                                glin = glint;
                            }
                            float obx = objectx[glin]-(width/2);
                            float oby = objecty[glin]-(height/2);
                            
                            float suffx = (obx/(width/2));
                            float suffy = (-oby/(height/2));
                            GL11.glVertex2f(suffx, suffy);
                        }
                        
                        GL11.glEnd();
                }
            }
        }
    }
}
