#pragma once

#include <glad/glad.h>
#include <stdlib.h> 
#include "raytraceData.h"



class shader {
public:
	shader();
	~shader();

	raytraceData::material* makeMaterial(GLfloat r, GLfloat g, GLfloat b, GLfloat amb, GLfloat diff, GLfloat spec);
	void shade(raytraceData::point* p, raytraceData::vector* n, raytraceData::material* m, raytraceData::color* c, raytraceData::point* vp);
	raytraceData::vector* shader::normalize(raytraceData::vector* n);
	float shader::dot(raytraceData::vector* v, raytraceData::vector* n);
	raytraceData::vector* shader::sub(raytraceData::point* v, raytraceData::point* p);
	raytraceData::light* shader::makeLight(GLfloat x, GLfloat y, GLfloat z, GLfloat diff, GLfloat spec, GLfloat shi);
	raytraceData::point* makePoint(GLfloat x, GLfloat y, GLfloat z, GLfloat w);
	raytraceData::vector* shader::mult(raytraceData::vector* v, float f);
	raytraceData::vector* shader::add(raytraceData::vector* v1, raytraceData::vector* v2);
	//raytrace raytrace;

};
