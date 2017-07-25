import java.io.File;
import java.util.*;

//------------MEDIAN MAINTENANCE----------//

public class MedianMaintenance {

    static int size = 0;

    static PriorityQueue<Integer> pqlow = new PriorityQueue<>(5000, Collections.reverseOrder());
    static PriorityQueue<Integer> pqhigh = new PriorityQueue<>(5000);

    public static void main(String[] args) {
        Scanner in;
        try {
            in = new Scanner(new File("median.txt"));

        } catch (Exception e) {
            in = new Scanner(System.in);
        }

        int sum = 0;
        pqlow.add(in.nextInt());
        size++;
        sum += pqlow.peek();

        while (in.hasNext()) {
            int x = pqlow.peek();
            int y = in.nextInt();

            if (y <= x)
                pqlow.add(y);
            else
                pqhigh.add(y);
            size++;
            System.out.println("size " + size);
            resize();
            System.out.println("median " + median());
            sum += median();
        }

        System.out.println(sum%10000);

    }

    public static int median() {
        int half;
        if (size % 2 == 0)
            half = size/2;
        else
            half = (size+1)/2;

        if (pqlow.size() == half)
            return pqlow.peek();
        else
            return pqhigh.peek();

    }

    public static void resize() {
        if (Math.abs(pqlow.size()-pqhigh.size()) > 1)
            if (pqlow.size() > pqhigh.size())
                while(pqlow.size()-pqhigh.size() > 1)
                    pqhigh.add(pqlow.poll());

            else
                while(pqhigh.size()-pqlow.size() > 1)
                    pqlow.add(pqhigh.poll());



    }

}