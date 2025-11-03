package racetrack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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
	
 
	public static ArrayList<Car> cars = new ArrayList<Car>(); 
	
	public static void addCar(Car car, Racetrack track)
	{
		car.setPos(track);
		cars.add(car);
	}
	
	public static void displayInfo(Racetrack track)
	{
 
		//Sort list based on weight member
	    Collections.sort(cars, Comparator.comparingInt(Car::getWeightPosition));
        
	    for(int i=0; i<cars.size(); i++)
	    {
	    	cars.get(i).DisplayCarInfo(i+1);
	    }
	}


	public static Car getNextCar(int order)
	{
		for(Car car : cars)
		{
			if(car.getMoveOrder() == order)
			{
				return car;
			}
		}

   		return cars.get(0);
	}
	
	public static void main(String[] args)
	{
 		int order = 1;
		
		String examplePath = "./track1.txt";  //Change path accordingly**
		Racetrack racetrack = new Racetrack(examplePath); //Set up track
		
		//Add cars to 'cars' list.
		addCar(new CPUCar('1'), racetrack);
		addCar(new CPUCar('2'), racetrack);
		addCar(new CPUCar('3'), racetrack);		

		racetrack.displayBanner();
		
		/** ======== START LOOP ======== **/
		
		System.out.println("START!");
		
		racetrack.print();
  
		boolean FinishLineReached = false; 
		
		while(!FinishLineReached)
		{
		
			//Reset order once it's reached the end of the list.
			if(order > cars.size()) order = 1;
			Car car = getNextCar(order);

			System.out.println("*****CAR "+car.getIdNumber()+"'s TURN!*****");
			car.move(racetrack);
			racetrack.print();
 
			//Break loop if winner is found.
			if( car.getWinner() ) {
				System.out.println("CAR "+car.getIdNumber()+" WINS!");
				racetrack.print();
				displayInfo(racetrack);

 				FinishLineReached = true;
 				break;
			}
			
			displayInfo(racetrack);
			System.out.println();
			
			car.updateCarInfo(racetrack, car.getRow(), car.getCol());
			System.out.println();
						
			order++;
		
		}
	
		/** ============================ **/

	}

 
}
