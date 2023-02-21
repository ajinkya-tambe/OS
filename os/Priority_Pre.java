import java.util.*;

class Process {
    private int pid;
    private int arrivalTime;
    private int burstTime;
    private int priority;

    public Process(int pid, int arrivalTime, int burstTime, int priority) {
        this.pid = pid;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.priority = priority;
    }

    public int getPid() {
        return pid;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getBurstTime() {
        return burstTime;
    }

    public int getPriority() {
        return priority;
    }
}

public class Priority_Pre {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of processes: ");
        int n = sc.nextInt();

        List<Process> processes = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            System.out.println("Enter details for process " + (i+1) + ":");
            System.out.print("Arrival Time: ");
            int arrivalTime = sc.nextInt();
            System.out.print("Burst Time: ");
            int burstTime = sc.nextInt();
            System.out.print("Priority: ");
            int priority = sc.nextInt();

            processes.add(new Process(i+1, arrivalTime, burstTime, priority));
        }

        // Sort the processes by arrival time
        processes.sort(Comparator.comparing(Process::getArrivalTime));

        // Priority queue to store processes in priority order
        PriorityQueue<Process> pq = new PriorityQueue<>(Comparator.comparing(Process::getPriority).reversed());

        int currentTime = 0;
        int totalWaitingTime = 0;
        int totalTurnaroundTime = 0;
        int completed = 0;

        while (completed < n) {
            // Add new processes to the queue if they have arrived
            while (!processes.isEmpty() && processes.get(0).getArrivalTime() <= currentTime) {
                pq.add(processes.remove(0));
            }

            if (pq.isEmpty()) {
                currentTime = processes.get(0).getArrivalTime();
                continue;
            }

            // Get the process with highest priority
            Process currentProcess = pq.poll();

            int remainingTime = currentProcess.getBurstTime() - 1;

            // Add waiting time for all other processes in the queue
            for (Process process : pq) {
                totalWaitingTime++;
            }

            // Add waiting time for the current process
            totalWaitingTime += currentTime - currentProcess.getArrivalTime();

            // Update turnaround time and completed process count
            if (remainingTime == 0) {
                completed++;
                totalTurnaroundTime += currentTime + 1 - currentProcess.getArrivalTime();
            }

            // Add remaining process to queue
            else {
                currentProcess = new Process(currentProcess.getPid(), currentProcess.getArrivalTime(), remainingTime, currentProcess.getPriority());
                pq.add(currentProcess);
            }

            currentTime++;
        }

        double avgWaitingTime = (double) totalWaitingTime / n;
        double avgTurnaroundTime = (double) totalTurnaroundTime / n;

        System.out.println("Average Waiting Time: " + avgWaitingTime);
        System.out.println("Average Turnaround Time: " + avgTurnaroundTime);
    }
}
