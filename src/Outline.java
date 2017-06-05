import java.io.*;
import java.util.*;
import java.awt.*;

public class Outline{
    //Sets a scaling factor so the states can be scaled however the user chooses.
    public static final int SCALE = 20;
    //Allows the user to enter
    //The intro text that explains the program
    public void intro(){
        System.out.println("This program draws a visualization of");
        System.out.println("data from a given file. Enter");
        System.out.println("- the 2 letter abbreviation for each state");
        System.out.println("- USA for all of the US by state, or");
        System.out.println("- USA-county for all of the US by county.");
        System.out.println("Capitalization does not matter.");
        System.out.println();
    }
    //Allows the user to input what they want to see (a state, the country or the country by county) and it will give the file of the chosen image to the next computer
    public Scanner fileInput(){
        Scanner files = new Scanner(System.in);
        boolean fileChosen = false;
        while(fileChosen == false){
            System.out.println("What would you like to see?");
            String fileToView = files.next() + ".txt";
            try{
                files = new Scanner(new File("src\\data\\" + fileToView));
                fileChosen = true;
            }catch(FileNotFoundException e){
                System.out.println("File not found. Try again.");
                fileChosen = false;
            }
        }
        return files;
    }
    //Draws the lines outlining the states and counties using the latitude and longitude points from the file of the chosen state
    public Scanner fileToDrawing(Scanner files){
        double minLong = files.nextDouble();
        double minLat = files.nextDouble();
        double maxLong = files.nextDouble();
        double maxLat = files.nextDouble();
        //I added 100 on each side to allow the image to be centered better so the outline of the state could be seen more clearly
        DrawingPanel panel = new DrawingPanel( (int) ((maxLong - minLong) * SCALE) + (SCALE * 2), (int) ((maxLat - minLat) * SCALE) + (SCALE * 2));
        Graphics g = panel.getGraphics();
        double lat1;
        double long1;
        double lat2;
        double long2;
        int subregions = files.nextInt();
        //After the first 5 different variables everything repeats in the same manner so it will repeat this way with every subregion
        while(files.hasNext() == true){
            //Used to skip the gap between subregions
            files.next();
            String subregionName = files.nextLine();
            String regionName = files.next();
            int points = files.nextInt();
            //Added the scale so that the state/country would be better centered
            long1 = (files.nextDouble() - minLong) * SCALE + SCALE;
            lat1 = (maxLat - files.nextDouble()) * SCALE + SCALE;
            //Subtracted 1 because the first line is already dealt with above
            for(int i = 1; i <= points - 1; i++){
                //I have these if statements to make sure that the decimal point is accounted for as turning a double into an int always rounds down
                if(long1 % 1 > 0.5){
                    long1 = long1 / 1 + 1;
                }
                if(lat1 % 1 > 0.5){
                    lat1 = lat1 / 1 + 1;
                }
                //Added the scale so the state would be better centered
                long2 = (files.nextDouble()- minLong) * SCALE + SCALE;
                lat2 = (maxLat - files.nextDouble()) * SCALE + SCALE;
                //I have these if statements to make sure that the decimal point is accounted for as turning a double into an int always rounds down
                if(long2 % 1 > 0.5){
                    long2 = long2 / 1 + 1;
                }
                if(lat2 % 1 > 0.5){
                    lat2 = lat2 / 1 + 1;
                }
                //Turned the different latitude and longitude points into ints because drawings can't use doubles
                g.drawLine((int)long2, (int)lat2, (int)long1, (int)lat1);
                long1 = long2;
                lat1 = lat2;
            }
        }
        return files;
    }
    //Used to see if the user wants to continue or not
    public boolean toContinue(Scanner files){
        files = new Scanner(System.in);
        System.out.print("Would you like to view somewhere new?");
        String viewNew = files.next();
        System.out.println();
        boolean response = false;
        while(response == false){
            if(viewNew.charAt(0)==('y') || viewNew.charAt(0) == ('Y')|| viewNew.charAt(0) == ('n') || viewNew.charAt(0) == ('N')){
                response = true;
            }
            else{
                System.out.println("I didn't understand that.");
            }
        }
        if(viewNew.charAt(0)==('y') || viewNew.charAt(0) == ('Y')){
            return true;
        }
        else{
            return false;
        }
    }
}