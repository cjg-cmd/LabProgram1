package racetrack;

 
public class Main {
	
	
	public static void main(String[] args)
	{
		
		
 		
		long last = System.nanoTime();
		
		Racetrack racetrack = new Racetrack("track1.txt"); //Change path accordingly
		System.out.printf( "Track generated in: %.3f seconds \n\n", (float)(System.nanoTime() - last) / 1_000_000_000.0 );
		
		racetrack.print();
 		
 		
	}
}
