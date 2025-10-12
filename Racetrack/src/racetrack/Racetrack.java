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

	private HashMap<Character, Integer> weightTypes = new HashMap<Character, Integer>(); //weight types 
	private ArrayList<String> track = new ArrayList<String>(); //Contains weights
	private ArrayList<Integer> weights = new ArrayList<Integer>();

	public int w, h;
	private boolean success = true;
 
	private <T> void LOGN( T x )	{
		System.out.println(x);
	}
	
	public Racetrack(String filename)
	{
		weightTypes.put('T', -1);
		weightTypes.put('X', 9999);
		weightTypes.put('F', 0);
			
		readFile(filename); //Store weight info in track array.
		
		computeWeights();
		compute( 0 ); //Start method at weight 0.
	}
	
	private boolean checkAdj(int x, int y, int val)
	{
		boolean changes = false;
	
		for(int i=0; i<9; i++)
		{
			int xc = i/3 - 1; //Centerjng the box
			int yc = i%3 - 1; //Centering the box
			
			if(xc==0 && yc==0) continue; //Ignore center (us)
			
			int adj_x = x + xc; //( Adj_x relative to node x)
			int adj_y = y + yc; // (Adj_y relative to node y)
			
			//Check out of bounds.
			if(adj_x > w-1 || adj_x < 0) continue;
			if(adj_y > h-1 || adj_y < 0) continue;
			
			//Get weight coordinates in array.
			int index = adj_x + adj_y * w; 
 			
			//grab weight from array.
			int adj_weight = weights.get( index );
			
			//If spot in array is open then take it.
			if(adj_weight == -1)
			{
				//Need to set changes to true indicating that the function needs to recurse.
  				weights.set(adj_x + adj_y * w, val);
  				changes = true;
			}
		}
		return changes;
 	}
	
	private void computeWeights()
	{
		//Setting up the weights array by iterating through the track array
		
		for( int y=0; y<h ; y++)
		{
			for(int x=0; x<w; x++)
			{
				int weight;
				char weightType = track.get(y).charAt(x);
				
				if(weightTypes.containsKey( weightType ))
				{
					weight = weightTypes.get(weightType);
				}
				else
				{
					weight = -1;
				}
				
				weights.add(weight);
			}
		}
	}
	
	private void compute(int start)
	{	
		//The prime function that iterates through the weights array to fill the empty weights accordingly. 
		boolean changed = false;
		
		for( int y=0; y<h ; y++)
		{
			for(int x=0; x<w; x++)
			{
				int weight = weights.get(x + y * w);

				if(weight == start)
				{
					changed |= checkAdj( x, y, start+1);  //boolean false = 0. so if checkAdj == 1 then (changed | checkAdj) will equal 1 and will remain 1.
				}
 			}
		}
		 
		if(changed) compute(start + 1);
 	}

	private void readFile(String path)
	{
  		try {
			File file = new File(path);
			Scanner input  = new Scanner(file);
			
			while(input.hasNext()) 
			{	
				String s = input.next();
				this.h++;
				this.w = s.length();
				track.add( s );
			}
 
		}
  		catch(FileNotFoundException e)
  		{
  			success = false;
			System.out.println(e);
 		}
 	}
	
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
