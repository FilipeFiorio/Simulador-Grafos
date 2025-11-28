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
    
    private int id;
    
    private Color corNode = new Color(2, 36, 66);
    
    public Node(double centroX, double centroY, int id) {
        this.centroX = centroX;
        this.centroY = centroY;
        this.id = id;
    }
    
    public void setCorNode(Color corNode) {
        this.corNode = corNode;
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
    
    public int getId() {
        return id;
    }
    
    public void drawNode(EngineFrame e) {
        e.fillCircle(centroX, centroY, RAIO_FIXO, corNode);
        e.drawCircle(centroX, centroY, RAIO_FIXO, e.BLACK);
        e.drawText(
                String.valueOf(id),
                (centroX - (e.measureText(String.valueOf(id), 18) / 2)),
                centroY - 3,
                18,
                e.WHITE
        );
    }
    
    public void drawNodeDesviado(EngineFrame e, double desvio) {
        e.fillCircle(centroX - desvio, centroY - desvio, RAIO_FIXO, corNode);
        e.fillCircle(centroX - desvio, centroY - desvio, RAIO_FIXO, e.BLACK);
        e.drawText(
                String.valueOf(id),
                (centroX - (e.measureText(String.valueOf(id), 18) / 2)),
                centroY - 3,
                18,
                e.WHITE
        );
    }
    
}
