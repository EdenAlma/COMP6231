#ifndef ISOSCLELES_H
#define ISOSCELES_H

#include"Triangle.h"

class Isosceles : public Triangle
{
public:
	Isosceles(int);
	Isosceles(int, std::string);
	virtual double getGeoPerim();
	virtual int getScrArea();
	virtual int getScrPerim();
	virtual int getBoxWidth();		//virtual methods
	virtual int getBoxLength();
	virtual void scale(int);
	virtual std::vector<std::vector<char>> draw(char pen = '*', char fill = ' ');
};

#endif