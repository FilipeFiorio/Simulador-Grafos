package Simulador;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author filipe
 */
public class BreadthFirstSearch {

    private boolean[] marked;
    private int[] edgeTo;
    private int[] distTo;

    public BreadthFirstSearch(Grafo g, int s) {
        marked = new boolean[g.getNumsNode()];
        edgeTo = new int[g.getNumsNode()];
        distTo = new int[g.getNumsNode()];
        bfs(g, s);
    }

    private void bfs(Grafo g, int s) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        marked[s] = true;

        while (!queue.isEmpty()) {
            int v = queue.remove();
            for (int w : g.adj(v)) {
                if (!marked[w]) {
                    queue.add(w);
                    marked[w] = true;
                    edgeTo[w] = v;
                    distTo[w] = distTo[v] + 1;
                   
                }
            }
        }
    }

    public boolean[] getMarked() { 
        return marked; 
    }
    
    public int[] getEdgeTo() { 
        return edgeTo; 
    }
    
    public int[] getDistTo() {
        return distTo;
    }
}

