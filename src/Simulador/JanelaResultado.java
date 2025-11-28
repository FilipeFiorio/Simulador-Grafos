package Simulador;

import br.com.davidbuzatto.jsge.core.engine.EngineFrame;
import static br.com.davidbuzatto.jsge.core.engine.EngineFrame.WHITE;
import java.util.ArrayList;
import java.util.List;


public class JanelaResultado extends EngineFrame {

    private Grafo grafo;
    private Node inicio;
    private TipoOperacao tipo;
    private double desvio;
    private String nodeVisitados;

    private List<Integer> ordemVisita;  
    private int indiceAtual;

    public JanelaResultado(Grafo grafo, Node inicio, TipoOperacao tipo) {
        super(
                990, 
                660, 
                "BUSCA EM " + tipo.toString(), 
                60, 
                true, 
                false, 
                false, 
                false, 
                false, 
                false);

        this.grafo = grafo;
        this.inicio = inicio;
        this.tipo = tipo;

        executarBusca();
    }
    
    @Override
    public void create() {
        indiceAtual = 0;
        ordemVisita = new ArrayList<>();
        nodeVisitados = "Vértices visitados";
    }


    @Override
    public void update(double delta) {
        
        if(indiceAtual < ordemVisita.size()) {
            indiceAtual++;
        }
        
    }

    @Override
    public void draw() {

        clearBackground(WHITE);
        setFontName("inter");

        drawText(nodeVisitados, 100, 50, 20, BLACK);
        
        drawDesviado();
    }
    
    private void drawDesviado() {
        for (Aresta a : grafo.getListaAresta()) {
            a.drawLineDesviado(this, desvio);
        }
        for (Node n : grafo.getListaNode()) {
            n.drawNodeDesviado(this, desvio);
        }
    }
    
    private void executarBusca() {

        int idNodeinicio = inicio.getId();
        boolean[] marked;

        if(tipo == TipoOperacao.PROFUNDIDADE) {
            DepthFirstSearch dfs = new DepthFirstSearch(grafo, idNodeinicio);
            marked = dfs.getMarked();

            for(int i = 0; i < marked.length; i++) {
                if (marked[i]) {
                    ordemVisita.add(i);
                }
                nodeVisitados += i + " ";
            }
        } else {
            BreadthFirstSearch bfs = new BreadthFirstSearch(grafo, idNodeinicio);
            marked = bfs.getMarked(); 

            for(int i = 0; i < marked.length; i++) {
                if(marked[i]) {
                    ordemVisita.add(i);
                }
                nodeVisitados += i + " ";
            }
        }
    }
}