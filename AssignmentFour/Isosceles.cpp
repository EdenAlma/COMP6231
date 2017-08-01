#include "Isosceles.h"
 
using std::string;
using std::vector;

Isosceles::Isosceles(int base) : Triangle(base, "Isosceles", "Generic Isosceles") //constructor
{
	if (getBase() % 2 == 0)  //if odd
	{
		setBase(getBase()+1);  
	}
	setHeight((base+1)/2); //set height
}

Isosceles::Isosceles(int base , string name) : Triangle(base, "Isosceles", name)
{
	if (getBase() % 2 == 0)
	{
		setBase(getBase() + 1);   //same as above 
	}
	setHeight((base + 1) / 2);
}

double Isosceles::getGeoPerim()
{
	return getBase() + 2*sqrt((0.25*pow(static_cast<double>(getBase()) ,2)) + pow(static_cast<double>(getHeight()), 2)); //geo perim
}

int Isosceles::getScrArea()
{
	return getHeight()*getHeight();  //scr area
}

int Isosceles::getScrPerim()
{
	return 4 * (getHeight() - 1); 
}

int Isosceles::getBoxWidth()
{
	return getBase();
}

int Isosceles::getBoxLength()
{
	return getHeight();
}

void Isosceles::scale(int factor)
{
	if ((getBase() + 2 * factor) >= 1)
	{
		setBase((getBase() + 2 * factor));
		setHeight((getBase() + 1) / 2);
	}
}

vector<vector<char>> Isosceles::draw(char pen, char fill)
{
	int length = getBoxLength();
	int width = getBoxWidth();
	int mid = width / 2;

	vector<vector<char>> image(length);

	for (vector<char>& v : image)
	{
		v = vector<char>(width);
	}

	for (int i = 0; i < length; ++i)
	{
		for (int k = 0; k < width; ++k)
		{
			if (abs(k - mid) <= i)  //conditions for creating Iso
			{
				image[i][k] = pen;
			}
			else
				image[i][k] = fill;
		}
	}

	return image;
}
