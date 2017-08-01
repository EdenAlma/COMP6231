#include "Rhombus.h"


using std::string;
using std::vector;

Rhombus::Rhombus(int d) : Shape("Rhombus", "Generic Rhombus"), diagonal(d)
{
	if(diagonal%2 == 0)  //if even
	{
		++diagonal;  //make odd
	}
}

Rhombus::Rhombus(int d, string name) : Shape("Rhombus", name), diagonal(d)
{
	if (diagonal % 2 == 0)
	{
		++diagonal;
	}
}

double Rhombus::getGeoArea()
{
	return pow(static_cast<double>(diagonal), 2.0) / 2;
}

double Rhombus::getGeoPerim()
{
	return 2 * sqrt(2) * diagonal;
}

int Rhombus::getScrArea()
{
	return 2 * (diagonal - 1);
}

int Rhombus::getScrPerim()
{
	int n = diagonal / 2;
	return (2 * n * (n + 1)) + 1;
}

int Rhombus::getBoxWidth()
{
	return diagonal;
}

int Rhombus::getBoxLength()
{
	return diagonal;
}

void Rhombus::scale(int factor)
{
	if ((diagonal + 2 * factor) >= 1)
	{
		diagonal = diagonal + 2 * factor;
	}
}

vector<vector<char>> Rhombus::draw(char pen, char fill)
{
	int length = getBoxLength();
	int width = getBoxWidth();   //ranges for calculation
	int mid = length / 2;   

	vector<vector<char>> image(length);

	for (vector<char>& v : image)
	{
		v = vector<char>(width);
	}

	for (int i = 0; i < length; ++i)    //constructing shape
	{
		for (int k = 0; k < width; ++k)
		{
			if (i == mid)
			{
				image[i][k] = pen;
			}
			else if (i < mid)
			{
				if (abs(k - mid) <= i)
				{
					image[i][k] = pen;
				}
				else
					image[i][k] = fill;
			}
			else
			{
				if (abs(k - mid) < abs(i-length))
				{
					image[i][k] = pen;
				}
				else
					image[i][k] = fill;
			}
		}
	}


	return image;
}


