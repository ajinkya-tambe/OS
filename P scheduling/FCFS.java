import java.text.ParseException;
import java.util.Scanner;

class FCFS {
    public static void main(String[] args) throws ParseException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of processes : ");
        int n = sc.nextInt();

        // input and output arrays
        int process_id[] = new int[n];
        int arrival_time[] = new int[n];
        int burst_time[] = new int[n];
        int finish_time[] = new int[n];
        int turnaround_time[] = new int[n];
        int wait_time[] = new int[n];
        int average_wait_time = 0, average_turnaround_time = 0;

        // accepting inputs and pid
        for (int i = 0; i < n; i++) {
            System.out.println("\nEnter process " + (i + 1) + " Arrival time: ");
            arrival_time[i] = sc.nextInt();
            System.out.println("Enter process " + (i + 1) + " Brust time: ");
            burst_time[i] = sc.nextInt();
            process_id[i] = i + 1;
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

                    // swap process ids
                    temp = process_id[j];
                    process_id[j] = process_id[j + 1];
                    process_id[j + 1] = temp;
                }
            }
        }

        // calculate finish time
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                finish_time[i] = arrival_time[i] + burst_time[i];
            } else {
                if (arrival_time[i] > finish_time[i - 1]) {
                    finish_time[i] = arrival_time[i] + burst_time[i];
                } else
                    finish_time[i] = finish_time[i - 1] + burst_time[i];
            }

            // calculating total turnaround and wait time
            turnaround_time[i] = finish_time[i] - arrival_time[i];
            wait_time[i] = turnaround_time[i] - burst_time[i];
            average_wait_time += wait_time[i];
            average_turnaround_time += turnaround_time[i];
        }

        System.out.println("\npid  arrival  brust  finish  turn  waiting");
        for (int i = 0; i < n; i++) {
            System.out.println(process_id[i] + "  \t " + arrival_time[i] + "\t" + burst_time[i] + "\t" + finish_time[i]
                    + "\t" + turnaround_time[i] + "\t" + wait_time[i]);
        }

        sc.close();

        System.out.println("\nAverage waiting time: " + (average_wait_time / n));
        System.out.println("Average turnaround time:" + (average_turnaround_time / n));
    }
}
