import java.util.Scanner;

public class PriorityPreemptive {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of processes: ");
        int n = sc.nextInt();
        int pid[] = new int[n];
        int at[] = new int[n];
        int bt[] = new int[n];
        int pr[] = new int[n];
        int ct[] = new int[n];
        int ta[] = new int[n];
        int wt[] = new int[n];
        int temp;
        float avgwt = 0, avgta = 0;

        for (int i = 0; i < n; i++) {
            System.out.print("\nEnter process ID: ");
            pid[i] = sc.nextInt();
            System.out.print("Enter arrival time: ");
            at[i] = sc.nextInt();
            System.out.print("Enter burst time: ");
            bt[i] = sc.nextInt();
            System.out.print("Enter priority: ");
            pr[i] = sc.nextInt();
        }

        // Sorting processes by priority
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (pr[i] > pr[j]) {
                    temp = pr[i];
                    pr[i] = pr[j];
                    pr[j] = temp;
                    temp = pid[i];
                    pid[i] = pid[j];
                    pid[j] = temp;
                    temp = at[i];
                    at[i] = at[j];
                    at[j] = temp;
                    temp = bt[i];
                    bt[i] = bt[j];
                    bt[j] = temp;
                }
            }
        }

        // Calculating completion time, turnaround time, and waiting time
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                ct[i] = at[i] + bt[i];
            } else {
                if (ct[i - 1] < at[i]) {
                    ct[i] = at[i] + bt[i];
                } else {
                    ct[i] = ct[i - 1] + bt[i];
                }
            }
            ta[i] = ct[i] - at[i];
            wt[i] = ta[i] - bt[i];
            avgwt += wt[i];
            avgta += ta[i];
        }

        // Displaying results
        System.out.println(
                "\nProcess ID\tArrival Time\tBurst Time\tPriority\tCompletion Time\tTurnaround Time\tWaiting Time");
        for (int i = 0; i < n; i++) {
            System.out.println(pid[i] + "\t\t"
                    + at[i] + "\t\t" + bt[i] + "\t\t" + pr[i] + "\t\t" + ct[i] + "\t\t" + ta[i] + "\t\t\t" + wt[i]);
        }
        System.out.println("\nAverage Waiting Time: " + (avgwt / n));
        System.out.println("Average Turnaround Time: " + (avgta / n));
    }
}