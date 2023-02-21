import java.util.Scanner;

class FCFS_Scheduling {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n;
        System.out.println("Enter number of processes: ");
        n = sc.nextInt();
        int p[] = new int[n];
        int ArrivalTime[] = new int[n];
        int BurstTime[] = new int[n];
        int CompletionTime[] = new int[n];
        int WaitingTime[] = new int[n];

        int TurnaroundTime[] = new int[n];
        int temp;
        float avgWaitingTime = 0, avgtt = 0;

        for (int i = 0; i < n; i++) {
            System.out.println("Enter arrival time for process " + (i + 1) + ": ");
            ArrivalTime[i] = sc.nextInt();
            System.out.println("Enter burst time for process " + (i + 1) + ": ");
            BurstTime[i] = sc.nextInt();
            p[i] = i + 1;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - (i + 1); j++) {
                if (ArrivalTime[j] > ArrivalTime[j + 1]) {
                    temp = ArrivalTime[j];
                    ArrivalTime[j] = ArrivalTime[j + 1];
                    ArrivalTime[j + 1] = temp;

                    temp = BurstTime[j];
                    BurstTime[j] = BurstTime[j + 1];
                    BurstTime[j + 1] = temp;

                    temp = p[j];
                    p[j] = p[j + 1];
                    p[j + 1] = temp;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            if (i == 0) {
                CompletionTime[i] = ArrivalTime[i] + BurstTime[i];
            } else {
                if (ArrivalTime[i] > CompletionTime[i - 1]) {
                    CompletionTime[i] = ArrivalTime[i] + BurstTime[i];
                } else {
                    CompletionTime[i] = CompletionTime[i - 1] + BurstTime[i];
                }
            }
            TurnaroundTime[i] = CompletionTime[i] - ArrivalTime[i];
            WaitingTime[i] = TurnaroundTime[i] - BurstTime[i];
            avgWaitingTime += (float) WaitingTime[i];
            avgtt += (float) TurnaroundTime[i];
        }
        System.out.println("\npid  arrivalT  burstT  completeT turnT waitingT");
        for (int i = 0; i < n; i++) {
            System.out.println(p[i] + "  \t " + ArrivalTime[i] + "\t" + BurstTime[i] + "\t" + CompletionTime[i] + "\t"
                    + TurnaroundTime[i] + "\t" + WaitingTime[i]);
        }
        sc.close();
        System.out.println("\naverage waiting time: " + (avgWaitingTime / n));
        System.out.println("average turnaround time:" + (avgtt / n));
    }
}
