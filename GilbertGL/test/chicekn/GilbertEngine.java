//I named my package "gilbertjava" but you need to put your package name here
//package (insert package name here);
package chicekn;

//Imports
import gilbertgl.*;
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
    boolean renderObjects = false;
    boolean connectPoints = true;
    
    
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
    public void setOptions(int canvasWidth, int canvasHeight, boolean willRenderObjects, boolean willConnectPoints){
        width=canvasWidth;
        height=canvasHeight;
        renderObjects=willRenderObjects;
        connectPoints=willConnectPoints;
    }
    
    //Get options to help with scene adjustment
    public Object[] getOptions(){
        return new Object[]{renderObjects, width, height, connectPoints};
        
    }
    
    //This renders all the points and shades them
    public void update(){
        
        //Configure GL
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        
        //Initiate variables
        //Initializing points array
        int shadeX[] = new int[5];
        int shadeY[] = new int[5];
        
        int na = 0;
                    
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
                    
                    if(na>=objectx.length-1){
                        if(connectPoints){
                            na = 0;
                        }else{
                            na=a;
                        }
                    }else{
                        na = a+1;
                    }

                    //Initialize the points
                    int pointx = objectx[a];
                    int pointy = objecty[a];
                    int pointx2 = objectx[na];
                    int pointy2 = objecty[na];

                    //Render shadows

                    //Calculate points for polygon and put them in an array so they render correctly
                    shadeX[0] = pointx;
                    if(pointx-lightx!=0){
                        shadeX[1] = pointx/(pointx-lightx);    
                    }else{
                        shadeX[1] = (lightx-pointx)*width;
                    }
                    
                    if(lightx-pointx2!=0){
                        shadeX[2] = pointx2/(pointx2-lightx);    
                    }else{
                        shadeX[2] = (lightx-pointx2)*width;
                    }
//                    shadeX[1] = (pointx-lightx)*width;
//                    shadeX[2] = (pointx2-lightx)*width;
                    shadeX[3] = pointx2;
                    shadeX[4] = pointx;

                    shadeY[0] = pointy;
                    if(pointy-lighty!=0){
                        shadeY[1] = pointy/(pointy-lighty);    
                    }else{
                        shadeY[1] = (lighty-pointy)*height;
                    }
                    
                    if(lighty-pointy2!=0){
                        shadeY[2] = pointy2/(pointy2-lighty);    
                    }else{
                        shadeY[2] = (lighty-pointy2)*height;
                    }
//                    shadeY[1] = ((pointy-lighty)*(height));
//                    shadeY[2] = ((pointy2-lighty)*height);
                    shadeY[3] = pointy2;
                    shadeY[4] = pointy;

                    //Draw the polygon
                    GL11.glColor4f(0.0f, 0.0f, 0.0f, 0.3f);
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
                    //On top of that, the performance hit cannot be justified

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
