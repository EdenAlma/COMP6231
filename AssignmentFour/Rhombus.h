#ifndef RHOMBUS_H
#define RHOMBUS_H

#include"Shape.h"

class Rhombus : public Shape
{
private:
	int diagonal;  //data memeber

public:
	Rhombus(int);
	Rhombus(int, std::string);
	virtual double getGeoArea();
	virtual double getGeoPerim();
	virtual int getScrArea();
	virtual int getScrPerim();   //same as other shapes
	virtual int getBoxWidth();
	virtual int getBoxLength();
	virtual void scale(int);
	virtual std::vector<std::vector<char>> draw(char pen = '*', char fill = ' ');

};

#endif