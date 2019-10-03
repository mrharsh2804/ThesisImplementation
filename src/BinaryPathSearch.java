
import java.awt.*;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Harsh
 */
public class BinaryPathSearch {

    public static void main(String[] args) {
        // Demo for 4 vertices:
        BinaryPathSearch b = new BinaryPathSearch(1, 3);
        b.createCP(1,3);
    }

    int vInd, cpDist; //start vertex
    Point.Double px1, py1, px2, py2;
    double y1, y2, x1, x2;
    double alpha;//  values in radians
    Point.Double next_px1;
    int dec = 3;
    double prec = Math.pow(10, dec);
    double cpCovDist = 0;

    public BinaryPathSearch(int vInd, int cpDist) {
        new Algo();
        this.vInd = vInd;
        this.cpDist = cpDist;
        this.y1 = 0;
        this.x1 = initX1_px1(vInd); //set initial x1 and px1
        y1 = calcY1(vInd); // sets y1 and py1        
        alpha = Algo.alpha;
    }

    private double calcY1(int v) {
        //calc x
        double x = Algo.vertex[v].distance(px1);
        double y = (Algo.distB - x) * Math.sin(alpha) / Math.sin(Algo.vAngles[v] - alpha);
        //System.out.println("y1 = "+ y);
        py1 = Algo.pointAtDist(Algo.vertex[v], Algo.vertex[v + 1], y);
        return y;
    }

    private double calcX1(Point.Double py) {
        double y = Algo.vertex[vInd].distance(py);
        x1 = Algo.distB - (y * Math.sin(Algo.vAngles[vInd] - alpha) / Math.sin(alpha));
        px1 = Algo.pointAtDist(Algo.vertex[vInd], Algo.vertex[vInd + 1], x1);
        return x1;
    }

    private double initX1_px1(int vInd) {
        px1 = Algo.pointAtDist(Algo.vertex[vInd], Algo.vertex[vInd - 1], Algo.distB);
        return Algo.distB;
    }

    private double calcNextX1(int v) //sets px1 and returns x1
    {
        double distToNextV = py1.distance(Algo.vertex[v + 1]); //py1 to v+1
        double remDist = distToNextV - Math.floor(distToNextV / Algo.distB) * Algo.distB; // total - dist covered by complete cams
        double li = distToNextV - remDist; // distance in between
        cpDist += li;
        next_px1 = Algo.pointAtDist(py1, Algo.vertex[v + 1], li); // point on edge at li
        px1 = next_px1; //update current px1
        return remDist;
    }

    private Point.Double pointAtAngleLen(Point.Double p, double angle, double len) {
        double x = p.getX() + Math.cos(angle) * len;
        double y = p.getY() - Math.sin(angle) * len;

        return new Point.Double(x, y);
    }

    public CanPath createCP(int vInd, int cpDist) {
        double tempX1;
        CanPath c = new CanPath();
        BinaryPathSearch b = new BinaryPathSearch(vInd, cpDist);
        tempX1 = x1;
        while(Math.round(tempX1 * prec) / prec !=  Math.round(y1 * prec) / prec)
        {            
            c = new CanPath();
            //add first VertCam
            VertCam v = new VertCam(px1, py1, "gamma", vInd);
            c.addVertCam(v);
            cpCovDist = x1+y1;
            for (int i = vInd; i < vInd + cpDist; i++) {
                x1 = calcNextX1(i); //sets x1 and px1 for vertex[vInd+1]7658345885
                y1 = calcY1(i+1); //sets y1 and py1 for vertex[vInd+1]
                //add VertCam for vInd+1
                cpCovDist += x1+y1;
                v = new VertCam(px1, py1, "", i);
                c.addVertCam(v);
            }
            System.out.printf("%."+dec+"f : %."+dec+"f : %."+dec+"f\n",tempX1,y1, cpCovDist);
            if( Math.round(tempX1 * prec) / prec !=  Math.round(y1 * prec) / prec)
            {
                if(tempX1 < y1)
                {
                    tempX1 = tempX1 + tempX1/2;
                    px1 = Algo.pointAtDist(Algo.vertex[vInd], Algo.vertex[vInd - 1], tempX1);
                }
                else if(tempX1 > y1)
                {
                    tempX1 = tempX1 - tempX1/2;
                    px1 = Algo.pointAtDist(Algo.vertex[vInd], Algo.vertex[vInd - 1], tempX1);
                }
            }
            
            //System.out.printf("%."+dec+"f : %."+dec+"f\n",tempX1,y1);
            
        }
        System.out.printf("%."+dec+"f : %."+dec+"f\n",tempX1,y1);
        
        return c;
    }

}
