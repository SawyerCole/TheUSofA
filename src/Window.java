import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

/**
 * Purpose:
 */
public class Window extends JPanel implements ActionListener {
    protected JTextField textField;

    public Window() throws FileNotFoundException {
        Outline outline = new Outline();
        outline.intro();
        boolean restart = true;
        while(restart){
            restart = outline.toContinue(outline.fileToDrawing(outline.fileInput()));
        }
        System.out.println("Goodbye!");
    }

    private void paintInterval() {

        this.repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;

    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }
}
