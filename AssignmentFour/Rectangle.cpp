#include "Rectangle.h"

using std::string;
using std::vector;

Rectangle::Rectangle(int h, int w) : Shape("Rectangle", "Generic Rectangle"), height(h), width(w) //constructors
{
}

Rectangle::Rectangle(int h, int w, string name) : Shape("Rectangle", name), height(h), width(w)
{

}

double Rectangle::getGeoArea()  
{
	return static_cast<double>(height*width); //cast to double
}

double Rectangle::getGeoPerim()
{
	return static_cast<double>((height+width)*2);
}

int Rectangle::getScrArea()
{
	return height*width;
}

int Rectangle::getScrPerim()
{
	return ((height+width)*2)-4;
}

int Rectangle::getBoxWidth()
{
	return width;
}

int Rectangle::getBoxLength()
{
	return height;
}

void Rectangle::scale(int factor)
{
	if (height <= factor || width <= factor)  //results in non-zero
		return;
	else
	{
		height += factor;
		width += factor;
	}
}

vector<vector<char>> Rectangle::draw(char pen, char fill)
{
	int length = getBoxLength();
	int width = getBoxWidth();

	vector<vector<char>> image(length);

	for (vector<char>& v : image)
	{
		v = vector<char>(getBoxWidth());

		for (char& c : v)
		{
			c = pen;
		}
	}
	

	return image;
}

