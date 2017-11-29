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

    public void setBoundaries(ArrayList<Point> boundaries) {
        this.boundaries = boundaries;
    }

    ArrayList<Point> boundaries = new ArrayList<>();
    Point q;

    public County(String SubregionName, ArrayList<Point> boundaries) {
        this.countyName = SubregionName;
        this.boundaries = boundaries;
    }

    /*
    public static void setLines(ArrayList<int[]> lines){
        lines.equals(lines);
    }
    */
    public void setCountyName(String subregionName){
        this.countyName = subregionName;
    }

    public void clearBoundaries(){boundaries.clear();}

    public void addBoundary(Point p){
        boundaries.add(p);
    }

    public boolean insideCounty(MouseEvent event){
        int max;
        int min;
        //for(int i : )
        for (int i = 0; i < boundaries.size(); i++) {
            if (event.getX()<= max(boundaries.get(i).x, boundaries.get(i+1).x) && event.getX() >= min(boundaries.get(i).x, boundaries.get(i+1).x) && event.getY() <= max(boundaries.get(i).y, boundaries.get(i+1).y) && event.getY() >= min(boundaries.get(i).y, boundaries.get(i+1).y)){
                return true;
            }
        }
        return false;
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
