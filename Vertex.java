package DS;

import java.util.ArrayList;


public class Vertex {
   
    
    final String name;
    private final ArrayList<Edge> edges;

    
    public Vertex(String name1) {
        name = name1;
        edges = new ArrayList<>();
    }

    
    public boolean isAdjacent(Vertex secondVertex) {
        
//        if (edges.stream().anyMatch((e) -> ((e.first == secondVertex) || (e.second == secondVertex)))) {
//            return true;
//        }
//        return false;
    
        // Functional Operation can be used
         return edges.stream().anyMatch((e)-> ((e.first == secondVertex) || (e.second == secondVertex)));
    }

    
    public void addEdge(Edge e) { edges.add(e); }

    
    public ArrayList<Vertex> getAdjacent() {
        
        ArrayList<Vertex> output = new ArrayList<>();
        
        edges.stream().forEach((e) -> {
            if (this == e.first)  output.add(e.second);
            else  output.add(e.first);
        });
        
        return output;
    
    }

    
    public void removeEdgeWith(Vertex toBeRemoved) {

        for (int i = 0; i < edges.size(); i++) {
            
            Edge e = edges.get(i);
            
            if ((e.first == toBeRemoved) || (e.second == toBeRemoved)) {
                edges.remove(i);
                return;
            }
            
        }

    }

    
    public int getDegree() { return edges.size(); }

    
    public void print() {
        
        System.out.print(name + ":");
        
        ArrayList<Vertex> adjacent = getAdjacent();
        
        adjacent.stream().forEach((v) -> { 
            System.out.print(v.name + ",");
        });
        
        System.out.println();
    
    }
}