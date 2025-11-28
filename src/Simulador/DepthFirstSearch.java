package Simulador;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author filipe
 */
public class DepthFirstSearch {

    private boolean[] marked;   
    private int[] edgeTo;       
    private final int source;   

    public DepthFirstSearch(Grafo g, int s) {
        this.source = s;
        this.marked = new boolean[g.getNumsNode()];
        this.edgeTo = new int[g.getNumsNode()];

        dfs(g, s);
    }

    private void dfs(Grafo g, int v) {
        marked[v] = true;
        for (int w : g.adj(v)) {
            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(g, w);
            }
        }
        
        g.getNodePeloId(v).setCorNode(Color.GREEN);
        
    }

    public boolean hasPathTo(int v) {
        return marked[v];
    }
    
    public List<Integer> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        List<Integer> path = new ArrayList<>();
        for (int x = v; x != source; x = edgeTo[x]) {
            path.add(x);
        }
        path.add(source);
        Collections.reverse(path);
        return path;
    }
    
    public boolean[] getMarked() {
        return marked;
    }
}

