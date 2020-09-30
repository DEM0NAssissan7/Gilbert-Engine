//I named my package "gilbertjava" but you need to put your package name here
//package (insert package name here);


//Imports
import java.util.ArrayList;
import java.util.HashSet;
import org.lwjgl.opengl.GL11;

public class GilbertEngine extends Thread{
    //Array that stores the object points
    
    ArrayList<int[]> objectsx = new ArrayList<>();
    ArrayList<int[]> objectsy = new ArrayList<>();
    ArrayList<Integer> pointsx = new ArrayList<>();
    ArrayList<Integer> pointsy = new ArrayList<>();
    ArrayList<Integer> lightsx = new ArrayList<>();
    ArrayList<Integer> lightsy = new ArrayList<>();
    ArrayList<int[]> arrayMap = new ArrayList<>();
    
    //Setting default values so the program doesn't break
    int width = 400;
    int height = 400;
    boolean renderObjects=false;
    boolean connectPolygon=false;
    
    
    //The function to add objects to the array
    public void addObj(int[] xpoints,int[] ypoints){
        objectsx.add(xpoints);
        objectsy.add(ypoints);
        arrayMap.add(new int[]{arrayMap.size(),xpoints.length+1});
        compilePoints();
    }

    //The function to change the points on an object
    public void chgObj(int[] xpoints,int[] ypoints,int i){
        int[] map = arrayMap.get(i);
        int index = map[0];
        for(int a = 0; a < xpoints.length; a++){
            pointsx.set(index+a, xpoints[a]);
            pointsy.set(index+a, ypoints[a]);
        }
        compilePoints();
    }
        
    //The function to re arrange the points on an object
    public void setObj(int[] xpoints,int[] ypoints,int i){
        objectsx.set(i, xpoints);
        objectsy.set(i, ypoints);
        compilePoints();
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
    public void setOptions(int canvasWidth, int canvasHeight, boolean willRenderObjects, boolean willConnectPolygon){
        width=canvasWidth;
        height=canvasHeight;
        renderObjects=willRenderObjects;
        connectPolygon=willConnectPolygon;
    }
    
    //Get options to help with scene adjustment
    public Object[] getOptions(){
        return new Object[]{renderObjects, width, height, connectPolygon};
        
    }
    
    //This renders all the points and shades them
    public void update(){ 

        //Configure GL
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glColor4f(0.0f, 0.0f, 0.0f, 0.2f);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        

        //Have every light render every object
        for(var aa = 0; aa < lightsx.size(); aa++){
            int lightx = lightsx.get(aa);
            int lighty = lightsy.get(aa);


                    //Initializing points array
                    int shadeX[] = new int[5];
                    int shadeY[] = new int[5];
                    
                    //Initialize counter for size
                    int sizem = arrayMap.get(0)[1];
                    
                    int na = 0;
                    int si = 0;
                    
                    //Get every point to shade
                    for(var a = 0; a < pointsx.size()-1; a++){

                        //Calculate next a
                        if(a!=sizem){
                            a++;
                            sizem = arrayMap.get(si)[1];
                            si++;
                            na=a;
                        }else{
                            na = a+1;
                        }

                        //Initialize the points
                        int pointx = pointsx.get(a);
                        int pointy = pointsy.get(a);
                        int pointx2 = pointsx.get(na);
                        int pointy2 = pointsy.get(na);

                        //Calculate points for polygon and put them in an array so they render correctly
                        shadeX[0] = pointx;
                        shadeX[1] = ((pointx-lightx)*width);
                        shadeX[2] = ((pointx2-lightx)*width);
                        shadeX[3] = pointx2;
                        shadeX[4] = pointx;

                        shadeY[0] = pointy;
                        shadeY[1] = ((pointy-lighty)*height);
                        shadeY[2] = ((pointy2-lighty)*height);
                        shadeY[3] = pointy2;
                        shadeY[4] = pointy;

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
                        for(int l = 0; l < objectsx.size(); l++){
                            int[] objectx=objectsx.get(l);
                            int[] objecty=objectsy.get(l);
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
    
    void compilePoints(){
        pointsx.clear();
        pointsy.clear();
        for(int i = 0; i < objectsx.size();i++){
            int[] pointsX = objectsx.get(i);
            int[] pointsY = objectsy.get(i);
            for(int l = 0; l < pointsX.length;l++){
                pointsx.add(pointsX[l]);
                pointsy.add(pointsY[l]);
            }
        }
    }
}
