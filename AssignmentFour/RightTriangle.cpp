#include "RightTriangle.h"


using std::string;
using std::vector;

RightTriangle::RightTriangle(int base):Triangle(base,"Right Triangle", "Generic Right Triangle")
{
	setHeight(base);
}

RightTriangle::RightTriangle(int base, string name):Triangle( base, "Right Triangle", name)
{
	setHeight(base);
}


double RightTriangle::getGeoPerim()
{
	return (sqrt(2) + 2)* getHeight();
}

int RightTriangle::getScrArea()
{
	return (getHeight()*(getHeight() + 1))/ 2;
}

int RightTriangle::getScrPerim()
{
	return 3 * (getHeight() - 1);
}

int RightTriangle::getBoxWidth()
{
	return getBase();
}

int RightTriangle::getBoxLength()
{
	return getHeight();
}

void RightTriangle::scale(int factor)
{
	if ((getBase() + factor) >= 1)
	{
		setHeight(getBase() + factor);
		setBase(getBase() + factor);
	}
}

vector<vector<char>> RightTriangle::draw(char pen, char fill)
{
	int length = getBoxLength();
	int width = getBoxWidth();
	vector<vector<char>> image(getBoxLength());

	for (vector<char>& v : image)
	{
		v = vector<char>(width);
	}

	for (int i = 0; i < length; ++i)
	{
		for (int k = 0; k < width; ++k)
		{
			if (k <= i)
			{
				image[i][k] = pen;
			}
			else
			{
				image[i][k] = fill;
			}
		}
	}

	return image;
}


