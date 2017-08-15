package DS;

import java.util.*;


public class Graph implements GraphInterface{

    private final HashMap<String, Vertex> vertices;
  
    private int v;
    private LinkedList<Integer> list[];
    
    public Graph() { this.vertices = new HashMap<>(); }

    public Graph(int v){
       
       this.v = v;
       list = new LinkedList[v];
       
       for(int i = 0; i < v; i++)
           list[i] = new LinkedList<>();
       
       vertices = null;
    }
    
    public void print() {
        System.out.println("Printing Graph");
        Vertex[] allVertices = vertices.values().toArray(new Vertex[0]);
        for (Vertex vertex:allVertices) 
            vertex.print();
    }

    public boolean hasKey(String name){
        return vertices.containsKey(name);
    } 
    public boolean notHasKey(String first, String second){
        return !vertices.containsKey(first) || !vertices.containsKey(second);
    } 
    
    @Override
    public void addVertex(String name) {
        if (hasKey(name))  
            return;
        Vertex vertex = new Vertex(name);
        vertices.put(name, vertex);
    }

    public void addEdge(int v, int w){ list[v].add(w); }

    
    @Override
    public void addEdge(String first, String second) {
       
        if (notHasKey(first, second)) return;
        
        Vertex firstVertex = vertices.get(first),
               secondVertex = vertices.get(second);
        
        if (firstVertex.isAdjacent(secondVertex)) return;
        
        Edge e = new Edge();
        
        e.first = firstVertex;
        e.second = secondVertex;
        
        firstVertex.addEdge(e);
        secondVertex.addEdge(e);
    }
    
    @Override
    public void removeVertex(String name) {
        
        if (hasKey(name)) return;
     
        Vertex toBeRemoved = vertices.get(name);
        vertices.remove(name);
        
        ArrayList<Vertex> adjacentVertices = toBeRemoved.getAdjacent();

        adjacentVertices.stream().forEach((adjacentVertex) -> { 
            adjacentVertex.removeEdgeWith(toBeRemoved);
        });
    }

    @Override
    public void removeEdge(String first, String second) {
      
        if (notHasKey(first, second)) return;
        
        Vertex firstVertex = vertices.get(first),
               secondVertex = vertices.get(second);
       
        firstVertex.removeEdgeWith(secondVertex);
        secondVertex.removeEdgeWith(firstVertex);
    }

    @Override
    public ArrayList<String> getAdjacent(String name) {
     
        if (!hasKey(name)) return null;
        
        Vertex vertex = vertices.get(name);
      
        ArrayList<Vertex> adjacent = vertex.getAdjacent();	
        
        ArrayList<String> output = new ArrayList<>();
        
        adjacent.stream().map((v1) -> v1.name).forEach((name1) -> {
            output.add(name1);
        });
        return output;
    }

    @Override
    public int getDegree(String name) {
        
        if (!hasKey(name)) return -1;
        
        Vertex vertex = vertices.get(name);
     
        return vertex.getDegree();
    }

    @Override
    public int numberVertices() { return vertices.size(); }

    @Override
    public int numberOfEdges() {
         
        int count = 0;
        
        Vertex[] allVertices = vertices.values().toArray(new Vertex[0]);
        
        for (Vertex vertex : allVertices) 
            count += vertex.getDegree();
        
        return count/2;
    }

    @Override
    public boolean areTwoVerticesConnected(String first, String second,
                                           HashMap<Vertex,Boolean> visited) {

        if (notHasKey(first, second))
            return false;
        
        if (first.equals(second)) return true;
        
        Vertex firstVertex = vertices.get(first);
        ArrayList<Vertex> adjacent = firstVertex.getAdjacent();
        visited.put(firstVertex, true);

        return adjacent.stream().filter((v1)->
               !(visited.containsKey(v1))).anyMatch((v2)->
               (areTwoVerticesConnected(v2.name, second, visited)));
    }
   
    @Override
    public boolean isConnected() { return false; }
   
    
    
//    DFS
//    DFS of whole graph
    void DFSUtil(int visit, boolean visited[]){
         
        // Mark the current node as visited and print it
        visited[visit] = true;
        System.out.print(visit + " ");
 
        // Recur for all the vertices adjacent to this vertex
        Iterator<Integer> i = list[visit].listIterator();
        while (i.hasNext()){
            int n = i.next();
            if (!visited[n])
                DFSUtil(n,visited);
        }
    }
    
    public void DFS(){
        boolean visited[] = new boolean[v];
        for(int i = 0; i < v; ++i){
            if(!visited[i])
                DFSUtil(i, visited);
        }
        System.out.println();
    }
    

//    BFS
//    BFS from given source
    public void BFS(int s){
        
        boolean visited[] = new boolean[v];
 
        // Create a queue for BFS
        LinkedList<Integer> queue = new LinkedList<>();
 
        // Mark the current node as visited and enqueue it
        visited[s]=true;
        queue.add(s);
 
        while (!queue.isEmpty()) {
            // Dequeue a vertex from queue and print it
            s = queue.poll();
            System.out.print(s+" ");
 
            // Get all adjacent vertices of the dequeued vertex s
            // If a adjacent has not been visited, then mark it visited and enqueue it
            Iterator<Integer> i = list[s].listIterator();
            while (i.hasNext()){
                int n = i.next();
                if (!visited[n]){
                    visited[n] = true;
                    queue.add(n);
                }
            }
        }
        System.out.println();
    }

//    BFS of whole graph
    public void BFSUtil(int s){
        
        boolean visited[] = new boolean[v];
 
        // Create a queue for BFS
        LinkedList<Integer> queue = new LinkedList<>();
 
        // Mark the current node as visited and enqueue it
        visited[s] = true;
        queue.add(s);
        System.out.print(s+" ");
        s = queue.poll();   // Dequeue a vertex from queue 
                   
        while (!queue.isEmpty()) {
            // Get all adjacent vertices of the dequeued vertex s
            // If a adjacent has not been visited, then mark it visited and enqueue it
            Iterator<Integer> i = list[s].listIterator();
            while (i.hasNext()){
                int n = i.next();
                if (!visited[n]){
                    visited[n] = true;
                    queue.add(n);
                }
            }
        }
    }
 
    public void BFS(){
        boolean visited[] = new boolean[v];
        for(int i = 0; i < v; ++i){
            if(!visited[i]){
               BFSUtil(i);
               visited[i] = true;
            }
        }
        System.out.println();
    }
    
    
//    Dijkstra's algo
       
    int minDistance(int distance[], boolean sptSet[]){
        int min = Integer.MAX_VALUE, min_index = -1;
        for(int i = 0; i < v; i++){
            if(sptSet[i] == false && distance[i] <= min){
               min = distance[i];
               min_index = i;
            }
        }
        return min_index;
    }
    
    void printSolution(int distance[], int n){    
        System.out.println("Vertex  Distance from source");
        for(int i = 0; i < v; i++)
           System.out.println(i + "\t\t" + distance[i]);
    }
    
    void dijkstra(int graph[][], int source){
        int distance[] = new int[v];
        boolean sptSet[] = new boolean[v];
        for(int i = 0; i < v; i++){
           distance[i] = Integer.MAX_VALUE;
           sptSet[i] = false;
        }
        distance[source] = 0;
        for(int i = 0; i < v-1; i++){
           int u = minDistance(distance, sptSet);
           sptSet[u] = true;
           for(int j = 0; j < v; j++){
               if(!sptSet[j] && graph[u][j]!=0 
                       && distance[u] != Integer.MAX_VALUE
                       && (distance[u] + graph[u][j]) < distance[j]){
                  // Condition #3 is to prevent overflow in case source vertex is isolated  
                  distance[j] = distance[u] + graph[u][j]; 
               }
           }
        }
        printSolution(distance, v);
    }
    
    
     void topologicalSortUtil(int v, boolean visited[], Stack stack){
        // Mark the current node as visited.
        visited[v] = true;
        Integer i;
 
        // Recur for all the vertices adjacent to this vertex
        Iterator<Integer> it = list[v].iterator();
        while (it.hasNext()){
            i = it.next();
            if (!visited[i])
                topologicalSortUtil(i, visited, stack);
        }
 
        // Push current vertex to stack which stores result
        stack.push(v);
    }
 
    // The function to do Topological Sort. It uses recursive topologicalSortUtil()
    void topologicalSort(){
        
        Stack stack = new Stack();
 
        // Mark all the vertices as not visited
        boolean visited[] = new boolean[v];
        for (int i = 0; i < v; i++)
            visited[i] = false;
 
        // Call the recursive helper function to store
        // Topological Sort starting from all vertices one by one
        for (int i = 0; i < v; i++)
            if (visited[i] == false)
                topologicalSortUtil(i, visited, stack);
 
        // Print contents of stack
        while (stack.empty()==false)
            System.out.print(stack.pop() + " ");
    }
}