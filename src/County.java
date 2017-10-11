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
    public boolean insideCounty(){
        int max;
        int min;
        for (int i = 0; i < boundaries.size(); i++) {
            if (MouseEvent. <= max(boundaries.get(i).x, boundaries.get(i+1).x) && MouseEvent.getXOnScreen() >= min(boundaries.get(i).x, boundaries.get(i+1).x) && MouseEvent.getYOnScreen() <= max(boundaries.get(i).y, boundaries.get(i+1).y) && MouseEvent.getYOnScreen() >= min(boundaries.get(i).y, boundaries.get(i+1).y)){
                return true;
            } else {
                return false;
            }
        }
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
