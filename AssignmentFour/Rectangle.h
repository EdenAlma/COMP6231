#ifndef RECTANGLE_H
#define RECTANGLE_H

#include"Shape.h"

class Rectangle : public Shape
{
private:
	int height;
	int width;   //members

public:
	Rectangle(int, int);
	Rectangle(int, int, std::string);
	virtual double getGeoArea();
	virtual double getGeoPerim();
	virtual int getScrArea();       //methods
	virtual int getScrPerim();
	virtual int getBoxWidth();
	virtual int getBoxLength();
	virtual void scale(int);
	virtual std::vector<std::vector<char>> draw(char pen = '*', char fill = ' ');  //draw

};

#endif
