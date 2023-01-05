#include "tracer.h"

#include <math.h>

using namespace raytraceData;

tracer::tracer() :
	s1(NULL), cy(NULL)
{
}

tracer::~tracer()
{
}

point* tracer::makePoint(GLfloat x, GLfloat y, GLfloat z, GLfloat w) {
	point* p;
	/* allocate memory */
	p = new point();
	/* put stuff in it */
	p->x = x; p->y = y; p->z = z; p->w = w;
	return (p);
}

/* point on ray r parameterized by t is returned in p */
void tracer::findPointOnRay(ray* r, float t, point* p) {
	p->x = r->start->x + t * r->end->x;
	p->y = r->start->y + t * r->end->y;
	p->z = r->start->z + t * r->end->z;
	p->w = 1.0;
}

/* raySphereIntersect */
/* returns TRUE if ray r hits sphere s, with parameter value in t */
int tracer::raySphereIntersect(ray* r, sphere* s, float* t) {
	point p;   /* start of transformed ray */
	float a, b, c;  /* coefficients of quadratic equation */
	float D;    /* discriminant */
	point* v;

	/* transform ray so that sphere center is at origin */
	/* don't use matrix, just translate! */
	p.x = r->start->x - s->c->x;
	p.y = r->start->y - s->c->y;
	p.z = r->start->z - s->c->z;
	v = r->end; /* point to direction vector */


	a = v->x * v->x + v->y * v->y + v->z * v->z;
	b = 2 * (v->x * p.x + v->y * p.y + v->z * p.z);
	c = p.x * p.x + p.y * p.y + p.z * p.z - s->r * s->r;

	D = b * b - 4 * a * c;

	if (D < 0) {  /* no intersection */
		return (FALSE);
	}
	else {
		D = static_cast<float>(sqrt(D));
		/* First check the root with the lower value of t: */
		/* this one, since D is positive */
		*t = (-b - D) / (2 * a);
		/* ignore roots which are less than zero (behind viewpoint) */
		if (*t < 0) {
			*t = (-b + D) / (2 * a);
		}
		if (*t < 0) { return(FALSE); }
		else return(TRUE);
	}
}

/* normal vector of s at p is returned in n */
/* note: dividing by radius normalizes */
void tracer::findSphereNormal(sphere* s, point* p, vector* n) {
	n->x = (p->x - s->c->x) / s->r;
	n->y = (p->y - s->c->y) / s->r;
	n->z = (p->z - s->c->z) / s->r;
	n->w = 0.0;
}

int tracer::rayCylinderIntersect(ray* r, cylinder* cy, float* t) {
	point* v;
	v = r->end;
	float a = (v->x * v->x) + (v->z * v->z);
	float b = 2 * (v->x * (r->start->x - cy->c->x) + v->z * (r->start->z - cy->c->z));
	float c = (r->start->x - cy->c->x) * (r->start->x - cy->c->x) + (r->start->z - cy->c->z) * (r->start->z - cy->c->z) - (cy->r * cy->r);

	float delta = b * b - 4 * (a * c);
	if (fabs(delta) < 0.001) return FALSE;
	if (delta < 0.0) return FALSE;

	float t1 = (-b - sqrt(delta)) / (2 * a);
	float t2 = (-b + sqrt(delta)) / (2 * a);

	if (t1 > t2) *t = t2;
	else *t = t1;

	float rf = r->start->y + *t * v->y;

	if ((rf >= cy->c->y) && (rf <= cy->c->y + cy->h))return TRUE;
	else return FALSE;

}
void tracer::findCylinderNormal(cylinder* v, point* pt, vector* n) {
	n->x = pt->x - v->c->x;
	n->z = pt->z - v->c->z;
	n->y = 0.0;
	n->w = 0.0;
	float length = n->x * n->x + n->y * n->y + n->z * n->z;
	length = sqrt(length);
	n->x /= length;
	n->y /= length;
	n->z /= length;
}

/* trace */
/* If something is hit, returns the finite intersection point p,
   the normal vector n to the surface at that point, and the surface
   material m. If no hit, returns an infinite point (p->w = 0.0) */
void tracer::trace(ray* r, point* p, vector* n, material** m) {
	float t = 0;     /* parameter value at first hit */
	int hit1, hit2 = FALSE;

	hit1 = raySphereIntersect(r, s1, &t);
	hit2 = rayCylinderIntersect(r, cy, &t);

	if (hit1) {
		*m = s1->m;
		findPointOnRay(r, t, p);
		findSphereNormal(s1, p, n);
	}
	else if (hit2) {
		*m = cy->m;
		findPointOnRay(r, t, p);
		findCylinderNormal(cy, p, n);
	}
	else {
		/* indicates nothing was hit */
		p->w = 0.0;
	}


}