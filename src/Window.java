import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;

/**
 * Purpose:
 */
public class Window extends JPanel {

    private Timer _timer;
    private Outline outline;
    private Dimension  dimension;
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



    private void paintInterval() {
        setMapSize();
        this.repaint();
    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2d = (Graphics2D) g;
        if (navPanel.getClearBoard()) {
            g2d.setColor(Color.WHITE);
            g2d.fillRect(0, 30, 1000, 1000);
            g2d.setColor(Color.BLACK);
        }
        paintOutline(g2d);
        setDimension(getDimension());
        //System.out.println(dimension.getHeight() + " " + dimension.getWidth());

    }

    private void paintOutline(Graphics2D g2d) {
        for (int[] cords : outline.getLines()) {
            g2d.drawLine(cords[1], cords[0] + 30, cords[3], cords[2] + 30);
        }
    }

    public Dimension getDimension(){
        return dimension;
    }

    void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }

    public void setMapSize() {outline.setScale(navPanel.getMapSize());}
}
