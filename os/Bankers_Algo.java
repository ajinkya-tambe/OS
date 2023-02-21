import java.util.Scanner;

public class Bankers_Algo {
    static int[][] need, allocated, max;
    static int[] work, available, safe_seq;
    static int processes, resources;
    static boolean[] finish;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of processes: ");
        processes = sc.nextInt();
        System.out.println("Enter total number of resources: ");
        resources = sc.nextInt();

        need = new int[processes][resources];
        allocated = new int[processes][resources];
        max = new int[processes][resources];
        finish = new boolean[processes];
        safe_seq = new int[processes];
        work = new int[processes];
        available = new int[processes];

        System.out.println("Enter allocation matrix: ");
        for (int i = 0; i < processes; i++) {
            for (int j = 0; j < resources; j++)
                allocated[i][j] = sc.nextInt();
        }

        System.out.println("Enter max matrix: ");
        for (int i = 0; i < processes; i++) {
            for (int j = 0; j < resources; j++)
                max[i][j] = sc.nextInt();
        }

        System.out.println("Enter available matrix: ");
        for (int i = 0; i < resources; i++) {
            available[i] = sc.nextInt();
        }

        for (int i = 0; i < resources; i++) {
            work[i] = available[i];
        }

        for (int i = 0; i < processes; i++) {
            for (int j = 0; j < resources; j++)
                need[i][j] = max[i][j] - allocated[i][j];
        }

        int cnt = 0;
        boolean flag = true;

        while (flag) {
            flag = false;
            for (int i = 0; i < processes; i++) {
                if (finish[i] == false) {
                    int j;
                    for (j = 0; j < resources; j++) {
                        if (need[i][j] > work[j]) {
                            break;
                        }
                    }

                    if (j == resources) {
                        for (int z = 0; z < resources; z++) {
                            work[z] += allocated[i][z];
                        }
                        finish[i] = true;
                        safe_seq[cnt] = i;
                        cnt++;
                        flag = true;
                    }
                }
            }
        }

        boolean safe = true;
        for (int i = 0; i < processes; i++) {
            if (finish[i] == false) {
                safe = false;
            }
        }

        if (safe) {
            System.out.println("The system is in a safe state!");
            System.out.println("Safe Sequence is: ");
            for (int i = 0; i < processes; i++) {
                System.out.print("P" + safe_seq[i]);
                if (i != processes - 1) {
                    System.out.print(" -> ");
                }
            }
        } else {
            System.out.println("The system is in an unsafe state!");
        }
    }
}