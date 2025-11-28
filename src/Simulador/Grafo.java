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
        for (Aresta a : listaAresta) {
            a.drawLine(e);
        }

        for (Node n : listaNode) {
            n.drawNode(e);
        }
    }

    public void drawGrafoDesviado(EngineFrame e, double desvio) {
        for (Aresta a : listaAresta) {
            a.drawLine(e);
        }

        for (Node n : listaNode) {
            n.drawNode(e);
        }
    }

    public void addNode(Node node) {
        listaNode.add(node);
    }

    public void addAresta(Aresta aresta) {
        listaAresta.add(aresta);
    }

    public void removeNode(Node node) {
        listaNode.remove(node);
    }

    public void removeAresta(Aresta aresta) {
        listaAresta.remove(aresta);
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
        listaAresta.clear();
        listaNode.clear();

    }

    public List<Integer> adj (int id) {
        
        Node node = getNodePeloId(id);
        List<Integer> vizinhos = new ArrayList<>();

        for (Aresta a : listaAresta) {
            if (a.getNode1() == node) {
                vizinhos.add (a.getNode2().getId());
            } else if (a.getNode2() == node) {
                vizinhos.add(a.getNode1().getId());
            }
        }

        return vizinhos;
    }

    public int getNumsNode() {
        return listaNode.size();
    }

    public Node getNodePeloId(int id) {
        for(Node n : listaNode) {
            if(n.getId() == id) {
                return n;
            }
        }
        return null;
    }

}
