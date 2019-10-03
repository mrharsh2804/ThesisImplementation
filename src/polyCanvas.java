
import java.awt.Color;
import java.awt.*;
import java.awt.geom.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Harsh
 */
public class polyCanvas extends javax.swing.JApplet {

    public static double[] vAngles;
    public static double THETA = 60;
    public static double RANGE = 70;
    public static double distL = 20;
    public static double gamma = 0;
    // x coordinates of vertices 

    public static int x[] = {100, 300, 400, 300, 150, 50};
    // y coordinates of vertices 
    public static int y[] = {100, 100, 250, 500, 500, 300};

    /**
     * Initializes the applet polyCanvas
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
            java.util.logging.Logger.getLogger(polyCanvas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(polyCanvas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(polyCanvas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(polyCanvas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
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

    public void paint(Graphics g1d) {
        Graphics2D g = (Graphics2D) g1d;
        Shape poly = createPoly(x, y);
        g.draw(poly);
        
        for(int i = 0; i < x.length;i++)
        {
            g.draw(createCamAtVertex(i,g));
        }
    }
    
    private Shape createCamAtVertex(int i, Graphics2D g) {
        
        //create bisector of vertex
        Line2D.Double bisector = (Line2D.Double) createBisectorOf(i);
        g.draw(bisector);
         
        //find x_t,y_t: point of placement of camera at distance d_t
        Point2D.Double p1 = (Point2D.Double) bisector.getP1();
        Point2D.Double p2 = (Point2D.Double) bisector.getP2();
        double x1, x2, y1, y2;
        x1 = x[i];
        y1 = y[i];
        x2 = p2.getX();
        y2 = p2.getY();

        double d = Math.sqrt(Math.abs(Math.pow(x2 - x1, 2) + (Math.pow(y2 - y1, 2))));
        double d_t = distVerToCam(THETA, RANGE, vAngles[i]);
        double t = d_t / d;
        double x_t = ((1 - t) * x1 + t * x2);
        double y_t = ((1 - t) * y1 + t * y2);
        /* // Other way to find a point on a line at distance d_t
        double m = (y2-y1) / (x2-x1);
        double dx = (d_t / Math.sqrt(1 + (m * m))); 
        double dy = m * dx; 
        double x_t = x1 + dx; 
        double y_t = y1 + dy;
        */       
        
        //set k to make it i+1 safe
        int k;
        if(i+1 == x.length)
        {
            k = 0;
        }
        else
            k = i+1;
        
        //create camera at the point x_t,y_t
        double rot_angle_edge = getAngleOfLine(x[i], y[i], x[k], y[k]);
        double tar_ang = Math.toDegrees(rot_angle_edge) + (vAngles[i] / 2);

        Shape cam = createAngle(RANGE, THETA);
        //cam = rotateAndTranslateShape(cam,-(tar_ang-90),x[i],y[i]);
        cam = rotateAndTranslateShape(cam, -(tar_ang - 90), x_t, y_t);
        return cam;
    }
    
    private double distVerToCam(double theta, double r, double sigma) {
        //r => raange'
        theta = Math.toRadians(theta);
        sigma = Math.toRadians(sigma);
        double cov = coverageOnVertex(theta, r, sigma);
        double h = r * Math.cos(theta / 2);
        double dist = h - Math.sqrt(cov * cov - (r * r * Math.sin(theta / 2) * Math.sin(theta / 2)));

        return dist;
    }

    private double coverageOnVertex(double theta, double r, double sigma) {
        //r => range
        
        double cov = r * (Math.sin(theta / 2) / Math.sin(sigma / 2));
        return cov;
    }

    private Shape createBisectorOf(int i) {
        //set k to make it i+1 safe
        int k;
        if(i+1 == x.length)
        {
            k = 0;
        }
        else
            k = i+1;        
        
        double rot_angle_edge = getAngleOfLine(x[i], y[i], x[k], y[k]);
        double bisector = Math.toDegrees(rot_angle_edge) + vAngles[i] / 2;
        //return createLineAtAngle(x[i],y[i],bisector,100);
        double x1 = x[i];
        double y1 = y[i];
        bisector = Math.toRadians(bisector);
        double length = 100;
        double x2 = x1 + Math.cos(bisector) * length;
        double y2 = y1 + Math.sin(bisector) * length;

        double x3 = x1 - Math.cos(bisector) * length;
        double y3 = y1 - Math.sin(bisector) * length;

        Line2D.Double l = new Line2D.Double();
        l.setLine(x2, y2, x3, y3);

        return l;
    }

    private double getAngleOfLine(int x1, int y1, int x2, int y2) {
        double angle = Math.atan2(y2 - y1, x2 - x1);
        if (angle < 0) {
            angle += 2 * Math.PI;
        }
        return angle;
    }

    private Shape createPoly(int[] x, int[] y) {
        Polygon p = new Polygon(x, y, x.length);
        double a, b, c, a_2, b_2, c_2;
        double[] vAng = new double[x.length];

        //for first vertex angle
        int i = 0;
        int k = x.length - 1;
        c_2 = Math.abs((Math.pow(x[i + 1] - x[k], 2)) + (Math.pow(y[i + 1] - y[k], 2)));
        c = Math.sqrt(c_2);
        a_2 = Math.abs((Math.pow(x[i] - x[k], 2)) + (Math.pow(y[i] - y[k], 2)));
        a = Math.sqrt(a_2);
        b_2 = Math.abs((Math.pow(x[i + 1] - x[i], 2)) + (Math.pow(y[i + 1] - y[i], 2)));
        b = Math.sqrt(b_2);
        double temp = (a_2 + b_2 - c_2) / (2 * a * b);
        vAng[0] = Math.toDegrees(Math.acos(temp));

        for (i = 1; i < x.length - 1; i++) {
            c_2 = Math.abs((Math.pow(x[i + 1] - x[i - 1], 2)) + (Math.pow(y[i + 1] - y[i - 1], 2)));
            c = Math.sqrt(c_2);
            a_2 = Math.abs((Math.pow(x[i] - x[i - 1], 2)) + (Math.pow(y[i] - y[i - 1], 2)));
            a = Math.sqrt(a_2);
            b_2 = Math.abs((Math.pow(x[i + 1] - x[i], 2)) + (Math.pow(y[i + 1] - y[i], 2)));
            b = Math.sqrt(b_2);
            temp = (a_2 + b_2 - c_2) / (2 * a * b);
            vAng[i] = Math.toDegrees(Math.acos(temp));
        }

        //for last vertex angle
        i = x.length - 1;
        k = 0;
        c_2 = Math.abs((Math.pow(x[k] - x[i - 1], 2)) + (Math.pow(y[k] - y[i - 1], 2)));
        c = Math.sqrt(c_2);
        a_2 = Math.abs((Math.pow(x[i] - x[i - 1], 2)) + (Math.pow(y[i] - y[i - 1], 2)));
        a = Math.sqrt(a_2);
        b_2 = Math.abs((Math.pow(x[k] - x[i], 2)) + (Math.pow(y[k] - y[i], 2)));
        b = Math.sqrt(b_2);
        temp = (a_2 + b_2 - c_2) / (2 * a * b);
        vAng[i] = Math.toDegrees(Math.acos(temp));

        vAngles = vAng;

        return p;
    }

    //Rotate at rot_angle and translate the shape to x,y
    private Shape rotateAndTranslateShape(Shape s, double rot_angle, double x, double y) {
        AffineTransform at = AffineTransform.getRotateInstance(Math.toRadians(-rot_angle), 0, 0);
        s = at.createTransformedShape(s);
        at.setToTranslation(x, y);
        return at.createTransformedShape(s);
    }

    private Shape createAngle(double length, double angle) {
        GeneralPath g = new GeneralPath();
        /*//draw 1st line
        angle = Math.toRadians(angle);
        double x1 = x;
        double y1 = y;
        double x2 = x1 + Math.cos(Math.toRadians(90) - angle / 2) * length;
        double y2 = y1 + Math.sin(Math.toRadians(90) - angle / 2) * length;
        
        Line2D line = new Line2D.Double(x1,y1,x2,y2);
        //g.draw(line);
        g.lineTo(x1, y1);
        //to rotate the line
        AffineTransform at = AffineTransform.getRotateInstance(angle, line.getX1(), line.getY1());
        //draw rotated line
        //g.draw(at.createTransformedShape(line));
         */

        angle = angle * Math.PI / 180;
        double x1 = 0;
        double y1 = 0;
        double x2 = x1 + Math.cos(90 * Math.PI / 180 - angle / 2) * length;
        double y2 = y1 + Math.sin(90 * Math.PI / 180 - angle / 2) * length;
        g.moveTo(x1, y1);
        g.lineTo(x2, y2);
        g.moveTo(x1, y1);
        x2 = x1 + Math.cos((90 * Math.PI / 180 - angle / 2) + angle) * length;
        y2 = y1 + Math.sin((90 * Math.PI / 180 - angle / 2) + angle) * length;
        g.lineTo(x2, y2);

        return g;
    }

    private Shape createLineAtAngle(double x1, double y1, double angle, int length) {
        angle = Math.toRadians(angle);
        double x2 = x1 + Math.cos(angle) * length;
        double y2 = y1 + Math.sin(angle) * length;
        Line2D.Double l = new Line2D.Double();
        l.setLine(x1, y1, x2, y2);

        return l;
    }

    /**
     * This method is called from within the init() method to initialize the
     * form. WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setPreferredSize(new java.awt.Dimension(1500, 1500));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 330, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 141, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
