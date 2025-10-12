package racetrack;

 
public class Main {
	
/*
   _____              .___       ___.           _________        .__  .__           ________             .__                 __   
  /     \ _____     __| _/____   \_ |__ ___.__. \_   ___ \  ____ |  | |__| ____    /  _____/  ____  __ __|  | _____ ________/  |_ 
 /  \ /  \\__  \   / __ |/ __ \   | __ <   |  | /    \  \/ /  _ \|  | |  |/    \  /   \  ___ /  _ \|  |  \  | \__  \\_  __ \   __\
/    Y    \/ __ \_/ /_/ \  ___/   | \_\ \___  | \     \___(  <_> )  |_|  |   |  \ \    \_\  (  <_> )  |  /  |__/ __ \|  | \/|  |  
\____|__  (____  /\____ |\___  >  |___  / ____|  \______  /\____/|____/__|___|  /  \______  /\____/|____/|____(____  /__|   |__|  
        \/     \/      \/    \/       \/\/              \/                    \/          \/                       \/             
        
*/
	
	public static void main(String[] args)
	{
		long last = System.nanoTime();
		
		Racetrack racetrack = new Racetrack("track1.txt"); //Change path accordingly
		System.out.printf( "Track generated in: %.3f seconds \n\n", (float)(System.nanoTime() - last) / 1_000_000_000.0 );
		
		racetrack.print();
	}
}
