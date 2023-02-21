import java.util.Scanner;

public class SJF_NonPreemptive {

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter no of process:");
        int n = sc.nextInt();

        int process_id[] = new int[n];
        int arrival_time[] = new int[n];
        int burst_time[] = new int[n];
        int finish_time[] = new int[n];
        int turnaround_time[] = new int[n];
        int wait_time[] = new int[n];
        int checkCompletion[] = new int[n];

        int system_time = 0, completedProcess = 0;
        float average_wait_time = 0, average_turnaround_time = 0;

        // assigning process_id and accepting inputs
        for (int i = 0; i < n; i++) {
            System.out.println("\nEnter process " + (i + 1) + " Arrival time:");
            arrival_time[i] = sc.nextInt();
            System.out.println("Enter process " + (i + 1) + " Brust time:");
            burst_time[i] = sc.nextInt();

            process_id[i] = i + 1;
            checkCompletion[i] = 0;
        }

        // boolean a = true;
        while (true) {
            int c = n, min = 999;
            // if total no of process = completed process, loop will be terminated
            if (completedProcess == n)
                break;

            for (int i = 0; i < n; i++) {
                if ((arrival_time[i] <= system_time) && (checkCompletion[i] == 0) && (burst_time[i] < min)) {
                    min = burst_time[i];
                    c = i;
                }
            }

            if (c == n)
                system_time++;
            else {
                finish_time[c] = system_time + burst_time[c];
                system_time += burst_time[c];
                turnaround_time[c] = finish_time[c] - arrival_time[c];
                wait_time[c] = turnaround_time[c] - burst_time[c];
                checkCompletion[c] = 1;
                completedProcess++;
            }
        }

        System.out.println("\npid  arrival brust  finish  turn  waiting");
        for (int i = 0; i < n; i++) {
            average_wait_time += wait_time[i];
            average_turnaround_time += turnaround_time[i];
            System.out.println(process_id[i] + "\t" + arrival_time[i] + "\t" + burst_time[i] + "\t" + finish_time[i]
                    + "\t" + turnaround_time[i] + "\t" + wait_time[i]);
        }

        System.out.println("\nAverage turnnaround time is " + (float) (average_turnaround_time / n));
        System.out.println("Average waiting time is " + (float) (average_wait_time / n));
        sc.close();
    }
}
