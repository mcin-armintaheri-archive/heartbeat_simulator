#define PROCESSING_LIGHT_SHADER

varying vec4 outvertex;
//varying vec3 outcolor;
varying vec3 outnormal;
varying vec3 outlightdir;


vec3 lightvec;
vec4 iDiff;

void main()
{
   vec3 outcolor = vec3(0.9,0.1,0.1);
   vec3 L = normalize(outlightdir - outvertex.xyz);   
   vec4 Idiff = vec4(outcolor * max(dot(outnormal,L), 0.0), 1.0);  
   Idiff = clamp(Idiff, 0.0, 1.0); 

   gl_FragColor = Idiff;
}
