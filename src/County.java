/**
 * Created by ros_wgnapier on 10/5/2017.
 */
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.*;
import java.io.*;
import java.util.*;



public class County {
    String countyName;
    ArrayList<Point> boundaries;
    Point q;

    public County() {
        String name;
        ArrayList<Point> boundaries = new ArrayList();
    }

    /*
    public static void setLines(ArrayList<int[]> lines){
        lines.equals(lines);
    }
    */
    public void addBoundary(Point p){
        boundaries.add(p);
    }

    private static int max(int cord, int cord1) {
        if(cord > cord1){
            return cord;
        } else{
            return cord1;
        }
    }

    private static int min(int cord, int cord1) {
        if(cord < cord1){
            return cord;
        } else{
            return cord1;
        }
    }
}
