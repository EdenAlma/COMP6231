#include "SlotMachine.h"
#include<cstdlib>
#include<ctime>
#include<algorithm>

using std::vector;
using std::max;
using std::string;
using std::cout;



void SlotMachine::make_shapes() //calls make shape in each of the slots
{
	srand(time(NULL));

	for (int i = 0; i < 3; ++i)
	{
		make_shape(i);
	}
}

void SlotMachine::make_shape(int i)
{
	int shape = rand() % 4;  //random nums
	int width = rand() % 25 + 1;
	
	
	if (shape == 0)
	{
		shape_reel[i] = new Rhombus(width);
	}
	else if (shape == 1)
	{
		shape_reel[i] = new Isosceles(width);   //switch
	}
	else if (shape == 2)
	{
		shape_reel[i] = new RightTriangle(width);
	}
	else if (shape == 3)
	{
		int height = rand() % 25 + 1;
		shape_reel[i] = new Rectangle(height, width);
	}
	
}

void SlotMachine::displayShapes()
{
	cout << createGrid();  //output vector overloaded << from Shape
	for (int i = 0; i < 3; ++i)
	cout << "(" << shape_reel[i]->getGeneric() << ", " << std::to_string(shape_reel[i]->getBoxWidth()) //display details
		<< ", " << std::to_string(shape_reel[i]->getBoxLength()) << ") ";

}

void SlotMachine::report_status()
{
	int mult = multiplier();   //amount to multiplty wager by

	switch (mult)
	{
	case -1:
		cout << "You lose your bet\nYou now have " << std::to_string(tokens) << " tokens!\n\n";
		break;
	case 0:
		cout << "You don't lose, you don't win, you are safe!\nYou now have " << std::to_string(tokens) << " tokens!\n\n";    //status messages
		break;
	case 2:
		cout << "Congratulations! you win 2 times your bet\nYou now have " << std::to_string(tokens) << " tokens!\n\n";
		break;
	case 3:
		cout << "Congratulations! you win 3 times your bet\nYou now have " << std::to_string(tokens) << " tokens!\n\n";
		break;
	default:
		break;
	}
}

void SlotMachine::release_shapes()  //delete dynamic shape objects
{
	for (int i = 0; i < 3; ++i)
	{
		delete shape_reel[i];
		shape_reel[i] = nullptr;
	}
}


vector<vector<char>> SlotMachine::createGrid()
{
	int tallest = max(max(shape_reel[0]->getBoxLength(), shape_reel[1]->getBoxLength()), shape_reel[2]->getBoxLength());
	int gridWidth = shape_reel[0]->getBoxWidth() + shape_reel[1]->getBoxWidth() + shape_reel[2]->getBoxWidth() + 10;   
	int gridHeight = tallest + 2;										//dimensions for grid
	int shape1Border = shape_reel[0]->getBoxWidth() + 3;
	int shape2Border = shape_reel[1]->getBoxWidth() + 3 + shape1Border;

	vector<vector<char>> grid(gridHeight); 

	for (vector<char>& v : grid)   //construct empty 2d vector
	{
		v = vector<char>(gridWidth);
	}

	for (int i = 0; i < gridHeight; ++i)   
	{
		for (int x = 0; x < gridWidth; ++x)
		{
			if (i == 0 || i == gridHeight - 1)
			{
				if (x == 0 || x == gridWidth - 1 || x == shape1Border || x == shape2Border)
				{
					grid[i][x] = '+';
				}
				else
				{                        //create outlines of grid
					grid[i][x] = '-';
				}
			}
			else
			{
				if (x == 0 || x == gridWidth - 1 || x == shape1Border || x == shape2Border)
				{
					grid[i][x] = '|';
				}
			}
		}
	}



	int h1 = 1;
	int w1 = 2;
	int h2 = 1;                  //coordinates for inserting shape into grid
	int w2 = shape1Border + 2;
	int h3 = 1;
	int w3 = shape2Border + 2;

	vector <vector<char>> temp1 = shape_reel[0]->draw();
	vector <vector<char>> temp2 = shape_reel[1]->draw();  //get temp shape arrays
	vector <vector<char>> temp3 = shape_reel[2]->draw();

	copyInto(temp1, grid, h1, w1);
	copyInto(temp2, grid, h2, w2);  //copy them into grid
	copyInto(temp3, grid, h3, w3);
	

	return grid;   //return grid

}

//method for copying one 2d vec into another
void SlotMachine::copyInto(vector<vector<char>>& from, vector<vector<char>>& to, int vertical, int horizontal)
{
	int h = horizontal;
	int v = vertical;

	for (int i = 0; i < from.size(); ++i)
	{
		for (int x = 0; x < from[0].size(); ++x)
		{
			to[v][h] = from[i][x];
			++h;
		}
		h = horizontal;
		++v;
	}
}

//tests shapes to deterimine multiplier and returns int
int SlotMachine::multiplier()
{
	int al = shape_reel[0]->getBoxLength();
	int aw = shape_reel[0]->getBoxWidth();
	string an = shape_reel[0]->getGeneric();
	int bl = shape_reel[1]->getBoxLength();
	int bw = shape_reel[1]->getBoxWidth();
	string bn = shape_reel[1]->getGeneric();
	int cl = shape_reel[2]->getBoxLength();
	int cw = shape_reel[2]->getBoxWidth();
	string cn = shape_reel[2]->getGeneric();


	if (an == bn && bn == cn)  //3 shapes the same
	{
		if (al == bl && bl == cl && aw == bw && bw == cw)  //3 shapes the same + same size
		{
			return 3;
		}
		else
		{
			return 2;
		}  
	}
	else if (an == bn || bn == cn || an == cn)  // two are the same
	{
		return 0;
	}
	else   //no matches 
	{
		return -1;
	}

}




void SlotMachine::run()
{
	int wager = -1;
	tokens = 10;  //initial token count
	welcome();  //welcome message

	while (true)
	{

		make_shapes();
		cout << "How much would you like to bet (enter 0 to quit)? ";
		std::cin >> wager;  //enter bet
		
		if (wager == 0)
		{
			{
				cout << "Game Over. You now have " << std::to_string(tokens) << " tokens!\n";
				break;
			}
		}

		if (wager > tokens || wager < 0)   //invalid
		{
			cout << "Invalid input! try again\n";
			release_shapes();  //realease dynamic shapes
			continue;
		}
		else
		{
			displayShapes();  //show slots
			tokens += multiplier() * wager;  //change balance
			cout << std::endl;
			report_status();  //show status
			release_shapes();  //realease dynamic shapes
			if (tokens == 0)  // mo money left
			{
				cout << "Game Over. You now have " << std::to_string(tokens) << " tokens!\n";
				break;
			}
			continue;
		}
	
	}

	
}

SlotMachine::~SlotMachine()
{
	for (int i = 0; i < 3; ++i)
	{
		if (shape_reel[i] != nullptr)     //deallocate any memory on heap
		{
			delete shape_reel[i];
			shape_reel[i] = nullptr;
		}
	}
}

void SlotMachine::welcome()  //welcome message
{
	cout << "Welcome to to this 3-Reel Slot Machine Game!\n"
		<< "Each reel will randomly display one of four shapes, each in 25 sizes.\n"
		<< "To win 3 times your be you need 3 similar shapes of the same size.\n"
		<< "To win 2 times your be you need 3 similar shapes.\n"
		<< "To win or lose nothing you need 2 similar shapes.\n"
		<< "Otherwise, you lose your bet.\n"
		<< "You start with 10 free tokens!\n\n";
}