package racetrack;

public class Position {
/** Row coordinate for a track/weight position **/
	private int row;
	/** Column coordinate for a track/weight position **/
	private int col;
	/**
	* Constructor
	*/
	public Position(int row, int col) {
		this.row = row;
		this.col = col;
	}
	/**
	* Get / Set Methods
	* 
	*C
	* 
	*/
	
	public boolean collides(Racetrack track, Car car)
	{
		return (track.getCar(row, col)!='0' || track.getWeight(row, col)==9999);
 	}
	
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getCol() {
		return col;
	}
	public void setCol(int col) {
		this.col = col;
	}
}
