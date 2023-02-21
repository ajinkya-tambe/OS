import java.util.*;

public class SJF_SchedulingNonPre {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter no of process: ");
        int totalProcesses = sc.nextInt();
        int pid[] = new int[totalProcesses];
        int arrivalTime[] = new int[totalProcesses];
        int burstTime[] = new int[totalProcesses];
        int completionTime[] = new int[totalProcesses];
        int turnaroundTime[] = new int[totalProcesses];
        int waitingTime[] = new int[totalProcesses];
        int flag[] = new int[totalProcesses];
        int executedTime = 0, executedProcesses = 0;
        float averageWaitingTime = 0, averageTurnaroundTime = 0;

        for (int i = 0; i < totalProcesses; i++) {
            System.out.println("Enter process " + (i + 1) + " arrival time:");
            arrivalTime[i] = sc.nextInt();
            System.out.println("Enter process " + (i + 1) + " brust time:");
            burstTime[i] = sc.nextInt();
            pid[i] = i + 1;
            flag[i] = 0;
        }
        boolean a = true;
        while (true) {
            int c = totalProcesses, min = 999;
            if (executedProcesses == totalProcesses)
                break;
            for (int i = 0; i < totalProcesses; i++) {
                if ((arrivalTime[i] <= executedTime) && (flag[i] == 0) && (burstTime[i] < min)) {
                    min = burstTime[i];
                    c = i;
                }
            }
            if (c == totalProcesses)
                executedTime++;
            else {
                completionTime[c] = executedTime + burstTime[c];
                executedTime += burstTime[c];
                turnaroundTime[c] = completionTime[c] - arrivalTime[c];
                waitingTime[c] = turnaroundTime[c] - burstTime[c];
                flag[c] = 1;
                executedProcesses++;
            }
        }
        System.out.println("\npid  arrival burst  complete turn waiting");
        for (int i = 0; i < totalProcesses; i++) {
            averageWaitingTime += waitingTime[i];
            averageTurnaroundTime += turnaroundTime[i];
            System.out.println(pid[i] + "\t" + arrivalTime[i] + "\t" + burstTime[i] + "\t" + completionTime[i] + "\t" + turnaroundTime[i] + "\t" + waitingTime[i]);
        }
        System.out.println("\nAverage Turnaround Time is " + (float) (averageTurnaroundTime / totalProcesses));
        System.out.println("Average Waiting Time is " + (float) (averageWaitingTime / totalProcesses));
        sc.close();
    }
}