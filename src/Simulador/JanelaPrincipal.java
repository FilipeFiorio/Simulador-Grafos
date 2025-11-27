package Simulador;

import br.com.davidbuzatto.jsge.core.engine.EngineFrame;
import br.com.davidbuzatto.jsge.geom.RoundRectangle;
import br.com.davidbuzatto.jsge.imgui.GuiButton;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Modelo de projeto básico da JSGE.
 *
 * JSGE basic project template.
 *
 * @author Prof. Dr. David Buzatto
 */
public class JanelaPrincipal extends EngineFrame {

    private Grafo grafo;

    private List<GuiButton> listaBotoes;
    private List<Aresta> removerArestas;

    private final Color corBotoes = new Color(255, 168, 44);
    private final Color corFundo = new Color(39, 168, 170);
    private final Color corMoldura = new Color(8, 65, 117);

    private RoundRectangle rRectTelaDesenho;
    private RoundRectangle rRectMoldura;

    private GuiButton botaoProfundidade;
    private GuiButton botaoLargura;
    private GuiButton botaoExcluir;
    private GuiButton botaoLimpar;
    private GuiButton botaoNode;
    private GuiButton botaoAresta;
    private GuiButton botaoCursor;

    private boolean desenharNode;
    private boolean desenharAresta;
    private boolean apenasCursor;
    private boolean excluir;
    private boolean bProfundidade;
    private boolean bLargura;

    private Node nodeArrasto;

    public JanelaPrincipal() {

        super(
                1280, // largura                      / width
                720, // algura                       / height
                "GRAFOS", // título                       / title
                60, // quadros por segundo desejado / target FPS
                true, // suavização                   / antialiasing
                false, // redimensionável              / resizable
                false, // tela cheia                   / full screen
                false, // sem decoração                / undecorated
                false, // sempre no topo               / always on top
                false // fundo invisível              / invisible background
        );

    }

    @Override
    public void create() {

        useAsDependencyForIMGUI();

        grafo = new Grafo();

        desenharNode = false;
        desenharAresta = false;
        apenasCursor = false;
        bLargura = false;
        bProfundidade = false;
        excluir = false;

        listaBotoes = new ArrayList<>();
        removerArestas = new ArrayList<>();

        botaoCursor = new GuiButton(1050, 215, 200, 50, "CURSOR");
        botaoNode = new GuiButton(1050, 275, 95, 50, "NODE");
        botaoAresta = new GuiButton(1155, 275, 95, 50, "ARESTA");
        botaoExcluir = new GuiButton(1050, 335, 95, 50, "EXCLUIR");
        botaoLimpar = new GuiButton(1155, 335, 95, 50, "LIMPAR");
        botaoProfundidade = new GuiButton(1050, 395, 200, 50, "Busca em Profundidade");
        botaoLargura = new GuiButton(1050, 455, 200, 50, "Busca em Largura");

        listaBotoes.add(botaoLargura);
        listaBotoes.add(botaoProfundidade);
        listaBotoes.add(botaoCursor);
        listaBotoes.add(botaoAresta);
        listaBotoes.add(botaoNode);
        listaBotoes.add(botaoExcluir);
        listaBotoes.add(botaoLimpar);

        rRectTelaDesenho = new RoundRectangle(30, 30, 990, 660, 10);
        rRectMoldura = new RoundRectangle(25, 25, 1000, 670, 10);

    }

    @Override
    public void update(double delta) {

        for (GuiButton b : listaBotoes) {
            b.update(delta);
            b.setBackgroundColor(corMoldura);
            b.setBorderColor(corMoldura.darker());
            b.setTextColor(WHITE);
        }

        if (apenasCursor) {
            botaoCursor.setBackgroundColor(corBotoes);
        } else if (desenharAresta) {
            botaoAresta.setBackgroundColor(corBotoes);
        } else if (desenharNode) {
            botaoNode.setBackgroundColor(corBotoes);
        } else if (excluir) {
            botaoExcluir.setBackgroundColor(corBotoes);
        }

        if (botaoLargura.isMousePressed()) {
            bProfundidade = false;
            apenasCursor = false;
            desenharNode = false;
            desenharAresta = false;
            bLargura = true;
            excluir = false;
        } else if (botaoProfundidade.isMousePressed()) {
            bProfundidade = true;
            apenasCursor = false;
            desenharNode = false;
            desenharAresta = false;
            bLargura = false;
            excluir = false;
        } else if (botaoAresta.isMousePressed()) {
            bProfundidade = false;
            apenasCursor = false;
            desenharNode = false;
            desenharAresta = true;
            bLargura = false;
            excluir = false;
        } else if (botaoCursor.isMousePressed()) {
            bProfundidade = false;
            apenasCursor = true;
            desenharNode = false;
            desenharAresta = false;
            bLargura = false;
            excluir = false;
        } else if (botaoNode.isMousePressed()) {
            bProfundidade = false;
            apenasCursor = false;
            desenharNode = true;
            desenharAresta = false;
            bLargura = false;
            excluir = false;
        } else if (botaoExcluir.isMousePressed()) {
            bProfundidade = false;
            apenasCursor = false;
            desenharNode = false;
            desenharAresta = false;
            bLargura = false;
            excluir = true;
        } else if (botaoLimpar.isMousePressed()) {
            bProfundidade = false;
            apenasCursor = false;
            desenharNode = false;
            desenharAresta = false;
            bLargura = false;
            excluir = false;

            grafo.limpar();
        }

        if (isMouseButtonPressed(MOUSE_BUTTON_LEFT)) {
            if (desenharNode && mouseInRRect(rRectTelaDesenho)) {
                Node novoNode = new Node(getMouseX(), getMouseY(), 10);
                grafo.addNode(novoNode);
            }
        }

        /* Codigo para desenhar linha arrastando o cursor do mouse
         * primeiro deve se clicar num node, arrastar até outro node
         * e soltar, assim desenhera a linha.
         * o primeiro bloco de if checa se o mouse foi clicado em cima de algum node,
         * o segundo bloco verifica que se ao soltar o cursor esta em outro node, 
         * verifica se o nodeInicial != do final, e se ambos nao sao nulos
         * ao final seta como nulo o node Iniacial, já que é um atributo da classe.
         */
        if (isMouseButtonPressed(MOUSE_BUTTON_LEFT)) {
            if (desenharAresta && mouseInRRect(rRectTelaDesenho)) {
                Node temp = getMouseNode(grafo.getListaNode());
                grafo.setNodeIni(temp);
            }
        }

        if (isMouseButtonReleased(MOUSE_BUTTON_LEFT)) {
            if (desenharAresta && mouseInRRect(rRectTelaDesenho)) {
                if (grafo.getNodeIni() != null) {
                    Node nodeFinal = getMouseNode(grafo.getListaNode());
                    if (nodeFinal != null && nodeFinal != grafo.getNodeIni()) {
                        Aresta novaAresta = new Aresta(grafo.getNodeIni(), nodeFinal);
                        grafo.addAresta(novaAresta);
                    }
                }
            }

            grafo.setNodeIni(null);
        }

        /*
            Bloco de codigo para poder arrastar os nodes
            verifica qual node foi clicado
            enquanto o botao esta apertado muda as coord x y do node
            ao soltar seta o nodeArrasto novamento para null
            asssim nenhum node sera arrastado
        */
        if (isMouseButtonDown(MOUSE_BUTTON_LEFT) && apenasCursor) {
            nodeArrasto = getMouseNode(grafo.getListaNode());
        }

        if (isMouseButtonDown(MOUSE_BUTTON_LEFT) && apenasCursor) {
            if (nodeArrasto != null) {
                nodeArrasto.setCentroX(getMouseX());
                nodeArrasto.setCentroY(getMouseY());
            }
        }

        if (isMouseButtonReleased(MOUSE_BUTTON_LEFT) && apenasCursor) {
            nodeArrasto = null;
        }

        /*
            bloco de codigo para apagar um node ou uma linha ou os dois
            remoce um node clicando nele, cria um iterator para percorrer a listas de arestas, 
            enaquanto tem aresta na lista verifica se um dos 2 nodes q compoe
            a aresta é o node removido, se for remove essa aresta
        */
        if (isMouseButtonPressed(MOUSE_BUTTON_LEFT) && excluir) {
            Node nodeRemovido = getMouseNode(grafo.getListaNode());
            grafo.removeNode(nodeRemovido);
            
            Iterator<Aresta> iterator = grafo.getListaAresta().iterator();
            
            while(iterator.hasNext()) {
                Aresta aresta = iterator.next();
                
                if(aresta.getNode1() == nodeRemovido || aresta.getNode2() == nodeRemovido) {
                    iterator.remove();
                }
            }

        }
        
        if(isMouseButtonPressed(MOUSE_BUTTON_LEFT) && excluir) {
            Aresta arestaRemovida = getArestaMouse(grafo.getListaAresta());
            grafo.removeAresta(arestaRemovida);
        }

    }

    @Override
    public void draw() {

        clearBackground(corFundo);

        setFontName("inter");
        setFontStyle(FONT_BOLD);

        for (GuiButton b : listaBotoes) {
            b.draw();
        }

        rRectMoldura.fill(this, corMoldura);
        rRectTelaDesenho.fill(this, WHITE);

        grafo.drawGrafo(this);
        
        if(apenasCursor) {
            drawText("Clique em um node para arrastá-lo", 344.35, 702, 20, WHITE);
        } else if(desenharNode) {
            drawText("Clique no quadro branco para desenhar um node", 245.31, 702, 20, WHITE);
        } else if(desenharAresta) {
            drawText("Clique em um node e arraste até outro para desenhar uma aresta", 196.56, 702, 20, WHITE);
        } else if(excluir) {
            drawText("Clique em um node ou na parte central de uma aresta para excluir", 192.25, 702, 20, WHITE);
        } else if(bProfundidade || bLargura) {
            drawText("Clique em um node para iniciar a operação", 310.375, 702, 20, WHITE);
        }
        
    }

    private boolean mouseInRRect(RoundRectangle r) {

        double x = getMouseX();
        double y = getMouseY();

        return x >= r.x && x <= r.x + r.width
                && y >= r.y && y <= r.y + r.height;
    }

    private Node getMouseNode(List<Node> listaNode) {
        double x = getMouseX();
        double y = getMouseY();

        for (Node n : listaNode) {
            double xEsq = n.getCentroX() - n.getRaio();
            double xDir = n.getCentroX() + n.getRaio();
            double yTop = n.getCentroY() - n.getRaio();
            double yBot = n.getCentroY() + n.getRaio();

            if ((x >= xEsq && x <= xDir) && (y >= yTop && y <= yBot)) {
                return n;
            }
        }

        //o mouse nao esta em cima de nenhum node
        return null;
    }
    
    private Aresta getArestaMouse(List<Aresta> listaAresta) {
        double x = getMouseX();
        double y = getMouseY();
        
        for(Aresta a : listaAresta) {
            double xIni = a.getNode1().getCentroX();
            double yIni = a.getNode1().getCentroY();
            double xFim = a.getNode2().getCentroX();
            double yFim = a.getNode2().getCentroY();
            
            double centroX = (xFim + xIni) / 2;
            double centroY = (yFim + yIni) / 2;
            
            //tolerancia do click de 1 terco do tamanho e x e y da aresta;
            double toleranciaClickX = centroX / 1.5;
            double toleranciaClickY = centroY / 1.5;
            
            if((centroX - toleranciaClickX <= x && x <= centroX + toleranciaClickX) &&
               (centroY - toleranciaClickY <= y && y <= centroY + toleranciaClickY)) {
                return a;
            }
        }
        
        return null;
    }


    public static void main(String[] args) {
        new JanelaPrincipal();
    }

}
