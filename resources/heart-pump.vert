
uniform int TIME_FROM_INIT;
uniform mat4 transform;
uniform mat4 modelview;
uniform mat3 normalMatrix;
uniform vec3 lightNormal;

attribute vec4 vertex;
attribute vec4 color;
attribute vec3 normal;

varying vec4 outvertex;
varying vec3 outcolor;
varying vec3 outnormal;
varying vec3 outlightdir;


const float PI = 4 * atan(1.0);
const float speed = PI * 1.5;

float steptime;
float spacecomp;
float timecomp;
float scale;
mat3 beatMatrix;

void main()
{
	steptime = speed * float(TIME_FROM_INIT)/1000.0;

	spacecomp = sin(vertex.y * 0.03)/10;
	timecomp = sin(steptime) + sin(2.0 * steptime);
	scale = spacecomp * timecomp  + 0.8;
	beatMatrix = mat3(scale);
	
	gl_Position = transform * vec4(beatMatrix * vertex.xyz, 1.0);
	outvertex = modelview * vertex;
	outcolor = color.rgb;
	outnormal = normalize(normalMatrix * normal);
	outlightdir = -lightNormal;
}
