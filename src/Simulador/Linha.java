package Simulador;

import br.com.davidbuzatto.jsge.core.engine.EngineFrame;
import java.awt.Color;

/**
 *
 * @author filipe
 */
public class Linha {

   private double xIni;
   private double xFim;
   
   private double yIni;
   private double yFim;
   
   private Color corLinha = EngineFrame.BLACK;
   
   public Linha(double xIni, double yIni, double xFim, double yFim) {
       this.xIni = xIni;
       this.yIni = yIni;
       this.xFim = xFim;
       this.yFim = yFim;
   }
   
   public void drawLine(EngineFrame e) {
       e.drawLine(xIni, yIni, xFim, yFim, corLinha);
   }
   
   public void drawLine(EngineFrame e, Color corLinha) {
       e.drawLine(xIni, yIni, xFim, yFim, corLinha);
       //gambiarra pra deixar a linha mais grossa e nao criar um retangulo
       e.drawLine(xIni, yIni + 1, xFim, yFim + 1, corLinha);
   }
}
