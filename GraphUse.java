package DS;

import java.util.HashMap;

class AdjacencyNode{
    int dest;
    AdjacencyNode next;
}

class AdjacencyList{AdjacencyNode head;}

class Graph2{
    int v;
    AdjacencyList list[];
}

public class GraphUse {

    public static AdjacencyNode newNode(int dest){        
        AdjacencyNode head = new AdjacencyNode();
        head.dest = dest;
        head.next = null;        
        return head;
    }

    public static Graph2 createGraph(int v){
        Graph2 g = new Graph2();
        g.v = v;
        g.list = new AdjacencyList[v];
        for(int i = 0; i < v; i++)
            g.list[i].head = newNode(-1);
        return g;
    }
    
    public static void addEdge(Graph2 g, int src, int dest){
        
        // Add edge from src to dest
        // New node is added to adjacency list
        AdjacencyNode node = newNode(src);
        node.next = g.list[src].head;
        g.list[src].head = node;
        
        // Graph is undirected so ad edge from dest to src
        node = newNode(src);
        node.next = g.list[dest].head;
        g.list[dest].head = node;
    }
   
    public static void printGraph(Graph2 g){
        for(int v = 0; v < g.v; v++){
            AdjacencyNode crawl = g.list[v].head;
            System.out.println("Adjacency List of Vertex head" + v);
            while(crawl != null){
                System.out.println(crawl.dest);
                crawl = crawl.next;
            }
            System.out.println();
        }
    }
    
    public static void main(String[] args) {
   
        Graph g = new Graph();

        g.addVertex("d");
        g.addVertex("g");
        g.addVertex("n");
        g.addVertex("a");
        g.addEdge("d", "g");
        g.addEdge("d", "n");
        g.addEdge("n", "g");
        g.addEdge("d", "g");
        g.addEdge("d", "g");
        g.removeEdge("d", "g");
       
        g.print();
        
        System.out.println(g.areTwoVerticesConnected("d", "a", new HashMap<>()));

        g = new Graph(6);
        g.addEdge(5, 2);
        g.addEdge(5, 0);
        g.addEdge(4, 0);
        g.addEdge(4, 1);
        g.addEdge(2, 3);
        g.addEdge(3, 1);
 
        System.out.println("Following is a Topological " + "sort of the given graph");
        
        g.topologicalSort();
       
//        g.BFS();
//        
//        g.BFS(2);
//
//        g.DFS();
    }
}