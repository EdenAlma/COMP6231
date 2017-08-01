#ifndef SHAPE_H
#define SHAPE_H

#include<string>
#include<vector>   //includes get inherited down
#include<iostream>

class Shape
{
private:
	static int nextID;
	std::vector<std::vector<char>> image;
	int idNum;
	std::string generic;   //data memebers
	std::string descriptive;

public:
	Shape(std::string, std::string);
	int getID();
	std::string getGeneric();
	std::string getDescriptive();   //concrete "implemented" methods
	void setDescriptive(std::string);
	std::string toString();

	friend std::ostream& operator<<(std::ostream&, Shape&);   //friend overloads used available to Shapes
	friend std::ostream& operator<<(std::ostream&, std::vector<std::vector<char>> grid);

	virtual double getGeoArea() = 0;
	virtual double getGeoPerim() = 0;
	virtual int getScrArea() = 0;   //virtual abstact methods
	virtual int getScrPerim() = 0;
	virtual int getBoxWidth() = 0;
	virtual int getBoxLength() = 0;
	virtual void scale(int) = 0;
	virtual std::vector<std::vector<char>> draw(char pen = '*', char fill = ' ') = 0;



};

#endif