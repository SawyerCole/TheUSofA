import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Outline {
    //Sets a scaling factor so the states can be scaled however the user chooses.
    private int scale = 20;
    private String stateName;
    private ArrayList <int[]> lines = new ArrayList();


//    Outline() {}


    //Allows the user to input what they want to see (a state, the country or the country by county) and it will give the file of the chosen image to the next computer
    public void fileInput(String name) throws FileNotFoundException {
        Scanner file = new Scanner(new File("src/Data/StatesAndAbbreviations"));
        boolean correctRegion = false;
        while (file.hasNext() && !correctRegion) {
            String state = file.next();
            if (state.equalsIgnoreCase("New")) {
                state += " " + file.next();
            }
            if (name.equalsIgnoreCase(state)) {
                correctRegion = true;
                if (name.length() > 2 && !name.equalsIgnoreCase("USA") || name.equalsIgnoreCase("US")) {
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
        lines = new ArrayList<>();
        Scanner file = new Scanner(new File("src\\data\\" + stateName + ".txt"));
        double minLong = file.nextDouble();
        file.nextDouble();
        file.nextDouble();
        double maxLat = file.nextDouble();
        double lat1;
        double long1;
        double lat2;
        double long2;
        file.nextInt();
        //After the first 5 different variables everything repeats in the same manner so it will repeat this way with every subregion
        while (file.hasNext()) {

            //Used to skip the gap between subregions
            file.next();
            file.nextLine();
            file.next();
            int points = file.nextInt();
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
            }
        }

    }

//    //Used to see if the user wants to continue or not
//    public boolean toContinue() {
//        Scanner files = new Scanner(System.in);
//        System.out.print("Would you like to view somewhere new?");
//        String viewNew = files.next();
//        System.out.println();
//        boolean response = false;
//        while (!response) {
//            if (viewNew.charAt(0) == ('y') || viewNew.charAt(0) == ('Y') || viewNew.charAt(0) == ('n') || viewNew.charAt(0) == ('N')) {
//                response = true;
//            } else {
//                System.out.println("I didn't understand that.");
//            }
//        }
//        return viewNew.charAt(0) == ('y') || viewNew.charAt(0) == ('Y');
//    }



    // Getters and Setters
    // Returns an array of an array of points for the lines to be drawn
    public ArrayList <int[]> getLines() {
        return lines;
    }
    // sets the scale
    public void setScale(int scale) { this.scale = scale; }
}