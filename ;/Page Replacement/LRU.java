import java.util.*;

public class LRU {
    static int pageFaults(int pages[], int n, int frames)
    {
        HashSet<Integer> s = new HashSet<>(frames);
        HashMap<Integer, Integer> indexes = new HashMap<>();
        int page_faults = 0;
        for (int i=0; i<n; i++)
        {
            if (s.size() < frames)
            {
                if (!s.contains(pages[i]))
                {
                    s.add(pages[i]);
                    page_faults++;
                }
                indexes.put(pages[i], i);
            }
            else
            {
                if (!s.contains(pages[i]))
                {
                    int lru = Integer.MAX_VALUE, val=Integer.MIN_VALUE;
                    Iterator<Integer> itr = s.iterator();
                    while (itr.hasNext()) {
                        int temp = itr.next();
                        if (indexes.get(temp) < lru)
                        {
                            lru = indexes.get(temp);
                            val = temp;
                        }
                    }
                    s.remove(val);
                    indexes.remove(val);
                    s.add(pages[i]);
                    page_faults++;
                }
                indexes.put(pages[i], i);
            }
        }
        return page_faults;
    }
    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of pages: ");
        int pgNo = sc.nextInt();

        //{7 0 1 2 0 3 0 4 2 3 0 3 10}
        System.out.println("Enter your page stream: ");
        int incomingStream[] = new int[pgNo];
        for(int i = 0; i < pgNo; i++)
        {
            incomingStream[i] = sc.nextInt();
        }

        System.out.println("Enter the number of frames: ");
        int frames = sc.nextInt();

        System.out.print("For Page Sequence : ");
        for(int i=0; i<incomingStream.length; i++) {
            System.out.print(incomingStream[i] + " ");
        }
        System.out.println("\n\nTotal Page Faults Is : "+pageFaults(incomingStream, incomingStream.length, frames));
    }
}
