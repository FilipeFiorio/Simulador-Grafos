package Simulador;

import br.com.davidbuzatto.jsge.core.engine.EngineFrame;
import java.awt.Color;

/**
 *
 * @author filipe
 */
public class Aresta {

    private Node node1;
    private Node node2;
   
   private Color corLinha = EngineFrame.BLACK;
   
   public Aresta(Node node1, Node node2) {
       this.node1 = node1;
       this.node2 = node2;
   }
   
   public Node getNode1() {
       return node1;
   }
   
   public Node getNode2() {
       return node2;
   }
   
   public void setCorLinha(Color corLinha) {
       this.corLinha = corLinha;
   }
   
   public void drawLine(EngineFrame e) {
       e.drawLine(node1.getCentroX(), node1.getCentroY(), node2.getCentroX(), node2.getCentroY(), corLinha);
   }

}
