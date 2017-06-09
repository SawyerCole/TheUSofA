/**
 * Created by ros_vishan on 6/5/2017.
 */

import javax.swing.*;
import java.io.FileNotFoundException;

public class TheUSofA extends JFrame {
    private Outline outline = new Outline();
    private Window window = new Window(outline);
    private NavPanel navigationPanel = new NavPanel(outline);

    private TheUSofA() throws FileNotFoundException {

        add(window);
        window.add(navigationPanel);
        setTitle("The US of A");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);
        setUndecorated(false);
        setVisible(true);
        setResizable(true);
    }

    public static void main(String[] args) throws FileNotFoundException {
        new TheUSofA();
    }


}
