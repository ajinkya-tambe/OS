import java.util.Scanner;

public class RoundRobin_Scheduling {
    public static void main(String args[]) {
        int n, i, qt, count = 0, temp, sq = 0, burstTime[], waitingTime[], turnaroundTime[], remainingBurstTime[];
        float averageWaitingTime = 0, averageTurnaroundTime = 0;
        burstTime = new int[10];
        waitingTime = new int[10];
        turnaroundTime = new int[10];
        remainingBurstTime = new int[10];
        Scanner s = new Scanner(System.in);
        System.out.print("Enter the number of process (maximum 10) = ");
        n = s.nextInt();
        System.out.print("Enter the burst time of the process\n");
        for (i = 0; i < n; i++) {
            System.out.print("P" + i + " = ");
            burstTime[i] = s.nextInt();
            remainingBurstTime[i] = burstTime[i];
        }
        System.out.print("Enter the quantum time: ");
        qt = s.nextInt();
        while (true) {
            for (i = 0, count = 0; i < n; i++) {
                temp = qt;
                if (remainingBurstTime[i] == 0) {
                    count++;
                    continue;
                }
                if (remainingBurstTime[i] > qt)
                    remainingBurstTime[i] = remainingBurstTime[i] - qt;
                else if (remainingBurstTime[i] >= 0) {
                    temp = remainingBurstTime[i];
                    remainingBurstTime[i] = 0;
                }
                sq = sq + temp;
                turnaroundTime[i] = sq;
            }
            if (n == count)
                break;
        }
        System.out.print("--------------------------------------------------------------------------------");
        System.out.print("\nProcess\t      Burst Time\t       Turnaround Time\t          Waiting Time\n");
        System.out.print("--------------------------------------------------------------------------------");
        for (i = 0; i < n; i++) {
            waitingTime[i] = turnaroundTime[i] - burstTime[i];
            averageWaitingTime = averageWaitingTime + waitingTime[i];
            averageTurnaroundTime = averageTurnaroundTime + turnaroundTime[i];
            System.out.print("\n " + (i + 1) + "\t\t " + burstTime[i] + "\t\t\t " + turnaroundTime[i] + "\t\t\t\t " + waitingTime[i] + "\n");
        }
        averageWaitingTime = averageWaitingTime / n;
        averageTurnaroundTime = averageTurnaroundTime / n;
        System.out.println("\nAverage waiting Time = " + averageWaitingTime + "\n");
        System.out.println("Average turnaround time = " + averageTurnaroundTime);
    }
}