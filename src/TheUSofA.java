/**
 * Created by ros_vishan on 6/5/2017.
 */

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;

public class TheUSofA extends JFrame {
    private Outline outline = new Outline();
    private Window window = new Window(outline);
    //private NavPanel navigationPanel = new NavPanel(outline);
    Dimension dimension = new Dimension(900, 600);

    private TheUSofA() throws FileNotFoundException {

        add(window);
        //window.add(navigationPanel);
        setTitle("The US of A");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setSize(dimension);
        window.setDimension(getSize());
        setLocationRelativeTo(null);
        setUndecorated(false);
        setVisible(true);
        setResizable(true);
    }

    public static void main(String[] args) throws FileNotFoundException {
        new TheUSofA();
    }


}
