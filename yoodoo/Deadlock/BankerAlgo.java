import java.util.*;

public class BankerAlgo
{
	public static void main(String args[])
	{	
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter number of processes: "); //5
		int P = sc.nextInt();

		System.out.print("Enter number of resources: "); //3
		int R = sc.nextInt();
	
		// int processes[] = {0 ,1, 2, 3, 4};
		int processes[] = new int[P];  
        for(int i = 0; i < P; i++)
		{  
        	processes[i] = i;  
        } 
			
		// int totalR[] = {10, 5, 7};
		int totalR[] = new int[R];
		System.out.println();
		for(int i = 0; i < R; i++)
		{
			System.out.print("Enter the total count of resource- " + (i + 1)+ " : ");
			totalR[i] = sc.nextInt();
		}

		int totalAllocated[] = new int[R];
		boolean []finish = new boolean[P]; //marking all the processes as false first
		int []safeSeq = new int[P];

		// int max[][] = {{7, 5, 3},
        //             	{3, 2, 2},
        //             	{9, 0, 2},
        //             	{2, 2, 2},
        //             	{4, 3, 3}};

		System.out.println("\n----------------------------------------------MAX MATRIX ----------------------------------------------");
		int max[][] = new int[P][R];
		for( int i = 0; i < P; i++)
		{  
            for( int j = 0; j < R; j++)
			{  
                System.out.print("Enter the maximum resource- "+ (j + 1) +" that can be allocated to process- "+ i +": ");  
                max[i][j] = sc.nextInt();  
            }  
        } 
		
		// int allocated[][] = {{0, 1, 0},
        //             	{2, 0, 0},
        //             	{3, 0, 2},
        //             	{2, 1, 1},
        //             	{0, 0, 2}};

		System.out.println("\n----------------------------------------------ALLOCATION MATRIX ----------------------------------------------");
		int allocated[][] = new int[P][R];
		for( int i = 0; i < P; i++)
		{  
            for( int j = 0; j < R; j++)
			{  
                System.out.print("Enter number of instances of resource- "+ (j + 1) +" are allocated to process- "+ i +": ");  
                allocated[i][j] = sc.nextInt();  
            }  
        }  
		
		int need[][] = new int[P][R];
		
		System.out.println("\nInputs taken from user: ");
		System.out.println("Given processes: " +Arrays.toString(processes));	
		System.out.println("Total resources: " +Arrays.toString(totalR));
		System.out.println("Max matrix: " +Arrays.deepToString(max));
		System.out.println("Allocated matrix: " +Arrays.deepToString(allocated));

		System.out.println("\nCalculated outputs: ");

		//calculating need matrix
		for(int i = 0; i < P; i++)
		{
			for(int j = 0; j < R; j++)
			{
				need[i][j] = max[i][j] - allocated[i][j];
			}
		}

		System.out.println("*Need matrix: " +Arrays.deepToString(need));
	
		//calculating the totalAllocated matric
		for(int i = 0; i < P; i++)
		{
			for(int j = 0; j < R; j++)
			{
				totalAllocated[j] += allocated[i][j];
			}
		}
					
		// System.out.println("*Total available matrix: " +Arrays.toString(totalAllocated));

		int avail[] = new int[R];
		//calculating available matrix
		for(int i = 0; i < R; i++)
		{
			avail[i] = totalR[i] - totalAllocated[i];
		}
		
		System.out.println("*Available matrix: " +Arrays.toString(avail));

		int work[] = new int[R];
		work = avail;

		int count = 0;
   		while (count < P)
    		{
        		//Find a process which is not finish and whose needs can be satisfied with current work[] resources.
				boolean found = false;
        		for (int p = 0; p < P; p++)
        		{
            		// First check if a process is finished, if no, go for next condition
            		if (finish[p] == false)
            		{
                		// Check if for all resources of current P need is less than work
                		int j;
                		for (j = 0; j < R; j++)
                    	if (need[p][j] > work[j])
                        		break;
 
                		// If all needs of p were satisfied.
                		if (j == R)
                		{
                    		// Add the allocated resources of current P to the available work resources i.e.free the resources
                    		for (int k = 0 ; k < R ; k++)
                        			work[k] += allocated[p][k];
 
                    		// Add this process to safe sequence.
                    		safeSeq[count++] = p;
 
                    		// Mark this p as finished
                    		finish[p] = true;
 
                    		found = true;
                		}
            		}
        		}
				
        		// If we could not find a next process in safe sequence.
        		if (found == false)
        		{
            		System.out.print("\nSystem is not in safe state");	
        		}
    		}
		System.out.println("*Work matrix: " +Arrays.toString(work));
		System.out.print("\nSystem is in safe state.\nSafe sequence is: ");
    		for (int i = 0; i < P ; i++)
        		System.out.print(safeSeq[i] + " ");
	}
}






