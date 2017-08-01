#ifndef TRIANGLE_H
#define TRIANGLE_H

#include"Shape.h"

class Triangle : public Shape
{
private:
	int base;
	int height;   //memeber used by derived classes through getter/setter

public:
	Triangle(int, std::string, std::string);
	int getBase();
	int getHeight();    //methods implemented for this class
	double getGeoArea();
	void setHeight(int);
	void setBase(int);
};

#endif
