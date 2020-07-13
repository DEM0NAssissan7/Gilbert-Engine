//I named my package "gilbertjava" but you need to put your package name here
//package (insert package name here);
package gilbertjava;

//Imports
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class GilbertEngine extends Thread{
    //Array that stores the object points
    ArrayList<int[]> objectsx = new ArrayList<>();
    ArrayList<int[]> objectsy = new ArrayList<>();
    ArrayList<Integer> lightsx = new ArrayList<>();
    ArrayList<Integer> lightsy = new ArrayList<>();
    
    //Setting default values so the program doesn't break
    int width = 400;
    int height = 400;
    boolean gbilm = false;
    boolean renderObjects=false;
    Color gbcol = new Color(255,255,255,10);
    
    
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
    
    //Set global illumination settings
    //Global illumination isn't exactly accurate and can be buggy
    //It is recommended to keep it off, however you can still enable it
    public void setGlobalIllumination(boolean example, Color color){
        gbilm = example;
        gbcol = color;
    }
    
    //Get options to help with scene adjustment
    public Object[] getOptions(){
        return new Object[]{gbilm, renderObjects, width, height, gbcol};
        
    }
    
    //This renders all the points and shades them
    public void update(Graphics g){ 
                //Have every light render every object
                for(var aa = 0; aa < lightsx.size(); aa++){
                    int lposx = lightsx.get(aa);
                    int lposy = lightsy.get(aa);
                    
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
                    
                    //Calculate slopes for shading                    
                    double xSlope = (pointx-lposx);
                    double ySlope = (pointy-lposy);
                    double xSlope2 = (pointx2-lposx);
                    double ySlope2 = (pointy2-lposy);

                    //Calculate end point for polygon at the edge of the window
                    int endRayX = (int) (xSlope*width);
                    int endRayY = (int) (ySlope*height);
                    int endRayX2 = (int) (xSlope2*width);
                    int endRayY2 = (int) (ySlope2*height);

                    //Render shadows
                    
                    //Check if global illumination is off
                    if(!gbilm){
                        
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
                    g.setColor(new Color(0,0,0,120));
                    
                    //Draw the polygon
                    g.fillPolygon(shadeX, shadeY, shadeX.length);
                    
                    }
                    
                    
                    
                    if(gbilm){
                        //If global illumination is on, render global illumination
                        
                        //Initializer
                        int[] globalX = new int[9];
                        int[] globalY = new int[9];
                        
                        //Organize the illumination to render the opposite of the shadows
                        
                        globalX[0] = endRayX;
                        globalX[1] = pointx;
                        globalX[2] = pointx2;
                        globalX[3] = endRayX2;
                        
                        globalY[0] = endRayY;
                        globalY[1] = pointy;
                        globalY[2] = pointy2;
                        globalY[3] = endRayY2;
                            
                        //Do a little math to figure out how the rest of the polygon should be arranged and loop around
                            if(endRayY2>=pointy2){
                                
                                //When the light is above the point
                                globalY[4] = height;
                                globalY[5] = 0;
                                globalY[6] = 0;
                                globalY[7] = height;
                                
                            }else{
                                
                                //When the light is below the point
                                globalY[4] = 0;
                                globalY[5] = height;
                                globalY[6] = height;
                                globalY[7] = 0;
                                
                            }
                            
                            if(endRayX2<=pointx2){
                                //If the light is to the right of the point
                                
                                globalX[4] = width;
                                globalX[5] = width;
                                globalX[6] = 0;
                                globalX[7] = 0;
                            }else{
                                
                                globalX[4] = 0;
                                globalX[5] = 0;
                                globalX[6] = width;
                                globalX[7] = width;
                            }
                                
                        globalX[8] = endRayX;
                        globalY[8] = endRayY;
                                
                        //Render the polygons
                        
                        //Set the color
                        g.setColor(gbcol);
                        
                        //Now, draw the polygon
                        g.fillPolygon(globalX, globalY, globalX.length);
                    }
                    }
                    //Fill in the points
                    //Test to see if renderObjects is true
                    if(renderObjects){
                    
                        g.setColor(Color.red);
                        g.fillPolygon(objectx, objecty, objectx.length);
                    }
                }
            }
        }
    }
