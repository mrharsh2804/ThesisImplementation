
import java.awt.*;
import java.util.*;

/**
 *
 * 
 * 
 * @author Harsh
 */
public class Algo {
    public static double[] vAngles; //in degrees
    public static double THETA = Math.toRadians(60);
    public static double RANGE = 70;
    public static double distL = 20;
    public static double gamma = 0;
    public static double alpha = Algo.THETA + Math.asin(Algo.distL/Algo.RANGE);
    public static double distA = 2 * Algo.RANGE * Math.sin(THETA/2) ;//sensor coverage when placed verticle.
    public static double beta = (Math.PI - THETA) / 2;
    public static double distB = distA * Math.sin(beta)/Math.sin(alpha); //create method for coverage on straight line
    // x coordinates of vertices 
    public static int x[] = {100, 300, 400, 300, 150, 50};
    // y coordinates of vertices
    public static int y[] = {100, 100, 250, 500, 500, 300};
    
    public static Point.Double[] vertex = new Point.Double[x.length];
    public static final double[] polyLength = new double[vertex.length];
    
    String[][] cp = new String[vertex.length][vertex.length];
    HashMap<String, CanPath> cpMap = new HashMap<>();
    
    Algo()
    {
        for(int i =0; i < vertex.length; i++)
        {
            vertex[i] = new Point.Double(x[i],y[i]);
        }
        genVertAngles();
        genPolyLength();
    }
    
    private void genVertAngles()
    {
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
        vAng[0] = Math.acos(temp);

        for (i = 1; i < x.length - 1; i++) {
            c_2 = Math.abs((Math.pow(x[i + 1] - x[i - 1], 2)) + (Math.pow(y[i + 1] - y[i - 1], 2)));
            c = Math.sqrt(c_2);
            a_2 = Math.abs((Math.pow(x[i] - x[i - 1], 2)) + (Math.pow(y[i] - y[i - 1], 2)));
            a = Math.sqrt(a_2);
            b_2 = Math.abs((Math.pow(x[i + 1] - x[i], 2)) + (Math.pow(y[i + 1] - y[i], 2)));
            b = Math.sqrt(b_2);
            temp = (a_2 + b_2 - c_2) / (2 * a * b);
            vAng[i] = Math.acos(temp);
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
        vAng[i] = Math.acos(temp);

        vAngles = vAng;
    }
    
    private void genPolyLength()
    {
        Point.Double p;
        for(int i = 1; i < vertex.length; i++)
        {
            p = vertex[i-1];
            polyLength[i-1] = p.distance(vertex[i]);
        }
        p = vertex[vertex.length-1];
        polyLength[vertex.length-1] = p.distance(new Point.Double(vertex[0].getX(), vertex[0].getY()));
    }
    
    public void optimalPath()
    {
//        PathComb p = new PathComb();
//        for(int i = 0 ; i < vertex.length-1; i++) // for can path
//        {
//            for(int j = 0; j < vertex.length-1; j++)//for vertices
//            {
//                int[] verColc = generateArray(i,j);
//                ArrayList<ArrayList<ArrayList<Integer>>> c = p.getPaths(verColc);
//                CanPath[] cpCombo = createCPCombo(c);
//                CanPath cp = checkForBestCP(cpCombo);
//                String sCP = parseGetPathsOP(cp);
//                map.put(cp, dist);
//            }
//        }
    }
    
    public CanPath createCanPath(CanPath[] cp)
    {
        CanPath cNew = new CanPath();
        for(CanPath c : cp)
        {
            c.cPath.stream().forEach((v) -> {
                cNew.addVertCam(v);
            });
        }        
        return cNew;
    }
    
    //by considering individual vertices
    public CanPath[] createCanPath1(int startInd, int dist)
    {
        //create cam at bisector for each vertex and create at gamma at other vertices
        //while doing that check how much distance is not covered before next vertex and then see whether
        //you need cam at bisector or at gamma
        
        //for easier logic start with covering an edge. note to self
        
        CanPath[] cp = new CanPath[dist+1];
        
        for(int k=startInd; k <= startInd+dist; k++)
        {
            CanPath c = new CanPath();
            
            VertCam v = createCamAt(k,"bisector");
            //c.addVertCam(v);
            //for backwards - k-1, k-2, k-3, ... start index
            for(int i = k-1; i >=startInd; k--)
            {
                double distX = v.getXCovAtVer();
                
                double rem = polyLength[i-1] - distX;
                double camNo = Math.floor(rem/distB);
                double li = camNo*distB;
                
                Point.Double endOf_li = pointAtDist(v.getP1(), vertex[i-1], li);
                double toBeCov = endOf_li.distance(vertex[i-1]);
                
                double yDist = RANGE * Math.sin(THETA / 2) / Math.sin(vAngles[i+1]/ 2);
                VertCam vNext;
                if(toBeCov == yDist)
                {
                    vNext = createCamAt(i-1, "bisector");
                }
                else
                {
                    //find the point on other side of the vertex (so that the createCamAt(gamma) remains consistent)
                    vNext = createCamAt(i-1, "gamma", endOf_li);
                }
                v = vNext;
                c.addVertCam(v);
            }
            
            v = createCamAt(k,"bisector");
            c.addVertCam(v);
            
            for(int i=k+1; i <= startInd+dist; i++)
            {
                //get distance covered on the edge
                double distY = v.getYCovAtVer();

                //get li
                double rem = polyLength[i]-distY;
                double camNo = Math.floor(rem/distB);
                double li = camNo * distB;

                //get distance to be covered
                Point.Double endOf_li = pointAtDist(v.getP2(), vertex[i+1], li);
                double toBeCov = endOf_li.distance(vertex[i+1]);

                //check for bisector or gamma
                double xDist = RANGE * Math.sin(THETA / 2) / Math.sin(vAngles[i+1]/ 2);
                VertCam vNext;
                if(toBeCov == xDist)
                {
                    vNext = createCamAt(i+1,"bisector");
                }
                else
                {
                    vNext = createCamAt(i+1,"gamma", endOf_li);
                }                
                v = vNext;
                c.addVertCam(v);
            }
            cp[k-startInd] = c;
        }
        
        return cp;
        
    }
    
    public CanPath createCanPath(int startInd, int dist) //for can dist of k. no combo
    {
        //use BinaryPathSearch.java for this
        BinaryPathSearch b = new BinaryPathSearch(startInd, dist);
        return b.createCP(startInd, dist);
    }
    
    //to be used for bisector vert cam
    public VertCam createCamAt(int vInd, String ori)
    {
        VertCam v;
        
        if(ori.equals("bisector"))
        {
            
        }
        
        return null;
    }
    
    //to be used for gamma ori vert cam
    public VertCam createCamAt(int vInd, String ori, Point.Double p)
    {
        VertCam v;
        
        return null;
    }
    
    public static Point.Double pointAtDist(Point.Double p1, Point.Double p2, double d_t)
    {
        double x1, x2, y1, y2;
        x1 = p1.getX();
        y1 = p1.getY();
        x2 = p2.getX();
        y2 = p2.getY();

        double d = p1.distance(p2);
        double t = d_t / d;
        double x_t = ((1 - t) * x1 + t * x2);
        double y_t = ((1 - t) * y1 + t * y2);
        
        return  new Point.Double(x_t,y_t);
    }

    private int[] generateArray(int i, int j) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private String parseGetPathsOP(CanPath c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private CanPath checkForBestCP(ArrayList<ArrayList<ArrayList<Integer>>> cpCombo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private CanPath[] createCPCombo(ArrayList<ArrayList<ArrayList<Integer>>> c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}


