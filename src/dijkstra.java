import java.io.File;
import java.util.*;


public class dijkstra {
    static int k = 200;

    public static void main(String[] args) {
        Scanner in;
        try {
            in = new Scanner(new File("d.txt"));
        }
        catch (Exception e){
            in = new Scanner(System.in);
        }

        List<Vertex> vList = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            Vertex v = new Vertex(i);
            v.length = 1000000;
            vList.add(v);
        }



        Vertex a, b;
        in.useDelimiter(",|\\t|\\n");
        int c;
        while(in.hasNextInt()) {
            a = vList.get(in.nextInt()-1);
            System.out.println("A" + a.id);
            while(in.hasNextInt()) {
                System.out.println("reached2");
                b = vList.get(in.nextInt()-1);
                System.out.println("b " + b.id);
                c = in.nextInt();
                System.out.println("c " + c);
                a.addEdge(b, c);

            }
            if (in.hasNext())
                in.next();
        }

        Dijkstra(vList);
        int[] results = {6,36,58,81,98,114,132,164,187,196};
        for (int t : results) {
            System.out.println(vList.get(t).length);
        }
    }

    public static void Dijkstra(List<Vertex> vList) {
        PriorityQueue<Vertex> pq = new PriorityQueue<>();
        Vertex s = vList.get(0);
        s.length = 0;
        pq.add(s);
        while(!pq.isEmpty()) {
            s = pq.poll();
            for (Edge e : s.edgeList) {
                int length = e.b.length;
                int newLength = e.length + s.length;
                if (length == 1000000) {
                    pq.add(e.b);
                }
                if (newLength < length) {
                    pq.remove(e.b);
                    e.b.length = newLength;
                    pq.add(e.b);
                }
            }
        }
    }
}

class Vertex implements Comparable {
    int id;
    int length;
    List<Edge> edgeList = new ArrayList<Edge>();

    public Vertex(int id) {
        this.id = id;
    }

    public void addEdge(Vertex b, int c) {
        edgeList.add(new Edge(b, c));
    }

    public boolean equals(Vertex other) {
        return this.getLength() == other.getLength();
    }

    public int compareTo(Object other) {
        Vertex v = (Vertex) other;
        if (this.equals(v))
            return 1;
        if (this.getLength() > v.getLength())
            return 1;
        else
            return -1;
    }

    public int getId() {
        return id;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}

class Edge {
    Vertex b;
    int length;

    public Edge(Vertex b, int length) {
        this.b = b;
        this.length = length;
    }
}

