import java.io.File;
import java.util.*;

//----------SCC----------//

public class SCC {


    static int[] finTime;
    static int[] explored;
    static int time;
    static Node leader;
    static Node[] leaderList;



    public static void main(String[] args) {
        int n = 875714;
        List<Node> nodeList = new ArrayList<>();
        for (int i = 0 ; i<n ; i++)
            nodeList.add(new Node(i));    //starts at 0
        finTime = new int[n];
        explored = new int[n];
        for (int i = 0; i < n; i++) {
            explored[i] = 0;
            finTime[i] = -1;
        }
        leaderList = new Node[n];
        time = 0;
        int a, b;
        Scanner in;
        try {
            in = new Scanner(new File("scc.txt"));
            while (in.hasNextInt()) {
                a = in.nextInt();
                b = in.nextInt();
                System.out.println(a + " " + b);
                nodeList.get(a - 1).out.add(nodeList.get(b-1));   //change for test
                nodeList.get(b-1).in.add(nodeList.get(a-1));
            }
        }
        catch (Exception e) {
            System.out.println(e);
            System.out.println("ERRRROROOR");
            in = new Scanner(System.in);
        }


        DFSLoopRev(nodeList);
        //--------------PART A-------------//
//        System.out.println("A\n");
//        for (Node i :
//                nodeList) {
//            System.out.println(i.id + " " + finTime[i.id]);
//        }



        for (Node i : nodeList) {
            i.id = finTime[i.id];
        }

        System.out.println("time " + time);

        nodeList.sort((Node o1, Node o2)-> {
                if (o1.id >= o2.id)
                    return 1;
                else
                    return -1;
        });

        for (int i = 0; i < n; i++) {
            explored[i] = 0;
        }





        DFSLoop(nodeList);
        System.out.println();


        for (Node i :
                leaderList) {
            explored[i.id]++;
        }


        Arrays.sort(explored);
        for (int i = explored.length - 1 ; i >= explored.length - 5 ; i--)
            System.out.print(explored[i]-1 + ", ");



    }

    public static void DFSLoopRev(List<Node> list) {
        for (int i = list.size() - 1 ; i >= 0 ; i--){
            if (explored[i] == 0) {
                DFSRev(list.get(i));
            }
        }
    }



    public static void DFSLoop(List<Node> list) {
        for (int i = list.size() - 1 ; i >= 0 ; i--){
            if (explored[i] == 0) {
                leader = list.get(i);
                DFS(list.get(i));
            }
        }
    }

    public static void DFS(Node a){
        Stack<Node> stack = new Stack<>();
        stack.push(a);
        Node s;
        while(!stack.isEmpty()) {
            s = stack.get(stack.size()-1);
            if (explored[s.id] == 1) {
                leaderList[s.id] = leader;
                stack.pop();
            }
            explored[s.id] = 1;
            for (Node i : s.out) {
                if (explored[i.id] == 0)
                    stack.push(i);
            }
        }

    }

    public static void DFSRev(Node a){
        Stack<Node> stack = new Stack<>();
        stack.push(a);
        Node s;
        while(!stack.isEmpty()) {
            s = stack.get(stack.size()-1);
            if (explored[s.id] == 1) {
                if (finTime[s.id] == -1) {
                    finTime[s.id] = time;
                    time++;
                }
                stack.pop();
            }
            explored[s.id] = 1;
            for (Node i : s.in) {
                if (explored[i.id] == 0)
                    stack.push(i);
            }
        }

    }

    public static void Display (List<Node> list) {
        for (Node b : list) {
            System.out.print(b.id + " ");
        }
        System.out.println();
    }





}

 class Node {
    int id;
    public List<Node> out = new ArrayList<>();
    public List<Node> in = new ArrayList<>();

    public Node(int i) {
        this.id = i;
    }


}

