import java.util.*;

//----------MINCUT----------//

public class mincut {

    static int k = 200;

    public static void main(String[] args) {
        List<Edge> edgeList = new ArrayList<>();
        Scanner in = new Scanner(System.in);

        for (int i = 0 ; i < 200 ; i++) {
            int a = in.nextInt();
            //System.out.println(a);
            String s = in.nextLine();
            //System.out.println(s);
            String[] sArr = s.split("\t");
            for (String si : sArr) {
                if (!(si.equals(""))) {
                    int b = Integer.parseInt(si);
                    if (b < a)
                        continue;
                    edgeList.add(new Edge(a, b));
                    //System.out.println(new Edge(a, b));
                }
            }
        }
        int min = Integer.MAX_VALUE, t;
        for (int i = 0 ;  i <= 2120 ; i++) {
            List<Edge> temp = new ArrayList<>();
            for (Edge e :
                    edgeList) {
                temp.add(new Edge(e.x, e.y));
            }
            t = MinCut(temp);
            if (t < min)
                min = t;
        }
        for (Edge ei : edgeList) {
            //System.out.println(ei);
        }
        System.out.println(min);
    }


    public static int MinCut(List<Edge> e) {

        while (k != 2) {
            int s = (int)(Math.random()*e.size());
            Edge temp = e.get(s);
            int a = temp.x;
            int b = temp.y;
            //System.out.println(a + " " + b);
            int l;
            if (a>b)
                l = a;
            else
                l = b;
            e.remove(s);
            for (int i = 0 ; i < e.size() ; i++) {
                temp = e.get(i);
                if (temp.x == a || temp.x == b )
                    temp.x = l;
                if (temp.y == b || temp.y == a)
                    temp.y = l;
                if (temp.x == temp.y) {
                    e.remove(i);
                    i--;
                }
            }

            k--;
        }
        k = 200;
        return e.size();
    }

}

class Edge{
    int x, y;

    public Edge(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public String toString() {
        return "Edges: " + this.x + " " + this.y;
    }

}


