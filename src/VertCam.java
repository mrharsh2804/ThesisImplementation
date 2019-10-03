
import java.awt.Point;
import java.awt.geom.Point2D;

/**
 *
 * @author Harsh
 */
public class VertCam {

    Point.Double p1, p2, p3;
    int index;
    String ori = new String();
    Point.Double startPoint = new Point.Double(0, 0);

    public VertCam(Point.Double p1, Point.Double p2, String ori, int ind) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = calcP3();
        this.ori = ori;
        index = ind;
    }

    //start point of coverage
    public Point.Double getP1() {
        return p1;
    }

    //end point of coverage
    public Point.Double getP2() {
        return p2;
    }

    //position of sensor
    public Point.Double getP3() {
        return p3;
    }

    public String getOri() {
        return ori;
    }

    public double getVertCamCov() {
        double sigma = Algo.vAngles[index];
        double theta = Algo.THETA;
        double r = Algo.RANGE;
        if (ori.equals("bisector")) {
            return r * Math.sin(theta / 2) / Math.sin(sigma / 2);
        } else {
            double xdist = startPoint.distance(Algo.x[index], Algo.y[index]);
            double ydist = calcYDist(sigma, xdist);
            return xdist + ydist;
        }
    }

    public double getXCovAtVer() {
        double sigma = Algo.vAngles[index];
        double theta = Algo.THETA;
        double r = Algo.RANGE;
        if (ori.equals("bisector")) {
            return r * Math.sin(theta / 2) / Math.sin(sigma / 2);
        } else {
            double xdist = startPoint.distance(Algo.x[index], Algo.y[index]);
            return xdist;
        }
    }

    public double getYCovAtVer() {
        double sigma = Algo.vAngles[index];
        double theta = Algo.THETA;
        double r = Algo.RANGE;
        if (ori.equals("bisector")) {
            return r * Math.sin(theta / 2) / Math.sin(sigma / 2);
        } else {
            double xdist = startPoint.distance(Algo.x[index], Algo.y[index]);
            double ydist = calcYDist(sigma, xdist);
            return ydist;
        }
    }

    public double calcYDist(double sigma, double xdist) {
        double theta = Algo.THETA;
        double r = Algo.RANGE;
        double gamma = ((Math.PI - theta) / 2) - (Math.asin(r / Algo.distL));
        double alpha = (Math.PI + theta) / 2 - gamma;
        double beta = (Math.PI - theta) / 2;
        double bdist = 2 * r * Math.sin(theta / 2) * Math.sin(beta) / Math.asin(alpha);
        double b_prime = bdist - xdist;
        return b_prime * Math.sin(alpha) / Math.sin(sigma - alpha);
    }

    private Point2D.Double calcP3() {
        
        return null;
    }
}
