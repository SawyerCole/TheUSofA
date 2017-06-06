/**
 * Created by ros_vishan on 6/5/2017.
 */
import javax.swing.JFrame;
import java.io.FileNotFoundException;

public class TheUSofA extends JFrame {

    private Window window = new Window();

    private TheUSofA() throws FileNotFoundException {
        add(window);
        setTitle("Cube");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 800);
        setLocationRelativeTo(null);
        setUndecorated(false);
        setVisible(true);
        setResizable(false);
    }

    public static void main(String[] args) throws FileNotFoundException {
        new TheUSofA();
    }
}
