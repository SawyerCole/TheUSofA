/**
 * TheUSofA.java
 * Assignment: Final Project
 * Purpose: Starts the program and creates components
 *
 * @version 6/19/2017
 */
import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
//The main class
public class TheUSofA extends JFrame {
    //private NavPanel navigationPanel = new NavPanel(outline);
    Dimension dimension = new Dimension(1200, 600);
    private Outline outline = new Outline();
    private Window window = new Window(outline);

    //This works as the main method which pulls together all of the classes
    private TheUSofA() throws FileNotFoundException {

        add(window);
        setTitle("The US of A");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(dimension);
        setLocationRelativeTo(null);
        setUndecorated(false);
        setFocusable(true);
        setVisible(true);
        setResizable(true);
    }

    //Uses the private main above
    public static void main(String[] args) throws FileNotFoundException {
        new TheUSofA();
    }


}
