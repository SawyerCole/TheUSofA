import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;

/**
 * Window.java
 * Assignment: Final Project
 * Purpose: Creates the JPanel that the map is drawn on
 *
 * @version 6/19/2017
 */
public class Window extends JPanel {

    private Timer _timer;
    //Creates an outline class which draws the states/country
    private Outline outline;
    private NavPanel navPanel;

    public Window(Outline outline) throws FileNotFoundException {
        this.outline = outline;
        navPanel = new NavPanel(outline);
        add(navPanel);
        setDoubleBuffered(true);
        setFocusable(true);
        _timer = new Timer(5, e -> paintInterval());
        _timer.start();

    }


    // runs every tick to set map size
    private void paintInterval() {
        setMapSize();
        this.repaint();
    }

    //Actually draws the components and repeats it
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2d = (Graphics2D) g;
        // draws a white rectangle to "clear" the bored
        if (navPanel.getClearBoard()) {
            g2d.setColor(Color.GREEN);
            g2d.fillRect(0, 30, 1200, 1000);
            g2d.setColor(Color.PINK);
        }
        paintOutline(g2d);

    }

    //Tells the paint method what to draw
    private void paintOutline(Graphics2D g2d) {
        for (int[] cords : outline.getLines()) {
            g2d.setColor(Color.PINK);
            g2d.drawLine(cords[1], cords[0] + 30, cords[3], cords[2] + 30);
        }
    }

    // sets the size of the map
    public void setMapSize() {int size = 1;}

}

