#pragma once

#include "raytraceData.h"
#include "shader.h"

class tracer {
public:
	tracer();
	~tracer();

	void findPointOnRay(raytraceData::ray* r, float t, raytraceData::point* p);
	int raySphereIntersect(raytraceData::ray* r, raytraceData::sphere* s, float* t);
	void findSphereNormal(raytraceData::sphere* s, raytraceData::point* p, raytraceData::vector* n);
	void trace(raytraceData::ray* r, raytraceData::point* p, raytraceData::vector* n, raytraceData::material** m);
	raytraceData::point* tracer::makePoint(GLfloat x, GLfloat y, GLfloat z, GLfloat w);
	int tracer::rayCylinderIntersect(raytraceData::ray* r, raytraceData::cylinder* theCylinder, float* t);
	void tracer::findCylinderNormal(raytraceData::cylinder* v, raytraceData::point* pt, raytraceData::vector* n);
	raytraceData::sphere* s1;	   /* the scene: so far, just one sphere */
	raytraceData::cylinder* cy;	   /* the scene: so far, just one sphere */
	shader shader;

};