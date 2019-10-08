
import java.awt.*;
import java.util.*;

/**
 *
 * @author Harsh
 */
public class CanPath {
    ArrayList<VertCam> cPath;
    int cDist;
    String cpName;
    double cpCovDist;
    int cpCamCount;

    
    public CanPath()
    {
        cPath = new ArrayList<>();
        cDist = 0;
        cpCovDist = 0;
        cpCamCount =0;
        //cpName = buildName();
    }
    
    private String buildName()
    {
        StringBuilder s = new StringBuilder();
        for(VertCam v:cPath)
        {
            s.append(v.index);
        }
        
        return s.toString();
    }
    
    public void addVertCam(VertCam v)
    {
        cPath.add(v);
        cDist++;
        cpCamCount++;
    }
    
    public Point.Double getEndPoint()
    {
        return cPath.get(cPath.size()-1).getP2();
    }
    
    public Point.Double getStartPoint()
    {
        return cPath.get(0).getP1();
    }
    
    public double getCovDist(CanPath c)
    {
        double cov =0;
        int k = 0;
        for(int i=0; i<cPath.size();i++)
        {
            VertCam v = cPath.get(i);
            if(i > 0)
            {
                //add length of the edge to coverage
                Point.Double p1 = cPath.get(i-1).getP2();
                Point.Double p2 = v.getP1();
                cov += p1.distance(p2);
            }
            /*if(v.getOri().equals("bisector"))
            {
                cov += 2*v.getXCovAtVer();
            }
            else
            {
                cov+= v.getXCovAtVer();
                cov+= v.getYCovAtVer();
            }
            */
            cov+= v.getVertCamCov();
            
        }
                
        return cov;
    }  
    
    public ArrayList<VertCam> getcPath()
    {
        return cPath;
    }
    
    public double getcpCovDist()
    {
        return cpCovDist;
    }
    
    public void setcpCovDist(double dist)
    {
        cpCovDist = dist;
    }
    
    public double getcpCamCount()
    {
        return cpCamCount;
    }
}
