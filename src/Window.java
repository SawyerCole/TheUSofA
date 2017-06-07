import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;

/**
 * Purpose:
 */
public class Window extends JPanel implements ActionListener {
    private JTextField textField;
    private JTextArea textArea;
    private Timer _timer;
    Outline outline = new Outline();

    public Window() throws FileNotFoundException {
        super(new GridBagLayout());

        textField = new JTextField(20);
        textField.addActionListener(this);

        textArea = new JTextArea(5, 20);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        //Add Components to this panel.
        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = GridBagConstraints.REMAINDER;

        c.fill = GridBagConstraints.HORIZONTAL;
        add(textField, c);

        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0;
        c.weighty = 1.0;
        add(scrollPane, c);


        setDoubleBuffered(true);
        setFocusable(true);
        _timer = new Timer(5, e -> paintInterval());
        _timer.start();

        outline.intro();
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    createAndShowGUI();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private void paintInterval() {

        this.repaint();
    }

    private static void createAndShowGUI() throws FileNotFoundException {
        //Create and set up the window.
        JFrame frame = new JFrame("Enter Location");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add contents to the window.
        frame.add(new Window());

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        boolean restart = true;
        while(restart){
            restart = outline.toContinue(outline.fileToDrawing(outline.fileInput(), g2d));
        }
        System.out.println("Goodbye!");

    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String text = textField.getText();

    }

}
