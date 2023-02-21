import java.util.*;

public class FirstComeFirstServe
{
	public static void main(String args[])
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("This program will perform First COme First Serve Algorithm on given inputs");
		System.out.print("\nEnter the number of process: ");
		int num = sc.nextInt();
		System.out.println();


		int pid[] = new int[num];
		int arrivalTime[] = new int[num];
		int burstTime[] = new int[num];
		int completionTime[] = new int[num];
		int turnAround[] = new int[num];
		int waitingTime[] = new int[num];	
		
		int temp;
		float avgWT = 0, avgTA = 0;

		for(int i = 0; i < num; i++)	
		{	
			System.out.print("Enter arrival time for process- " + (i+1) + " :");
			arrivalTime[i] = sc.nextInt();
			System.out.print("Enter burst time for process- " + (i+1) + " :");
			burstTime[i] = sc.nextInt();
			pid[i] = i+1;

			System.out.println();
		}

		//sorting as per arrival time
		for(int i = 0 ; i < num; i++)
		{

			for(int  j=0;  j < num-(i+1) ; j++)
			{
				if( arrivalTime[j] > arrivalTime[j+1] )
				{
					//swapping the arrival time
					temp = arrivalTime[j];
					arrivalTime[j] = arrivalTime[j+1];
					arrivalTime[j+1] = temp;
					
					//swapping the burst time
					temp = burstTime[j];
					burstTime[j] = burstTime[j+1];
					burstTime[j+1] = temp;
			
					//swapping the pid
					temp = pid[j];
					pid[j] = pid[j+1];
					pid[j+1] = temp;
				}
			}
		}

		//
		for(int  i = 0 ; i < num; i++)
		{
			if(i == 0)
				completionTime[i] = arrivalTime[i] + burstTime[i];	
			else
			{
				if(arrivalTime[i] > completionTime[i-1])
					completionTime[i] = arrivalTime[i] + burstTime[i];
				else
					completionTime[i] = completionTime[i-1] + burstTime[i];
			}
			turnAround[i] = completionTime[i] - arrivalTime[i] ;		// turnaround time= completion time- arrival time
			waitingTime[i] = turnAround[i] - burstTime[i] ;          	// waiting time= turnaround time- burst time
			avgWT += waitingTime[i] ;               						// total waiting time
			avgTA += turnAround[i] ;               						// total turnaround time
		}

		System.out.println("\nPid  Arrival  Brust  Completion TurnArd Waiting");
		for(int  i = 0 ; i< num;  i++)
		{
			System.out.println(pid[i] + "  \t " + arrivalTime[i] + "\t" + burstTime[i] + "\t" + completionTime[i] + "\t" + turnAround[i] + "\t"  + waitingTime[i] ) ;
		}
			
		System.out.println("\naverage waiting time: "+ (avgWT/num));
		System.out.println("average turnaround time:"+(avgTA/num));   
	}
}