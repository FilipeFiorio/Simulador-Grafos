package Simulador;

import br.com.davidbuzatto.jsge.core.engine.EngineFrame;
import java.awt.Color;

/**
 *
 * @author filipe
 */
public class Node {

    private double centroX;
    private double centroY;
    private final double RAIO_FIXO = 20;
    
    private int valor;
    
    private final Color corNode = new Color(2, 36, 66);
    private Color corContorno = EngineFrame.BLACK;
    
    public Node(double centroX, double centroY, int valor) {
        this.centroX = centroX;
        this.centroY = centroY;
        this.valor = valor;
    }
    
    public void setCorContorno(Color corContorno) {
        this.corContorno = corContorno;
    }
    
    public void setCentroX(double centroX) {
        this.centroX = centroX;
    }
    
    public void setCentroY(double centroY) {
        this.centroY = centroY;
    }
    
    public double getCentroX() {
        return centroX;
    }
    
    public double getCentroY() {
        return centroY;
    }
    
    public double getRaio() {
        return RAIO_FIXO;
    }
    
    public void drawNode(EngineFrame e) {
        e.fillCircle(centroX, centroY, RAIO_FIXO, corNode);
        e.drawCircle(centroX, centroY, RAIO_FIXO, corContorno);
    }
    
    public void drawNode(EngineFrame e, double raio, Color corPreenchimento, Color corContorno) {
        e.fillCircle(centroX, centroY, raio, corPreenchimento);
        e.fillCircle(centroX, centroY, raio, corContorno);
    }
    
}
