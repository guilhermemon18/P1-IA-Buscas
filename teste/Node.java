package teste;

import java.util.ArrayList;
import java.util.List;

public class Node<T extends Comparable<T>> implements Comparable<Node<T>> {
    // Id for readability of result purposes
    public T id;//é basicamente o nomedovértice, no caso vai ser string
    // Parent in the path
    public Node<T> parent = null;
    public List<Edge> neighbors;//vizinhos deles, se for grafo não orientado o vizinho tem este como vizinho também.

    // Evaluation functions
    public double f = Double.MAX_VALUE;
    public double g = Double.MAX_VALUE;
    // Hardcoded heuristic
    public double h; //heuristica!

    Node(T id,double h){
          this.h = h;
          this.id = id;
          this.neighbors = new ArrayList<>();
    }

    @Override
    public int compareTo(Node<T> n) {
          return Double.compare(this.f, n.f);
    }

    public  class Edge {//tinha static na frente de class, removi para consertar!
          Edge(int weight, Node<T> node){
                this.weight = weight;
                this.node = node;
          }

          public int weight;
          public Node<T> node;
    }

    public void addBranch(int weight, Node<T> node){
          Edge newEdge = new Edge(weight, node);
          neighbors.add(newEdge);
    }

    public double calculateHeuristic(Node<T> target){
          return this.h;
    }
}
