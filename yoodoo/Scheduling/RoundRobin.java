//  RR (Round Robin) is a preemptive CPU scheduling algorithm where each process is assigned a time slot known as time quantum in cyclic way, FCFS (First Come First Serve) CPU scheduling algorithm is nothing but its non-preemptive form.

import java.util.Scanner;

public class RoundRobin {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter no of process: \n");
        int n = sc.nextInt();

        int[] burst_time = new int[n];
        int[] waiting_time = new int[n];
        int[] turnaround_time = new int[n];
        int[] finish_time = new int[n];
        int quantum;

        System.out.println();
        for (int i = 0; i < n; i++) {
            System.out.println("Enter process " + (i + 1) + " Burst time:");
            burst_time[i] = sc.nextInt();
        }

        System.out.print("\n\nEnter the time quantum: ");
        quantum = sc.nextInt();

        int current_time = 0;
        int remaining_time[] = new int[n];

        for (int i = 0; i < n; i++) {
            remaining_time[i] = burst_time[i];
        }

        while (true) {
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

            finish_time[i] = turnaround_time[i] + waiting_time[i];
        }

        System.out.println("pid \t burst \t waiting turn \t finish ");

        for (int i = 0; i < n; i++)

        {
            System.out.println("P" + (i + 1) + " \t " + burst_time[i] + " \t " + waiting_time[i] + " \t "
                    + turnaround_time[i] + " \t " + finish_time[i]);
        }

        sc.close();
    }

}
