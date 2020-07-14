# The Gilbert Engine

Welcome to the gilbert engine github homepage.

The gilbert engine is a next generation 2d shader for java.
This shader can do ray tracing and simple polygon shading.

The engine is split into 3 parts, two of which being ray tracing.

GilbertJava is the simple polygon shader.
It renders simple shadows and does global illumination.
It uses some pretty simple math to find the shadow for a 2d object.

GilbertJunior is a mathmatical ray tracer and stands for "Gilbert Java Raytracing."
It uses math to draw lines where the rays would go.
It's not photorealistic but is exponentially less taxing on the hardware as GilbertPRoJ.

GilbertPRoJava is a physical, photorealistic ray tracing shader, standing for "Gilbert Physical Raytracing Java".
This uses actual photons to render a scene and traces the photons from the light source.
This shader is incredibly taxing on the hardware and requires a lot of time to render a scene.
It is intended for people wanting to either mess around with it or
for product logos that need photorealism for professionalism.


# Running Instructions

The engine doesn't require any real installation, but it does not run on it's own.
The program was designed for netbeans 12 running java 14, so make sure you have those two ready if you want to use it.

To run the project, download the code and extract the zip file from github. Go into netbeans, click on "file" in the top left, and click open project. Navigate to your downloads folder or wherever you extracted the zip. 

Choose GilbertJava for a simple polygon shader. This engine is recommended as it performs the best and is the only fully developed shader. 

Choose GilbertJR if you want a simple raytracer. This is advised to not be chosen as it can be buggy and is performance hungry, however you can still use it.

Choose GilbertPRoJ if you want a physical, realistic raytracer. This is not recommended to be used as it's still in early development and it lacks many standard features

After you open which project you want, open the source packages and open Example.java. Here, you can see examples of the code you would use in your program. When you want to see the example running, click the play button at the top to see the engine in action. You can make changes to whatever you want in the code.


# Implementation in your program (GilbertJava only)

Putting the gilbert engine in your program is quite easy as the entire engine is contained within a single .java file. 

To put the gilbert engine in your program, you will first need to look at "Running Instructions" above to continue.

After you install the engine into netbeans, take the GilbertEngine.java file and put it in the package you want to use it in. Next, look at the Example.java file in the"Gilbert Java" project in netbeans. See how to use the program and implement them into your program.

Disclaimer: The implementation is currently not available for GilbertJR or GilbertPRoJava
