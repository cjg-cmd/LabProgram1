package racetrack;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class CPUCar extends Car {
	
	String type = "DEFAULT";
	
	int currentMovePos;
	
	public CPUCar(char id, String type)
	{
		super(id);
		this.type = type;
		currentMovePos = 1;
 	}
 
	
 	@Override
	public void move(Racetrack track) {

	    int rangeY = this.getRowVelocity();
	    int rangeX = this.getColVelocity();

	    //starting positions
	    int startRow = this.getRow();
	    int startCol = this.getCol();

	    int bestRow = startRow; 
	    int bestCol = startCol; 

	    int lowestWeight = 9999; 

	    //Check adjacent coordinates, find next coordinate
	    
	    if(type=="USER")
	    {	
	    	Scanner scnr = new Scanner(System.in);
	    	
	    	System.out.print("Please input row: ");	    	
	    	int row = scnr.nextInt();
	    	
	    	System.out.print("Please input col: ");	    	
	    	int col = scnr.nextInt();
	    	
	    	bestRow = this.getRow() + Math.min(row, this.getMaxSpeed());  
	    	bestCol = this.getCol() + Math.min(col, this.getMaxSpeed());  
	    }
	    
	    if(type!="USER")
	    {
		    for (int ry = -rangeY; ry <= rangeY; ry++) {
		        for (int rx = -rangeX; rx <= rangeX; rx++) {
	
		        	// set adjacent coordinates
		            int row = startRow + ry; 
		            int col = startCol + rx;
	
		            // Bounds
		            if (row < 0 || row >= track.height()) continue;
		            if (col < 0 || col >= track.width()) continue;
		            if (track.occupied(row, col)) continue; 
	
		            int weight = track.getWeight(row, col);
	
		            // Found better tile
		            if (weight < lowestWeight) 
		            {
	
		                lowestWeight = weight;
		                bestRow = row;
		                bestCol = col;
	
		            }
		        }
	 	    }
	    }
	    
	    //Check path before moving to new position.
	    
	    Path path = new Path(this.getCol(), this.getRow(), bestCol, bestRow, track);
	    path.GetResults(); //Store path positions.
	    
	    Position validPos = null;
  	    
	    for(int i=1; i<path.poses.size(); i++)
	    {
	    	Position currentPos = path.poses.get(i);

	    	int row = currentPos.getRow();
	    	int col = currentPos.getCol();
	    	
			if(  track.getWeight(row, col) == 9999 || (track.getCar(row, col) != '0' && track.getCar(row, col) != this.getIdNumber() ) )
			{
				
				carCollision(); //If I don't add this the agile cars do not move when stuck.
				
				if(type=="SPORT" || type=="USER")
				{
					if(track.getWeight(row, col) != 9999)
					{
						System.out.println("Car "+this.getIdNumber()+" has hit Car "+track.getCar(row, col)+" at ("+row+","+col+")");
					}else{
						System.out.println("Car "+this.getIdNumber()+" has struck a wall at "+" at ("+row+","+col+")");
					}
					
					bestRow = this.poses.get(currentMovePos-1).getRow();
					bestCol = this.poses.get(currentMovePos-1).getCol();
				}
				
 				break;
			}
			else
			{
		    	validPos = currentPos;
			}
			
	    }
	    
	    if(type=="AGILE")
	    {
		    if(validPos != null)
		    {
		    	bestRow = validPos.getRow();
		    	bestCol = validPos.getCol();
		    }
		    else
		    {
		    	bestRow = this.getRow();
		    	bestCol = this.getCol();
		    }
 
	    }
	    
 	    poses.add( new Position( bestRow, bestCol ) ); //Add pose to `poses` ADT.
 	    currentMovePos++;
 	    
		this.updateCarInfo(track, bestRow, bestCol);	

		
		if( track.getWeight(bestRow, bestCol) == 0 )
		{
			this.setWinner(true);
		}
		
 	    if (this.getWinner()) 
 	    {
	        track.setWeight(bestRow, bestCol, -1);
	    }

 	}

}
