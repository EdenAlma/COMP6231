#ifndef SLOTMACHINE_H
#define SLONTMACHINE_H

#include"Rectangle.h"
#include"Isosceles.h"
#include"Rhombus.h"
#include"RightTriangle.h"
#include<array>

class SlotMachine
{
private:
	int tokens;  //number of start tokens
	std::vector<std::vector<char>> createGrid();  //image of slot
	void copyInto(std::vector<std::vector<char>>& from, std::vector<std::vector<char>>& to, int vertical, int horizonatal);
	int multiplier();  
	std::array < Shape*, 3 > shape_reel{};  //real holding shape ptrs
	void make_shapes();
	void make_shape(int);  //allocate mem
	void displayShapes();
	void report_status();  
	void release_shapes();  //deallocate meme
	void welcome();  //message
	SlotMachine(const SlotMachine&) = delete;

public:
	SlotMachine() = default;
	SlotMachine& operator=(const SlotMachine&) = delete;
	void run();  //run
	virtual ~SlotMachine();  //dstr -->dealocates in case of mem leak
};

#endif