import java.util.*;

//----------QUICKSORT----------//

public class Quicksort {

    public static long ans = 0;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        //int n =in.nextInt();
        int[] a = new int[10000];
        for (int i = 0 ; i < a.length ; i++)
            a[i] = in.nextInt();
        System.out.print(Med(a, 0, 1, 2));
        quickSort(a);

        System.out.println(Arrays.toString(a));
        System.out.println(ans);
    }



    public static void quickSort(int[] a) {
        Sort(a, 0, a.length-1);
    }

    public static void Sort(int[] a, int l,int h) {
        if (l < h) {
            int i = l+1;
            ans += h - l;
            for (int j = l+1; j <= h ; j++) {
                if (a[j] < a[l]) {
                    int t = a[j];
                    a[j] = a[i];
                    a[i] = t;
                    i++;
                }
            }
            int t = a[l];
            a[l] = a[i-1];
            a[i-1] = t;
            Sort(a, l, i-2);
            Sort(a, i, h);
        }
    }

    public static int Med(int[] a, int l, int m, int h) {
        if (a[l] < a[m] && a[l] < a[h]) {
            if (a[m] < a[h])
                return m;
            else
                return h;
        }

        else if (a[m] < a[l] && a[m] < a[h]) {
            if (a[l] < a[h])
                return l;
            else
                return h;
        }

        else {
            if (a[l] < a[m])
                return l;
            else
                return m;
        }
    }




}

