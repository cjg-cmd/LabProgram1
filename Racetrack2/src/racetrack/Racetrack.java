package racetrack;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Racetrack {

    /** Racetrack from text file **/
	
	private boolean success = true;
	
    private char[][] track;
    /** Weights of the track position **/
    private int[][] weights;
    
    /**Car poses in track **/
    private char[][] carPoses;
    
    /** Constant Value for finish line weight **/
    final private int FINISH_WEIGHT = 0;
    /** Constant Value for wall weight **/
    final private int WALL_WEIGHT = 9999;
    /** Constant Value for pre initialized track weight **/
    final private int INIT_TRACK_WEIGHT = -1;


	public Racetrack(String filename)
	{
 
		//useDefaultTrack();
		
		readFile(filename); //Store weight info in track array.
		setWeights();
		compute( FINISH_WEIGHT ); //Start method at weight 0.
	}
	
    
    /**
     * @return - track position
     */
    public char getTrack(int row, int col){
        return track[row][col];
    }
    
    public int getWeight(int row, int col) {
    	return weights[row][col];
    }

    /**
     * Update position on track
     */
    public void setTrack(int row, int col, char value){
        track[row][col] = value;
    }
    
    public void setWeight(int row, int col, int value){
        weights[row][col] = value;
    }
    /**
     * @return - number of rows
     */
    public int height() {
        return track.length;
    }

    /**
     * @return - number of columns
     */
    public int width() {
        return track[0].length;
    }
    
    public boolean occupied(int row, int col)
    {
    	return getCar(row, col) != '0';
    }
    
    public void setCar(int row, int col, char value)
    {
     	carPoses[row][col] = value;
    }
    
    public char getCar(int row, int col)
    {
    	return carPoses[row][col];
    }
	

    /**
     * Display game banner (Optional)
     */
    public void displayBanner(){
		String art = 	"   ______     _     __   ____                           \n" +
						"  / ____/____(_)___/ /  / __ \\____ _________  __________\n" +
						" / / __/ ___/ / __  /  / /_/ / __ `/ ___/ _ \\/ ___/ ___/\n" +
						"/ /_/ / /  / / /_/ /  / _, _/ /_/ / /__/  __/ /  (__  ) \n" +
						"\\____/_/  /_/\\__,_/  /_/ |_|\\__,_/\\___/\\___/_/  /____/  \n" +
						"                                                        ";

        System.out.println(art + "\n");
    }
	
	

    /**
     * This method creates a track and weights using static values (For Demo Purposes only)
     */
    
	private <T> void LOGN( T x )	{
		System.out.println(x);
	}
    
	private boolean checkAdj(int x, int y, int val)
	{
		boolean changes = false;
		int w = width();
		int h = height();
	
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
			
			//grab weight from array.
			int adj_weight = getWeight(adj_y, adj_x); 
			
			//If spot in array is open then take it.
			if(adj_weight == INIT_TRACK_WEIGHT)
			{
				//Need to set changes to true indicating that the function needs to recurse.
   				setWeight(adj_y,adj_x,val);
  				changes = true;
			}
		}
		return changes;
 	}
	
	private void setWeights()
	{
		//Setting up the weights array by iterating through the track array
		int w = width();
		int h = height();
	
		for( int row=0; row<h ; row++)
		{
			for(int col=0; col<w; col++)
			{
				int weight;
				char weightType = getTrack(row,col);  
				
				switch(weightType)
				{
					case 'X':
						weight = WALL_WEIGHT;
					break;
					case 'T':
						weight = INIT_TRACK_WEIGHT;
					break;
					default:
						weight = FINISH_WEIGHT;
					break;
				}
				
				setCar(row,col,'0');
 				setWeight(row,col,weight);
 			}
		}
	}
	
	
	private void compute(int start)
	{	
		//The prime function that iterates through the weights array to fill the empty weights accordingly. 
		int w = width();
		int h = height();
		boolean changed = false;
		
		for( int row=0; row<h ; row++)
		{
			for(int col=0; col<w; col++)
			{
				int weight = getWeight(row,col);   //weights.get(x + y * w);

				if(weight == start)
				{
					changed |= checkAdj( col, row, start + 1);  //boolean false = 0. so if checkAdj == 1 then (changed | checkAdj) will equal 1 and will remain 1.
				}
 			}
		}
		 
		if(changed) compute(start + 1);
 	}

	
	//init function as well.
	private void readFile(String path)
	{
  		try {
			File file = new File(path);
			Scanner input  = new Scanner(file);

 			int h = 0;
 			int w = 0; 
 			String s = "";
 			
 			//Set dimensions.
			while(input.hasNext()) 
			{	
				String current = input.next();
				s+=current;
				w = current.length();
				h++;
 			}
			
			track = new char[h][w]; 
			weights = new int[h][w];
			carPoses = new char[h][w];
 
			for( int row=0; row < height() ; row++)
			{	 
				for(int col=0; col < width(); col++)
				{
					setTrack(row, col, s.charAt( row * w + col));

				}
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
 		
		for( int y=0; y < height() ; y++)
		{
			LOGN("");
 
			for(int x=0; x < width(); x++)
			{
				int weight = getWeight(y,x);  
				
				switch(weight)
				{
					case WALL_WEIGHT:
			            System.out.printf("%-4s", "X");
					break;
					case FINISH_WEIGHT:
			            System.out.printf("%-4s", "F");
					break;
					default:
						System.out.printf("%-4s", getCar(y,x)=='0' ? "" : getCar(y,x));
					break;
				}
			}
		}
		LOGN("");
		LOGN("");
 
	}
	
    public void useDefaultTrack(){
        weights = new int[][]{
                {WALL_WEIGHT, WALL_WEIGHT, WALL_WEIGHT, WALL_WEIGHT, WALL_WEIGHT, WALL_WEIGHT, WALL_WEIGHT, WALL_WEIGHT, WALL_WEIGHT, WALL_WEIGHT, WALL_WEIGHT},
                {WALL_WEIGHT, 11, 11, 11, 11, 11, 11, WALL_WEIGHT, FINISH_WEIGHT, FINISH_WEIGHT, WALL_WEIGHT},
                {WALL_WEIGHT, 10, 10, 10, 10, 10, 11, WALL_WEIGHT, 1, 1, WALL_WEIGHT},
                {WALL_WEIGHT, 10, 9, 9, 9, 10, WALL_WEIGHT, WALL_WEIGHT, 2, 2, WALL_WEIGHT},
                {WALL_WEIGHT, 10, 9, 8, WALL_WEIGHT, WALL_WEIGHT, WALL_WEIGHT, WALL_WEIGHT, 3, 3, WALL_WEIGHT},
                {WALL_WEIGHT, 10, 9, 8, 7, 6, 5, 4, 4, 4, WALL_WEIGHT},
                {WALL_WEIGHT, 10, 9, 8, 7, 6, 5, 5, 5, 5, WALL_WEIGHT},
                {WALL_WEIGHT, 10, 9, 8, 7, 6, 6, 6, 6, 6, WALL_WEIGHT},
                {WALL_WEIGHT, WALL_WEIGHT, WALL_WEIGHT, WALL_WEIGHT, WALL_WEIGHT, WALL_WEIGHT, WALL_WEIGHT, WALL_WEIGHT, WALL_WEIGHT, WALL_WEIGHT, WALL_WEIGHT}
        };
        track = new char[][]{
            {'X','X','X','X','X','X','X','X','X','X','X'},
            {'X','T','T','T','T','T','T','X','F','F','X'},
            {'X','T','T','T','T','T','T','X','T','T','X'},
            {'X','T','T','T','T','T','X','X','T','T','X'},
            {'X','T','T','T','X','X','X','X','T','T','X'},
            {'X','T','T','T','T','T','T','T','T','T','X'},
            {'X','T','T','T','T','T','T','T','T','T','X'},
            {'X','T','T','T','T','T','T','T','T','T','X'},
            {'X','X','X','X','X','X','X','X','X','X','X'},
        };
    }
}


