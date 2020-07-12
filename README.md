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

GilbertPRoJ is a physical, photorealistic ray tracing shader, standing for "Gilbert Physical Raytracing Java".
This uses actual photons to render a scene and traces the photons from the light source.
This shader is incredibly taxing on the hardware and requires a lot of time to render a scene.
It is intended for people wanting to either mess around with it or
for product logos that need photorealism for professionalism.

