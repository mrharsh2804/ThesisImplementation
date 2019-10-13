
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
        int y[] = {100, 100, 250, 510, 500, 300};
        
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
        
//        CanPath c = new Algo().optimalPath();
//        for(VertCam v: c.getcPath())
//        {
//            putMarker(v.getP1().getX(), v.getP1().getY());
//            putMarker(v.getP2().getX(), v.getP2().getY());
//        }
//        
        
g.setColor(Color.green);
putMarker(181.0899572263979,502.07266381509316);
putMarker(130.40726592276454,460.8145318455291);
putMarker(74.66840692820963,349.33681385641927);
putMarker(51.89876330041979,292.40494679832085);
putMarker(89.23028633545387,143.07885465818447);
putMarker(144.4046669965815,100.0);
putMarker(269.0405446966818,100.0);
putMarker(323.1034476150537,134.65517142258057);
putMarker(392.238993432003,238.3584901480045);
putMarker(382.43337592133463,295.67322260452994);
putMarker(315.3208669828824,470.16574584450575);
putMarker(262.5771201258231,507.5051413417215);

//
//g.setColor(Color.blue);
//putMarker(224.63672513758564,500.0);
//putMarker(158.34374319549096,516.687486390982);
//putMarker(74.73545470365865,349.4709094073173);
//putMarker(51.85899230454253,292.5640307818299);
//putMarker(97.20195305446121,111.19218778215516);
//putMarker(207.2175807247574,100.0);
//putMarker(269.53551957480755,100.0);
//putMarker(323.46812169600867,135.202182544013);
//putMarker(392.603667512958,238.90550126943697);
//putMarker(376.1513713590437,309.6215716023907);
//putMarker(306.7184669835023,483.20383254124425);
//putMarker(225.3611015189942,500.0);
//        
g.setColor(Color.red);
putMarker(187.4281483426107,502.4952098895074);
putMarker(134.40156196397018,468.8031239279404);
putMarker(50.79327347213786,301.5865469442757);
putMarker(66.06087125838255,235.75651496646978);
putMarker(89.23028633545387,143.07885465818447);
putMarker(144.4046669965815,100.0);
putMarker(269.0405446966818,100.0);
putMarker(323.1034476150537,134.65517142258057);
putMarker(392.238993432003,238.3584901480045);
putMarker(382.43337592133463,295.67322260452994);
putMarker(315.3208669828824,470.16574584450575);
putMarker(262.5771201258231,507.5051413417215);
//
//    Point.Double p1 = new Point.Double(88.56718500428515,145.73125998285934);
//    Point.Double p2 = new Point.Double(61.43423459125005,254.26306163499981);
//    
//    Point.Double v1 = new Point.Double(x[0], y[0]);
//    Point.Double v2 = new Point.Double(x[5], y[5]);
//    
//    double d1 = p1.distance(v1);
//    double d2 = p2.distance(v2);
//    System.out.println(d1 +" : "+d2);

        
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