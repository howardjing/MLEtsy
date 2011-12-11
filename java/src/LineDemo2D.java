import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.Scrollbar;

import javax.swing.JApplet;
import javax.swing.JFrame;

public class LineDemo2D extends JApplet {

  public DrawError error;
  
  public LineDemo2D(DrawError error) {
      super();
      this.error = error;
  }
  public void init() {
    setBackground(Color.white);
    setForeground(Color.white);

  }


  public void paint(Graphics g) {
    Graphics2D g2 = (Graphics2D) g;
    
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
        RenderingHints.VALUE_ANTIALIAS_ON);

    
    //int x = 0;

    for (int i=0; i<error.result.size(); i++) {
        if (error.result.get(i) == 0) {
            g2.setPaint(Color.red);
        }
        else {
            g2.setPaint(Color.green);
        }
        int y = i%800;
        int x = i/800;
        g2.draw(new Line2D.Double(300*x, y, 300*x + 300, y));
    }

    

  }

  public static void main(String s[]) {
    JFrame f = new JFrame("ShapesDemo2D");
    f.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
    });
    DrawError error = new DrawError("output/4fold/results12");
    JApplet applet = new LineDemo2D(error);
    f.getContentPane().add("Center", applet);
    applet.init();
    f.pack();
    f.setSize(new Dimension(300, 300));
    f.setVisible(true);
  }
}