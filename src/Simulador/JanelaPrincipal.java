package Simulador;

import br.com.davidbuzatto.jsge.core.engine.EngineFrame;
import br.com.davidbuzatto.jsge.geom.RoundRectangle;
import br.com.davidbuzatto.jsge.imgui.GuiButton;
import java.awt.Color;
import java.awt.Cursor;
import java.util.ArrayList;
import java.util.List;

/**
 * Modelo de projeto básico da JSGE.
 * 
 * JSGE basic project template.
 * 
 * @author Prof. Dr. David Buzatto
 */
public class JanelaPrincipal extends EngineFrame {
    
    private List<Node> listaNode;
    private List<Linha> listaLinha;
    private List<GuiButton> listaBotoes;
    
    private final Color corBotoes = new Color(255, 168, 44);
    private final Color corFundo = new Color(39, 168, 170);
    private final Color corMoldura = new Color(8, 65, 117);
    private final Color corRespostavisual = new Color(40, 40, 40, 40);
    
    private RoundRectangle rRectTelaDesenho;
    private RoundRectangle rRectMoldura;
    private RoundRectangle rRectBotaoNode;
    private RoundRectangle rRectBotaoLinha;
    private RoundRectangle rRectBotaoCursor;
    
    private final Node nodeBotao = new Node(1150, 305, 1914);
    private final Linha linhaBotao = new Linha(1100, 366, 1200, 366);
    
    private GuiButton botaoProfundidade;
    private GuiButton botaoLargura;
    
    private boolean desenharNode;
    private boolean desenharlinha;
    
    public JanelaPrincipal() {
        
        super(
            1280,                 // largura                      / width
            720,                 // algura                       / height
            "GRAFOS",      // título                       / title
            60,                  // quadros por segundo desejado / target FPS
            true,                // suavização                   / antialiasing
            false,               // redimensionável              / resizable
            false,               // tela cheia                   / full screen
            false,               // sem decoração                / undecorated
            false,               // sempre no topo               / always on top
            false                // fundo invisível              / invisible background
        );
        
    }
    
    @Override
    public void create() {
        
        useAsDependencyForIMGUI();
        
        desenharNode = false;
        desenharlinha = false;
        
        listaNode = new ArrayList<>();
        listaLinha = new ArrayList<>();
        listaBotoes = new ArrayList<>();
        
        botaoProfundidade = new GuiButton(1050, 420, 200, 50, "Busca em Profundidade");
        botaoLargura = new GuiButton(1050, 480, 200, 50, "Busca em Largura");
        
        
        listaBotoes.add(botaoLargura);
        listaBotoes.add(botaoProfundidade);
        
        rRectTelaDesenho = new RoundRectangle(30, 30, 990, 660, 10);
        rRectMoldura = new RoundRectangle(25, 25, 1000, 670, 10);
        rRectBotaoCursor = new RoundRectangle(1050, 199, 200, 64, 10);
        rRectBotaoNode = new RoundRectangle(1050, 273, 200, 64, 10);
        rRectBotaoLinha = new RoundRectangle(1050, 346, 200, 64, 10);
        
    }

    @Override
    public void update( double delta ) {
        
        for(GuiButton b : listaBotoes) {
            b.update(delta);
            b.setBackgroundColor(corBotoes);
            b.setTextColor(WHITE);
        }
        
        if(isMouseButtonPressed(MOUSE_BUTTON_LEFT)) {
            if(mouseIn(rRectBotaoCursor)) {
                desenharlinha = false;
                desenharNode = false;
            } else if(mouseIn(rRectBotaoNode)) {
                desenharlinha = false;
                desenharNode = true;
            } else if(mouseIn(rRectBotaoLinha)) {
                desenharNode = false;
                desenharlinha = true;
            }
        }
        
        if(isMouseButtonPressed(MOUSE_BUTTON_LEFT)) {
            if(desenharNode && mouseIn(rRectTelaDesenho)) {
                Node novo = new Node(getMouseX(), getMouseY(), 10);
                listaNode.add(novo);
                System.out.println("oi");
            }
        }
       
        
    }

    @Override
    public void draw() {
        
        clearBackground( corFundo );
        
        setFontName("inter");
        setFontStyle(FONT_BOLD);
        
        
        botaoLargura.draw();
        botaoProfundidade.draw();
        
        rRectMoldura.fill(this, corMoldura);
        rRectTelaDesenho.fill(this, WHITE);
        
        //Criando os botoes personalizados laterais, primeiro o cursor, depois o de nodes e o 3ceiro de linhas
        drawText("CURSOR",1125.5, 241 , WHITE);
        
        nodeBotao.drawNode(this, 20, corMoldura, corBotoes);
        drawText("NODE", 1133.5, 305, 11, WHITE);
        
        linhaBotao.drawLine(this, corBotoes);
        drawText("CONEXAO", 1122, 377, WHITE);
        
        //resposta visual de quando o mouse passa pelo botao, testando como trocar o cursor pra deixar mais bonito
        if(mouseIn(rRectBotaoCursor)) {
            rRectBotaoCursor.fill(this, corRespostavisual);
            setCursor(Cursor.HAND_CURSOR);
        } else if(mouseIn(rRectBotaoNode)) {
            rRectBotaoNode.fill(this, corRespostavisual);
            setCursor(Cursor.HAND_CURSOR);
        } else if(mouseIn(rRectBotaoLinha)) {
            rRectBotaoLinha.fill(this, corRespostavisual);
            setCursor(Cursor.HAND_CURSOR);
        } else {
            setCursor(Cursor.DEFAULT_CURSOR);
        }
       
        for(Node n : listaNode) {
            n.drawNode(this);
        }
    
    }
    
    private boolean mouseIn(RoundRectangle r) {
        
        double x = getMouseX();
        double y = getMouseY();
        
        return x >= r.x && x <= r.x + r.width &&
               y >= r.y && y <= r.y + r.height;
    }
    

    
    public static void main( String[] args ) {
        new JanelaPrincipal();
    }
    
}
