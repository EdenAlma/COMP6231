#include "Shape.h"
#include<typeinfo>
#include<iomanip>
#include <sstream>

using std::string;
using std::to_string;
using std::stringstream;
using std::vector;


int Shape::nextID = 1;  //set up static int for ID

Shape::Shape(std::string gen, std::string des) :  generic(gen), descriptive(des), idNum(nextID++)
{
}

int Shape::getID()
{
	return idNum;
}


string Shape::getGeneric()
{
	return generic;
}

string Shape::getDescriptive()
{
	return descriptive;
}

void Shape::setDescriptive(string input)
{
	descriptive = input;
}

string Shape::toString()  //toString method
{
	string output;
	stringstream stream;

	output += "Shape Information\n-----------------\n";
	output += "Static type:   ";
	output += typeid(this).name();
	output += "\nDynamic type:  ";
	output += typeid(*this).name();
	output += "\nGeneric name:  ";
	output += getGeneric();
	output += "\nDescription:   ";
	output += getDescriptive();
	output += "\nid:            ";
	output += to_string(getID()); 
	output += "\nB. Box width:  ";
	output += to_string(getBoxWidth());
	output += "\nB. Box length: ";
	output += to_string(getBoxLength());
	output += "\nScr area:      ";
	output += to_string(getScrArea());
	output += "\nGeo area:      ";
	stream << std::fixed << std::setprecision(2) << getGeoArea();
	output += stream.str();
	stream.str("");
	output += "\nScr perimeter: ";
	output += to_string(getScrPerim());
	output += "\nGeo perimeter: ";
	stream << std::fixed << std::setprecision(2) << getGeoPerim();
	output += stream.str();
	stream.clear();

	return output;

}

std::ostream& operator<<(std::ostream& out , Shape& s)
{
	out << s.toString();
	return out;                                                     // << overloads
}

std::ostream & operator<<(std::ostream & out, vector<vector<char>> grid)
{
	for (vector<char>& v : grid)
	{
		for (char & c : v)
		{
			out << c;
		}
		out << "\n";
	}
	
	return out;
}


