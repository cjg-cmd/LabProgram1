package racetrack;

public class CPUCar extends Car {

	
	public CPUCar(char id)
	{
		super(id);
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

	    //Check adjacent coordinates.
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

	                // 0 is finish line
	                if (weight == 0)
	                {
	                    this.setWinner(true);
	                }
	            }
	        }
	    }

 	    track.setCar(startRow, startCol, '0'); //Clear old position.
	    track.setCar(bestRow, bestCol, getIdNumber()); //set new position.

 	    if (this.getWinner()) 
 	    {
	        track.setWeight(bestRow, bestCol, -1);
	    }

	    this.updateCoordinates(bestRow, bestCol);
	}

}
