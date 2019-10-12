
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
        
        BinaryPathSearch b = new BinaryPathSearch(4,5);
        b.createCP(4,5);
    }

    int vInd, cpDist; //start vertex
    Point.Double px1, py1, px2, py2;
    double y1, y2, x1, x2;
    double alpha;//  values in radians
    Point.Double next_px1;
    int dec = 1;
    double prec = Math.pow(10, dec);
    double cpCovDist = 0;
    int n = Algo.x.length;

    public BinaryPathSearch(int vInd, int cpDist) {
        Algo a = new Algo();
        this.vInd = vInd;
        this.cpDist = cpDist;
        //this.y1 = 0;
        alpha = Algo.alpha;
        this.x1 = initX1_px1(vInd); //set initial x1 and px1
        y1 = calcY1(vInd); // sets y1 and py1        
        y2 = -1;
    }

    private double calcY1(int v) {
        //calc x
        
        double x = Algo.vertex[v].distance(px1);
        double y = (Algo.distB - x) * Math.sin(alpha) / Math.sin(Algo.vAngles[v] - alpha);
        
        //System.out.println("y1 = "+ y);
        py1 = Algo.pointAtDist(Algo.vertex[v], Algo.vertex[(v+1)%n], y);
        return y;
    }

    private double calcX1(Point.Double py) {
        double y = Algo.vertex[vInd].distance(py);
        x1 = Algo.distB - (y * Math.sin(Algo.vAngles[vInd] - alpha) / Math.sin(alpha));
        px1 = Algo.pointAtDist(Algo.vertex[vInd], Algo.vertex[(vInd + 1)%n], x1);
        return x1;
    }

    private double initX1_px1(int vInd) {
        px1 = Algo.pointAtDist(Algo.vertex[vInd], Algo.vertex[vInd - 1==-1?n-1:vInd-1], Algo.distB/2);
        return Algo.distB/2;//181.0899572263979, 502.07266381509316
    }

    private double calcNextX1(int v) //sets px1 and returns x1
    {
        double distToNextV = py1.distance(Algo.vertex[(v+1)%n]); //py1 to v+1
        double remDist = distToNextV - Math.floor(distToNextV / Algo.distB) * Algo.distB; // total - dist covered by complete cams
        double li = distToNextV - remDist; // distance in between
        cpCovDist += li;
        next_px1 = Algo.pointAtDist(py1, Algo.vertex[(v+1)%n], li); // point on edge at li
        px1 = next_px1; //update current px1
        return remDist;
    }

    private Point.Double pointAtAngleLen(Point.Double p, double angle, double len) {
        double x = p.getX() + Math.cos(angle) * len;
        double y = p.getY() - Math.sin(angle) * len;

        return new Point.Double(x, y);
    }

    public CanPath createCP(int vInd, int cpDist) {
        CanPath c = new CanPath();
        BinaryPathSearch b = new BinaryPathSearch(vInd, cpDist);
        double tempX1 = x1;
        int count = 0;
        
        while(Math.round(tempX1 * prec) / prec !=  Math.round(y2 * prec) / prec)
        {        
//            if(count ==8790){
//                count++;
//                count--;
//            }
            //System.out.printf("%."+dec+"f : ",tempX1);
            //System.out.printf("%."+dec+"f : %."+dec+"f\n",tempX1,y2);
            c = new CanPath();
            tempX1 = x1;
            //add first VertCam
            VertCam v = new VertCam(px1, py1, "gamma", vInd);
            
            if(-1 == Algo.vertex.length-1)
            {
            System.out.println("("+px1.getX()+","+px1.getY()+");");
            System.out.println("("+py1.getX()+","+py1.getY()+");");
            }
            c.addVertCam(v);
            
            cpCovDist = x1+y1;
            int k = 0;
            for (int i = vInd; i < vInd + cpDist; i++) {
                k = i%n;
                x1 = calcNextX1(k); //sets x1 and px1 for vertex[vInd+1]
                y1 = calcY1((k+1)%n); //sets y1 and py1 for vertex[vInd+1]
                //add VertCam for vInd+1
                cpCovDist += x1+y1;
                if(-1 == Algo.vertex.length-1)
                {
                    System.out.println("("+px1.getX()+","+px1.getY()+");");
                    System.out.println("("+py1.getX()+","+py1.getY()+");");
                }
                v = new VertCam(px1, py1, "", (i+1)%n);
                c.addVertCam(v);
            }
            //System.out.printf("%."+dec+"f : %."+dec+"f\n",y1, cpCovDist);
            y2 = y1;
            
            if(Math.round(tempX1 * prec) / prec !=  Math.round(y2 * prec) / prec)
            {
                if(tempX1 < y2)
                {
                    x1 = tempX1 + tempX1/2;
                    px1 = Algo.pointAtDist(Algo.vertex[vInd], Algo.vertex[vInd - 1==-1?n-1:vInd-1], x1);
                    y1 = calcY1(vInd);
                }
                else if(tempX1 > y2)
                {
                    x1 = tempX1 - tempX1/2;
                    px1 = Algo.pointAtDist(Algo.vertex[vInd], Algo.vertex[vInd - 1== -1?n-1:vInd-1], x1);
                    y1 = calcY1(vInd);
                }
            }
            
            //System.out.printf("%."+dec+"f : %."+dec+"f\n",tempX1,y2);
            count++;
        }
        
        
        //calc total cp distance
        cpCovDist = tempX1+y2;
        
        //display the path
        System.out.print("------------");
        for(int i = vInd; i < vInd+cpDist; i++)
        {
            System.out.print(i%n+", ");
            cpCovDist += Algo.vertex[i%n].distance(Algo.vertex[(i+1)%n]);
        }
        System.out.println((vInd+cpDist)%n+"-----------");
        
        
        
        System.out.printf("%."+dec+"f : %."+dec+"f : %."+dec+"f\n",tempX1,y2, cpCovDist);
        
        //System.out.println(count);
        c.setcpCovDist(cpCovDist);
        return c;
    }

}
