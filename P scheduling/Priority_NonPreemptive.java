import java.util.Scanner;

public class Priority_NonPreemptive {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of processes : ");
        int n = sc.nextInt();

        // input and output arrays
        int process_id[] = new int[n];
        int arrival_time[] = new int[n];
        int burst_time[] = new int[n];
        int priority[] = new int[n];
        int finish_time[] = new int[n];
        int turnaround_time[] = new int[n];
        int wait_time[] = new int[n];
        int checkCompletion[] = new int[n];

        int system_time = 0, completedProcess = 0;
        int average_wait_time = 0, average_turnaround_time = 0;

        // accepting inputs and pid
        for (int i = 0; i < n; i++) {
            System.out.println("\nEnter process " + (i + 1) + " Arrival time: ");
            arrival_time[i] = sc.nextInt();
            System.out.println("Enter process " + (i + 1) + " Brust time: ");
            burst_time[i] = sc.nextInt();
            System.out.println("Enter process " + (i + 1) + " Priority: ");
            priority[i] = sc.nextInt();
            process_id[i] = i + 1;
            checkCompletion[i] = 0;
        }

        // scheduling processes according to arrival time
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - (i + 1); j++) {
                if (arrival_time[j] > arrival_time[j + 1]) {
                    // swap arrival time
                    int temp = arrival_time[j];
                    arrival_time[j] = arrival_time[j + 1];
                    arrival_time[j + 1] = temp;

                    // swap burst time
                    temp = burst_time[j];
                    burst_time[j] = burst_time[j + 1];
                    burst_time[j + 1] = temp;

                    // swap priority
                    temp = priority[j];
                    priority[j] = priority[j + 1];
                    priority[j + 1] = temp;

                    // swap process ids
                    temp = process_id[j];
                    process_id[j] = process_id[j + 1];
                    process_id[j + 1] = temp;
                }
            }
        }

        while (true) {
            int c = n, min = 999;
            // if total no of process = completed process, loop will be terminated
            if (completedProcess == n)
                break;

            for (int i = 0; i < n; i++) {
                if ((arrival_time[i] <= system_time) && (checkCompletion[i] == 0) && (priority[i] < min)) {
                    min = priority[i];
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

        System.out.println("\npid  arrival  brust  priority finish  turn  waiting");
        for (int i = 0; i < n; i++) {
            average_wait_time += wait_time[i];
            average_turnaround_time += turnaround_time[i];
            System.out.println(process_id[i] + "  \t " + arrival_time[i] + "\t" + burst_time[i] + "\t" + priority[i]
                    + "\t" + finish_time[i]
                    + "\t" + turnaround_time[i] + "\t" + wait_time[i]);
        }

        sc.close();

        System.out.println("\nAverage waiting time: " + (average_wait_time / n));
        System.out.println("Average turnaround time:" + (average_turnaround_time / n));
    }
}
