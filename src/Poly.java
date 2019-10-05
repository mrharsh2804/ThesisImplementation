
import java.awt.*;
import java.applet.*;
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
        putMarker(84,160);
        putMarker(100,100);
        putMarker(286,100);
        putMarker(336,154);
        putMarker(370,206);
        putMarker(395,261);
        putMarker(302,493);
        putMarker(206,500);
        putMarker(206,500);
        putMarker(146,492);
        putMarker(62,325);
        putMarker(58,264);
g.setColor(Color.blue);
putMarker(92,130);
putMarker(165,100);
putMarker(289,100);
putMarker(338,157);
putMarker(373,209);
putMarker(393,266);
putMarker(300,498);
putMarker(198,500);
putMarker(198,500);
putMarker(140,481);
putMarker(56,313);
putMarker(62,250);
        
g.setColor(Color.red);

putMarker(88,145);
putMarker(132,100);
putMarker(256,100);
putMarker(314,121);
putMarker(383,224);
putMarker(384,288);
putMarker(315,462);
putMarker(263,500);
putMarker(200,500);
putMarker(142,484);
putMarker(58,317);
putMarker(61,254);

    Point.Double p1 = new Point.Double(88,145);
    Point.Double p2 = new Point.Double(61.43423459125005,254.26306163499981);
    
    Point.Double v1 = new Point.Double(x[0], y[0]);
    Point.Double v2 = new Point.Double(x[5], y[5]);
    
    double d1 = p1.distance(v1);
    double d2 = p2.distance(v2);
    System.out.println(d1 +" : "+d2);
        
    }
    
    public void putMarker(int x, int y)
    {
        g.drawLine(x-5,y,x+5,y);
        g.drawLine(x,y-5,x,y+5);
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