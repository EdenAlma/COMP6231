#include "Triangle.h"

using std::string;

Triangle::Triangle(int b, string gen ,string des) : Shape(gen, des), base(b)
{
}

int Triangle::getBase()
{
	return base;
}

int Triangle::getHeight()
{
	return height;
}

double Triangle::getGeoArea()
{
	return (base*height) / 2.0;
}

void Triangle::setHeight(int h)
{
	height = h;
}

void Triangle::setBase(int b)
{
	base = b;
}
