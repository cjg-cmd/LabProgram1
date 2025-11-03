package racetrack;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.io.File;  


public class Racetrack {

	/*
	 * 
	 * 
	 *  T = BLANK SPACE
		X = WALL
		F = FINISH LINE
	
		WALL WEIGHT -> 9999
		FINISH WEIGHT -> 0
		REMAINING SPACES (BLANK SPACE (T)) -> -1
		
		ANY CHARACTER THAT IS INCLUDED IN THE MAP THAT ISN'T LISTED IN THE WEIGHT TYPES HASH
		MAP WILL BE DEFAULTED TO A WEIGHT OF -1.
		
	 * 
	 * 
	 * 
	 * */


	public void print()
	{
		//Prints current weight array.
		if(!success)
		{
			System.out.println("Cannot print track.");
			return;
		}
 		
		LOGN("=================TRACK WEIGHTS===================");	
 		
		for( int y=0; y<h ; y++)
		{
			LOGN("");
 
			for(int x=0; x<w; x++)
			{
				int weight = weights.get(x + y * w);
				
				switch(weight)
				{
					case 9999:
			            System.out.printf("%-4s", "X");
					break;
					default:
			            System.out.printf("%-4d", weight);
					break;
				}
			}
		}
		LOGN("");
		LOGN("");
		LOGN("=================TRACK WEIGHTS===================");
		LOGN("Track Dimensions: "+h+"x"+w);
	}
	
}
