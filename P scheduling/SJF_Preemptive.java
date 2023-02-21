import java.util.Scanner;

public class SJF_Preemptive {
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
        int burst_time2[] = new int[n];
        int system_time = 0, completedProcess = 0;
        float average_wait_time = 0, average_turnaround_time = 0;

        for (int i = 0; i < n; i++) {
            process_id[i] = i + 1;
            System.out.println("\nEnter process " + (i + 1) + " Arrival time:");
            arrival_time[i] = sc.nextInt();
            System.out.println("Enter process " + (i + 1) + " Burst time:");
            burst_time[i] = sc.nextInt();
            burst_time2[i] = burst_time[i];
            checkCompletion[i] = 0;
        }

        while (true) {
            int min = 99, c = n;
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
                burst_time[c]--;
                system_time++;
                if (burst_time[c] == 0) {
                    finish_time[c] = system_time;
                    checkCompletion[c] = 1;
                    completedProcess++;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            turnaround_time[i] = finish_time[i] - arrival_time[i];
            wait_time[i] = turnaround_time[i] - burst_time2[i];
            average_wait_time += wait_time[i];
            average_turnaround_time += turnaround_time[i];
        }

        System.out.println("pid  arrival  burst  finish  turn  waiting");
        for (int i = 0; i < n; i++) {
            System.out.println(process_id[i] + "\t" + arrival_time[i] + "\t" + burst_time2[i] + "\t" + finish_time[i]
                    + "\t" + turnaround_time[i] + "\t" + wait_time[i]);
        }

        System.out.println("\nAverage turnaround time is " + (float) (average_turnaround_time / n));
        System.out.println("Average wait time is " + (float) (average_wait_time / n));
        sc.close();
    }
}
