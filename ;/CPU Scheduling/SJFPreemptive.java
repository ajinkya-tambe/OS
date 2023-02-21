import java.util.*;
 
public class SJFPreemptive 
{
	public static void main (String args[])
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("This program will perform SJF (Preemptive) Algorithm on given inputs");
		System.out.print("Enter no of process:");
		int n= sc.nextInt();	
		int pid[] = new int[n]; // it takes pid of process
		int at[] = new int[n]; // at means arrival time
		int bt[] = new int[n]; // bt means burst time
		int ct[] = new int[n]; // ct means complete time
		int ta[] = new int[n];// ta means turn around time
		int wt[] = new int[n];  // wt means waiting time		
		int f[] = new int[n];  // f means it is flag it checks process is completed or not
		int k[]= new int[n];   // it is also stores brust time
		int i, st=0, tot=0;
	    	float avgwt=0, avgta=0;
 
		for(i=0;i<n;i++)		
		{
			System.out.print("\nEnter process " + (i+1) + " arrival time:");
			at[i] = sc.nextInt();
			System.out.print("Enter process " + (i+1) + " brust time:");
			bt[i] = sc.nextInt();
			pid[i] = i+1;
			k[i]= bt[i];
			f[i] = 0;
		}
    
    		while(true)
		{
		     int min=99,c=n;
		     if (tot==n)
     			break;
   
		     for ( i=0;i<n;i++)
		     {
			     if ((at[i]<=st) && (f[i]==0) && (bt[i]<min))
			     {
				     min=bt[i];
				     c=i;
			     }
		     }
    
		     if (c==n)
			     st++;
		     else
		     {		
			     bt[c]--;
			     st++;
			     if (bt[c]==0)
			     {
				     ct[c]= st;
				     f[c]=1;
				     tot++;
			     }
		     }
	    }
    
	    for(i=0;i<n;i++)	
	    {
		     ta[i] = ct[i] - at[i];
		     wt[i] = ta[i] - k[i];
		     avgwt+= wt[i];
		     avgta+= ta[i];
	    }
    
	    System.out.println("Pid  Arrival  Burst  Finish TurnAT Waiting");	
	    for(i=0;i<n;i++)
	    {		
		     System.out.println(pid[i] +"\t"+ at[i]+"\t"+ k[i] +"\t"+ ct[i] +"\t"+ ta[i] +"\t"+ wt[i]);
	    }
    
	    System.out.println("\nAverage tat is "+ (float)(avgta/n));		
	    System.out.println("Average wt is "+ (float)(avgwt/n));
	}
}