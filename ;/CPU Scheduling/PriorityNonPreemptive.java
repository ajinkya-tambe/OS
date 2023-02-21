import java.util.Scanner;

public class PriorityNonPreemptive {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n, priority[], burstTime[], process[];

        System.out.print("Enter the number of processes: ");
        n = input.nextInt();

        priority = new int[n];
        burstTime = new int[n];
        process = new int[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Enter priority, burst time, and process number for process " + (i + 1) + ": ");
            priority[i] = input.nextInt();
            burstTime[i] = input.nextInt();
            process[i] = input.nextInt();
        }

        // Sorting the processes by priority
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (priority[i] > priority[j]) {
                    int temp = priority[i];
                    priority[i] = priority[j];
                    priority[j] = temp;

                    temp = burstTime[i];
                    burstTime[i] = burstTime[j];
                    burstTime[j] = temp;

                    temp = process[i];
                    process[i] = process[j];
                    process[j] = temp;
                }
            }
        }

        int completionTime[] = new int[n];
        completionTime[0] = burstTime[0];
        for (int i = 1; i < n; i++) {
            completionTime[i] = completionTime[i - 1] + burstTime[i];
        }

        System.out.println("Process \t Priority \t Burst Time \t Completion Time");
        for (int i = 0; i < n; i++) {
            System.out.println(process[i] + "\t\t" + priority[i] + "\t\t" + burstTime[i] + "\t\t" + completionTime[i]);
        }
    }
}