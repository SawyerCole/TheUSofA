import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.*;

public class Outline {
    //Sets a scaling factor so the states can be scaled however the user chooses.
    private int scale = 20;
    private String stateName;
    private ArrayList <int[]> lines = new ArrayList();



    //Allows the user to input what they want to see (a state, the country or the country by county) and it will give the file of the chosen image to the next computer
    public void fileInput(String name) throws FileNotFoundException {
        Scanner file = new Scanner(new File("src/Data/StatesAndAbbreviations"));
        boolean correctRegion = false;
        while (file.hasNext() && !correctRegion) {
            String state = file.next();
            //This if statement checks file for states starting in "new" (ex. New York)
            if (state.equalsIgnoreCase("New")) {
                state += " " + file.next();
            }
            //Looks into file with all the state names to fine the name inputted
            if (name.equalsIgnoreCase(state)) {
                correctRegion = true;
                //If the input is any one of the strings below it will go straight to its correct state abbreviation/name, otherwise if it is an individual state it will just use that
                if (name.length() > 2 && !name.equalsIgnoreCase("USA") || name.equalsIgnoreCase("US") || name.equalsIgnoreCase("USA-County") || name.equalsIgnoreCase("USA-County2") || name.equalsIgnoreCase("USA-County3")) {
                    state = file.next();
                    this.stateName = state.toUpperCase();
                } else {
                    this.stateName = state.toUpperCase();
                }
            }
        }
    }

    //Draws the lines outlining the states and counties using the latitude and longitude lines from the file of the chosen state
    public void fileToDrawing() throws FileNotFoundException {
        ArrayList<Point> boundaries = new ArrayList<>();
        County county = new County("null", boundaries);
        setScale(scale);
        Scanner file = new Scanner(new File("src\\data\\" + stateName + ".txt"));
        double minLong = file.nextDouble();
        file.nextDouble();
        file.nextDouble();
        double maxLat = file.nextDouble();
        double lat1;
        double long1;
        double lat2;
        double long2;
        String subregionName;
        String nameOfState;
        int points;
        file.nextInt();
        Point p = new Point();
        int j = 0;
        //After the first 5 different variables everything repeats in the same manner so it will repeat this way with every subregion
        while (file.hasNext()) {
            j++;
            //Used to skip the gap between subregions
            file.nextLine();
            file.nextLine();
            subregionName = file.nextLine();
            //counties.add(subregionName);
            nameOfState = file.next();
            points = file.nextInt();
            //Added the scale so that the state/country would be better centered
            long1 = (file.nextDouble() - minLong) * scale + scale;
            lat1 = (maxLat - file.nextDouble()) * scale + scale;
            //Subtracted 1 because the first line is already dealt with above
            for (int i = 1; i <= points - 1; i++) {
                //I have these if statements to make sure that the decimal point is accounted for as turning a double into an int always rounds down
                if (long1 % 1 > 0.5) {
                    long1 = long1 / 1 + 1;
                }
                if (lat1 % 1 > 0.5) {
                    lat1 = lat1 / 1 + 1;
                }
                //Added the scale so the state would be better centered
                long2 = (file.nextDouble() - minLong) * scale + scale;
                lat2 = (maxLat - file.nextDouble()) * scale + scale;
                //I have these if statements to make sure that the decimal point is accounted for as turning a double into an int always rounds down
                if (long2 % 1 > 0.5) {
                    long2 = long2 / 1 + 1;
                }
                if (lat2 % 1 > 0.5) {
                    lat2 = lat2 / 1 + 1;
                }
                //Turned the different latitude and longitude lines into ints because drawings can't use doubles
                int[] line = new int[4];
                line[0] = (int) lat2;
                line[1] = (int) long2;
                line[2] = (int) lat1;
                line[3] = (int) long1;
                lines.add(line);
                long1 = long2;
                lat1 = lat2;
                p.x = (line[0]);
                p.y = line[1];
                boundaries.add(p);
            }
            county.setCountyName(subregionName);
            county.setBoundaries(boundaries);
            AllCounties.add(county);
        }
    }

    // Getters and Setters
    // Returns an array of an array of points for the lines to be drawn
    public ArrayList <int[]> getLines() {
        return lines;
    }
    // sets the scale
    public void setScale(int scale) { this.scale = NavPanel.getMapSize(); }
    //Clears the lines array so that it won't draw stuff that has already been drawn
    public void clearLines(){ lines.clear();}

    public String getStateName() {return stateName;}

    public ArrayList<County> getAllCounties() {
        return AllCounties;
    }

    ArrayList<County> AllCounties = new ArrayList<>();


    //public void clearCountries(){counties.clear();}

    //public ArrayList<String> getCounties() {return counties;}

}