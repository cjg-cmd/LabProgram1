package racetrack;

 
public class Main {
	
/*
   _____              .___       ___.           _________        .__  .__           ________             .__                 __   
  /     \ _____     __| _/____   \_ |__ ___.__. \_   ___ \  ____ |  | |__| ____    /  _____/  ____  __ __|  | _____ ________/  |_ 
 /  \ /  \\__  \   / __ |/ __ \   | __ <   |  | /    \  \/ /  _ \|  | |  |/    \  /   \  ___ /  _ \|  |  \  | \__  \\_  __ \   __\
/    Y    \/ __ \_/ /_/ \  ___/   | \_\ \___  | \     \___(  <_> )  |_|  |   |  \ \    \_\  (  <_> )  |  /  |__/ __ \|  | \/|  |  
\____|__  (____  /\____ |\___  >  |___  / ____|  \______  /\____/|____/__|___|  /  \______  /\____/|____/|____(____  /__|   |__|  
        \/     \/      \/    \/       \/\/              \/                    \/          \/                       \/             
        
  
  READ: YOU MUST PUT YOUR TRACK IN THE Tracks FOLDER IN THE FORM OF A TXT DOCUMENT IN ORDER FOR IT TO GENERATE.**
  THANK YOU!
        
*/
	
	public static void main(String[] args)
	{
		long last = System.nanoTime();
		
		String examplePath = "./Tracks/track1.txt";  //Change path accordingly**
		Racetrack racetrack = new Racetrack(examplePath); //Set up track
		
		System.out.printf( "Track generated in: %.3f seconds \n\n", (float)(System.nanoTime() - last) / 1_000_000_000.0 );
		racetrack.print(); //Print track
	}
}
