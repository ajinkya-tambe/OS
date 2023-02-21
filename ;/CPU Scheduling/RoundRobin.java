import java.util.Scanner;

public class RoundRobin {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of processes: ");

        int n = sc.nextInt();

        int[] burst_time = new int[n];
        int[] waiting_time = new int[n];
        int[] turnaround_time = new int[n];
        int[] completion_time = new int[n];
        int quantum;

        System.out.print("Enter the burst time for each process: ");

        for (int i = 0; i < n; i++)

        {
            burst_time[i] = sc.nextInt();
        }

        System.out.print("Enter the time quantum: ");
        quantum = sc.nextInt();

        int current_time = 0;
        int remaining_time[] = new int[n];

        for (int i = 0; i < n; i++)

        {
            remaining_time[i] = burst_time[i];
        }

        while (true)

        {
            boolean done = true;

            for (int i = 0; i < n; i++)

            {
                if (remaining_time[i] > 0)

                {
                    done = false;

                    if (remaining_time[i] > quantum)

                    {
                        current_time += quantum;
                        remaining_time[i] -= quantum;
                    }

                    else

                    {
                        current_time = current_time + remaining_time[i];

                        waiting_time[i] = current_time - burst_time[i];

                        remaining_time[i] = 0;
                    }
                }
            }

            if (done == true)

            {
                break;
            }
        }

        for (int i = 0; i < n; i++)

        {
            turnaround_time[i] = burst_time[i] + waiting_time[i];

            completion_time[i] = turnaround_time[i] + waiting_time[i];
        }

        System.out.println("Process\t Burst Time\t Waiting Time\t Turnaround Time\t Completion Time");

        for (int i = 0; i < n; i++)

        {
            System.out.println("P[" + (i + 1) + "]" + "\t\t" + burst_time[i] + "\t\t" + waiting_time[i] + "\t\t"
                    + turnaround_time[i] + "\t\t" + completion_time[i]);
        }

    }

}