# The Gilbert Engine

Welcome to the gilbert engine github homepage.

The gilbert engine is a next generation 2d shader for java.
This shader can do ray tracing and simple polygon shading.

The engine is split into 3 parts, two of which being ray tracing.

GilbertJava is the simple polygon shader.
It renders simple shadows and does global illumination.
It uses some pretty simple math to find the shadow for a 2d object.

GilbertGL is an OpenGL port of GilbertJava
It uses the LWJGL library (highly recommend)
It has greatly better performance than GilbertJava
This can be used in any OpenGL applications, including games.

GilbertJunior is a mathmatical ray tracer and stands for "Gilbert Java Raytracing."
It uses math to draw lines where the rays would go.
It's not photorealistic but is exponentially less taxing on the hardware as GilbertPRoJ.

GilbertPRoJava is a physical, photorealistic ray tracing shader, standing for "Gilbert Physical Raytracing Java".
This uses actual photons to render a scene and traces the photons from the light source.
This shader is incredibly taxing on the hardware and requires a lot of time to render a scene.
It is intended for people wanting to either mess around with it or
for product logos that need photorealism for professionalism.

# Running Instructions

Download the zip and extract

Go into the folder and open (Gilbert Version)/dist/gilbert.jar

You MUST have java installed

# Editing Instructions

The program was designed for netbeans running java 14, so make sure you have those two ready if you want to edit it.

To run the project, download the code and extract the zip file from github. Go into netbeans, click on "file" in the top left, and click open project. Navigate to your downloads folder or wherever you extracted the zip. 

After you open which project you want, open the source packages. Example.java is the examples of the code you would use in your program. You can make changes to whatever you want in the code.


# Implementation in your program (GilbertJava and GilbertGL only)

Putting the gilbert engine in your program is quite easy as the entire engine is contained within a single .java file. 

To put the gilbert engine in your program, you will first need to look at "Running Instructions" above to continue.

After you install the engine into netbeans, take the GilbertEngine.java file and put it in the package you want to use it in. Next, look at the Example.java file in the"Gilbert Java" project in netbeans. See how to use the program and implement them into your program.

Disclaimer: The implementation is currently not available for GilbertJR or GilbertPRoJava
