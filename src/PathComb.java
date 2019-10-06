
import static java.lang.System.in;
import java.util.ArrayList;

public class PathComb {

//    public static void main(String[] args) {
//        // Demo for 4 vertices:
//
//        int[] input = generateArray(4,5);//new int[]{4, 5, 0, 1};
//        ArrayList<ArrayList<ArrayList<Integer>>> results = getPaths(input);
//// Output results:
//        results.forEach((n) -> System.out.println(n));
//    }
    
    private static int[] generateArray(int i, int j) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        int[] col = new int[j+1];
        for(int k = 0; k <= j; k++)
        {
            col[k] = (i+k)%Algo.x.length;
        }        
        return col;
    }

    public static ArrayList<ArrayList<ArrayList<Integer>>> getPaths(int[] vertices) {
        int pathCount = 1 << (vertices.length - 1);
        ArrayList<ArrayList<ArrayList<Integer>>> paths = new ArrayList<>();
        for (int i = 0; i < pathCount; i++) {
            ArrayList<Integer> group = new ArrayList<>();
            group.add(vertices[0]);
            ArrayList<ArrayList<Integer>> path = new ArrayList<>();
            path.add(group); // Path has at least one group
            for (int j = 1, bitPattern = i; j < vertices.length; j++, bitPattern >>= 1) {
                if ((bitPattern & 1) == 1) { // bit is set, so add vertex to existing group
                    group.add(vertices[j]);

                } else { // put vertex in a new group
                    group = new ArrayList<>();
                    group.add(vertices[j]);

                    path.add(group);
                }
            }

            paths.add(path);
        }
        return paths;
    }
}
