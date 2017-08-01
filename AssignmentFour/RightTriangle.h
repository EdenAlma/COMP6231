#ifndef RiGHTTRIANGLE_H
#define RIGHTTRIANGLE_H

#include"Triangle.h"

class RightTriangle : public Triangle
{
public:
	RightTriangle(int);
	RightTriangle(int, std::string);
	virtual double getGeoPerim();
	virtual int getScrArea();
	virtual int getScrPerim();
	virtual int getBoxWidth();
	virtual int getBoxLength();
	virtual void scale(int);
	virtual std::vector<std::vector<char>> draw(char pen = '*', char fill = ' ');
};

#endif
