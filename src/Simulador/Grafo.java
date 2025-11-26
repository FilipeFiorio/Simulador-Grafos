package Simulador;

import br.com.davidbuzatto.jsge.core.engine.EngineFrame;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author filipe
 */
public class Grafo {

    private List<Node> listaNode = new ArrayList<>();
    private List<Aresta> listaAresta = new ArrayList<>();
    
    private Node nodeIni = null;
    
        public void drawGrafo(EngineFrame e) {
        for(Aresta a : listaAresta) {
            a.drawLine(e);
        }
        
        for(Node n :listaNode) {
            n.drawNode(e);
        }
    }
    
    public void addNode(Node node) {
        listaNode.add(node);
    }
    
    public void addAresta(Aresta aresta) {
        listaAresta.add(aresta);
    }
    
    public List<Node> getListaNode() {
        return listaNode;
    }
    
    public void setListaNode(List<Node> listaNode) {
       this.listaNode = listaNode;
    }
    
    public void setListaAresta(List<Aresta> listaAresta) {
       this.listaAresta = listaAresta;
    }
    
    public List<Aresta> getListaAresta() {
        return listaAresta;
    }
    
    public void setNodeIni(Node nodeIni) {
        this.nodeIni = nodeIni;
    }
    
    public Node getNodeIni() {
        return nodeIni;
    }
    
    public void limpar() {
        listaAresta = null;
        listaNode = null;
    }
    
}
