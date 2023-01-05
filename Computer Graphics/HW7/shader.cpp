#include "shader.h"

using namespace raytraceData;
#include<cmath>

vector* shader::normalize(vector* n)
{
	float length = sqrt(n->x * n->x + n->y * n->y + n->z * n->z);
	n->x /= length;
	n->y /= length;
	n->z /= length;
	return(n);
}
float shader::dot(vector* v, vector* n)
{
	float d = v->x * n->x + v->y * n->y + v->z * n->z;

	return d;
}
vector* shader::sub(point* p1, point* p2)
{
	point* v = new point();
	v->x = p1->x - p2->x;
	v->y = p1->y - p2->y;
	v->z = p1->z - p2->z;
	return (v);
}

vector* shader::add(vector* v1, vector* v2)
{
	vector* v3 = new vector();
	v3->x = v1->x + v2->x;
	v3->y = v1->y + v2->y;
	v3->z = v1->z + v2->z;
	return (v3);
}

vector* shader::mult(vector* v2, float f)
{
	vector* v = new vector();
	v->x = v2->x * f;
	v->y = v2->y * f;
	v->z = v2->z * f;
	return (v);
}

point* shader::makePoint(GLfloat x, GLfloat y, GLfloat z, GLfloat w) {
	point* p;
	/* allocate memory */
	p = new point();
	/* put stuff in it */
	p->x = x; p->y = y; p->z = z; p->w = w;
	return (p);
}

shader::shader()
{
}

shader::~shader()
{
}
light* shader::makeLight(GLfloat x, GLfloat y, GLfloat z, GLfloat diff, GLfloat spec, GLfloat shi) {
	light* l;

	/* allocate memory */
	l = new light();

	/* put stuff in it */
	l->p = makePoint(x, y, z, 1.0);
	l->diffuse = diff;
	l->spec = spec;
	l->shi = shi;


	return(l);
}

material* shader::makeMaterial(GLfloat r, GLfloat g, GLfloat b, GLfloat amb, GLfloat diff, GLfloat spec) {
	material* m;

	/* allocate memory */
	m = new material();

	/* put stuff in it */
	m->c.r = r;
	m->c.g = g;
	m->c.b = b;
	m->amb = amb;
	m->diffuse = diff;
	m->specular = spec;

	return(m);
}

/* LIGHTING CALCULATIONS */

/* shade */
/* color of point p with normal vector n and material m returned in c */
void shader::shade(point* p, vector* n, material* m, color* c, point* vp) {

	light* l;
	l = makeLight(10.0, -5.0, 5.0, 1.0, 0.7, 50.0);
	n = normalize(n);
	/* so far, just finds ambient component of color */
	c->r = m->amb * m->c.r;
	c->g = m->amb * m->c.g;
	c->b = m->amb * m->c.b;

	/*diffuse*/
	vector* L = new vector();
	L = normalize(sub(l->p, p));
	float diff = max(dot(L, n), 0.0);
	c->r += diff * m->diffuse * l->diffuse * m->c.r;
	c->g += diff * m->diffuse * l->diffuse * m->c.g;
	c->b += diff * m->diffuse * l->diffuse * m->c.b;

	/*specular*/
	vp = mult(vp, -1);
	vector* V = normalize(vp);
	vector* H = add(L, V);
	H = normalize(H);
	float spec = pow(max(dot(H, n), 0.0), l->shi);
	c->r += spec * l->spec * m->specular;
	c->g += spec * l->spec * m->specular;
	c->b += spec * l->spec * m->specular;

	/* clamp color values to 1.0 */
	if (c->r > 1.0) c->r = 1.0;
	if (c->g > 1.0) c->g = 1.0;
	if (c->b > 1.0) c->b = 1.0;

}