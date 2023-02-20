import java.util.*;
public class Optimal {
    static boolean search(int key, int[] fr)
    {
        for (int i = 0; i < fr.length; i++)
            if (fr[i] == key)
                return true;
        return false;
    }
    static int predict(int pg[], int[] fr, int pn, int index)
    {
        int res = -1, farthest = index;
        for (int i = 0; i < fr.length; i++) {
            int j;
            for (j = index; j < pn; j++) {
                if (fr[i] == pg[j]) {
                    if (j > farthest) {
                        farthest = j;
                        res = i;
                    }
                    break;
                }
            }
            if (j == pn)
                return i;
        }
        return (res == -1) ? 0 : res;
    }

    static void optimalPage(int pg[], int pn, int fn)
    {
        int[] fr = new int[fn];
        int hit = 0;
        int index = 0;
        for (int i = 0; i < pn; i++) {
            if (search(pg[i], fr)) {
                hit++;
                continue;
            }
            if (index < fn)
                fr[index++] = pg[i];
            else {
                int j = predict(pg, fr, pn, i + 1);
                fr[j] = pg[i];
            }
        }
        System.out.println("Total Page Fault : " + (pn - hit));
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        //7, 0, 1, 2, 0, 3, 0, 4, 2, 3, 0, 3, 2

        System.out.println("Enter the number of pages: ");
        int pgNo = sc.nextInt();

        int pg[] = new int[pgNo];

        System.out.println("Enter your page stream: ");
        for(int i = 0; i < pgNo; i++)
        {
            pg[i] = sc.nextInt();
        }

        System.out.println("Enter the number of frames: ");
        int fn = sc.nextInt();

        System.out.print("For Page Sequence : ");
        for(int i=0; i<pg.length; i++) {
            System.out.print(pg[i] + " ");
        }
        
        System.out.println();
        int pn = pg.length;
        optimalPage(pg, pn, fn);
    }
}
