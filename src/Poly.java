
import java.awt.*;
import java.applet.*;
import java.awt.geom.Line2D;
import java.util.*;
import java.util.stream.*;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Harsh
 */
public class Poly extends javax.swing.JApplet {

    /**
     * Initializes the applet Polygon
     */
    @Override
    public void init() {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Poly.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Poly.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Poly.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Poly.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the applet */
        try {
            java.awt.EventQueue.invokeAndWait(new Runnable() {
                public void run() {
                    initComponents();
                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    Graphics2D g;
    public void paint(Graphics g1d) 
    { 
        // x coordinates of vertices 
        g = (Graphics2D) g1d;
        int x[] = {100, 300, 400, 300, 150, 50};
    // y coordinates of vertices
        int y[] = {100, 100, 250, 500, 500, 300};
        
//        // x coordinates of vertices 
//
//    public static int x[] = {100, 300, 400, 300, 150, 50};
//    // y coordinates of vertices 
//    public static int y[] = {100, 100, 250, 500, 500, 300};
  
        // number of vertices 
        int numberofpoints = x.length; 
        
        // create a polygon with given x, y coordinates 
        //Polygon p = new Polygon(x, y, numberofpoints); 
  
        // set the color of line drawn to blue 
        g.setColor(Color.BLUE); 
  
        // draw the polygon using drawPolygon 
        // function using object of polygon class 
          
        g.drawPolygon(x, y, numberofpoints); 
        
        
        
                
        double startx = 50 , starty = 50, length = 50, angle =45*Math.PI/180;
        double x1 = startx;
        double y1 = starty;
        double x2 = x1 + Math.cos(angle) * length;
        double y2 = y1 + Math.sin(angle) * length;
        
        //g.drawLine((int)x1,(int)y1,(int)x2,(int)y2);        
        
        
        Polygon p = new Polygon(x,y,numberofpoints);
g.setColor(Color.green);
putMarker(84.88567975002711,160.45728099989157);
putMarker(100.0,100.0);
putMarker(286.9538165501505,100.0);
putMarker(336.30109912980504,154.4516486947075);
putMarker(370.8688720382797,206.30330805741949);
putMarker(395.2282056314687,261.9294859213282);
putMarker(302.6509997974135,493.3725005064663);
putMarker(206.87855374711955,500.0);
putMarker(206.87855374711955,500.0);
putMarker(146.315803247098,492.6316064941959);
putMarker(62.70751475526562,325.4150295105312);
putMarker(58.99365332641487,264.02538669434057);
g.setColor(Color.blue);
putMarker(92.3781233361901,130.48750665523957);
putMarker(165.2244112458848,100.0);
putMarker(289.86028894598513,100.0);
putMarker(338.4424505329012,157.66367579935184);
putMarker(373.01022344137584,209.51533516206382);
putMarker(393.3487213785121,266.62819655371976);
putMarker(300.77151554445686,498.07121113885785);
putMarker(198.3381879857033,500.0);
putMarker(198.3381879857033,500.0);
putMarker(140.5312545873877,481.06250917477536);
putMarker(56.922966095555346,313.84593219111065);
putMarker(62.424897082921376,250.30041166831452);
        
g.setColor(Color.red);

putMarker(88.56718500428515,145.73125998285934);
putMarker(132.048824918892,100.0);
putMarker(256.68470261899233,100.0);
putMarker(314.0002475270659,121.00037129059882);
putMarker(383.13579334401516,224.7036900160228);
putMarker(384.46141350521987,288.84646623695033);
putMarker(315.02850912967847,462.4287271758039);
putMarker(263.12188579238415,500.0);
putMarker(200.803946942334,500.0);
putMarker(142.20135901455234,484.4027180291047);
putMarker(58.593070522719984,317.18614104543997);
putMarker(61.43423459125005,254.26306163499981);

    Point.Double p1 = new Point.Double(88.56718500428515,145.73125998285934);
    Point.Double p2 = new Point.Double(61.43423459125005,254.26306163499981);
    
    Point.Double v1 = new Point.Double(x[0], y[0]);
    Point.Double v2 = new Point.Double(x[5], y[5]);
    
    double d1 = p1.distance(v1);
    double d2 = p2.distance(v2);
    System.out.println(d1 +" : "+d2);
        
    }
    
    public void putMarker(double x, double y)
    {
        
        g.draw(new Line2D.Double(x-5,y,x+5,y));
        g.draw(new Line2D.Double(x,y-5,x,y+5));
    }
    public Graphics2D getGraph()
    {
        return g;
    }
    
    
    
    /**
     * This method is called from within the init() method to initialize the
     * form. WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}