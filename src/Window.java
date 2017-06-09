import javax.swing.*;
import java.awt.*;

/**
 * Purpose:
 */
public class Window extends JPanel {

    private Timer _timer;
    private Outline outline;

    public Window(Outline outline) {
        this.outline = outline;
        setDoubleBuffered(true);
        setFocusable(true);
        _timer = new Timer(5, e -> paintInterval());
        _timer.start();
    }



    private void paintInterval() {
        this.repaint();
    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        paintOutline(g2d);
    }

    private void paintOutline(Graphics2D g2d) {

        for (int[] cords : outline.getLines()) {
            g2d.drawLine(cords[0], cords[1], cords[2], cords[3]);
        }
    }
}
