import java.util.Scanner;

public class Priority_Scheduling {

    public static void main(String args[]) {
        Scanner s = new Scanner(System.in);

        int x, n, Processes[], processPriority[], burstTime[], waitingTime[], turnaroundTime[], averageWaitingTime,
                averageTurnaroundTime, i;

        Processes = new int[10];
        processPriority = new int[10];
        burstTime = new int[10];
        waitingTime = new int[10];
        turnaroundTime = new int[10];

        System.out.print("Enter the number of process : ");
        n = s.nextInt();
        System.out.print("\n\t Enter burst time : time priorities \n");

        for (i = 0; i < n; i++) {
            System.out.print("\nProcess[" + (i + 1) + "]:");
            burstTime[i] = s.nextInt();
            processPriority[i] = s.nextInt();
            Processes[i] = i + 1;
        }

        for (i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (processPriority[i] > processPriority[j]) {
                    x = processPriority[i];
                    processPriority[i] = processPriority[j];
                    processPriority[j] = x;
                    x = burstTime[i];
                    burstTime[i] = burstTime[j];
                    burstTime[j] = x;
                    x = Processes[i];
                    Processes[i] = Processes[j];
                    Processes[j] = x;
                }
            }
        }
        waitingTime[0] = 0;
        averageWaitingTime = 0;
        turnaroundTime[0] = burstTime[0];
        averageTurnaroundTime = turnaroundTime[0];
        for (i = 1; i < n; i++) {
            waitingTime[i] = turnaroundTime[i - 1];
            averageWaitingTime += waitingTime[i];
            turnaroundTime[i] = waitingTime[i] + burstTime[i];
            averageTurnaroundTime += turnaroundTime[i];
        }

        System.out.print("\n\nProcess \t Burst Time \t Wait Time \t Turn Around Time   Priority \n");
        for (i = 0; i < n; i++)
            System.out.print("\n   " + Processes[i] + "\t\t   " + burstTime[i] + "\t\t     " + waitingTime[i]
                    + "\t\t     " + turnaroundTime[i] + "\t\t     "
                    + processPriority[i] + "\n");
        averageWaitingTime /= n;
        averageTurnaroundTime /= n;
        System.out.print("\n Average Wait Time : " + averageWaitingTime);
        System.out.print("\n Average Turn Around Time : " + averageTurnaroundTime);

    }
}