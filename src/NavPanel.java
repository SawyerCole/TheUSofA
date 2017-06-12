import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

/**
 * NavPanel.java
 * Assignment:
 * Purpose:
 *
 * @version 6/8/2017
 */
public class NavPanel extends JPanel {
    private JLabel stateLabel, countyLabel;
    private JTextField stateTextField, countyTextField;
    private JCheckBox isCounty;
    private JButton viewCountry, submitButton;
    private Outline outline;
    private boolean clearBoard = false;

    public NavPanel(Outline outline) {
        this.outline = outline;
        createComponents();
        addComponents();
        componentsActions();
    }

    // Creates navigation bar components
    private void createComponents() {
        viewCountry = new JButton("View Country");
        stateLabel = new JLabel("State: ");
        stateTextField = new JTextField(15);
        countyLabel = new JLabel("County: ");
        countyTextField = new JTextField(15);
        countyTextField.setEnabled(false);
        isCounty = new JCheckBox("This is a County");
        submitButton = new JButton("Submit");
    }

    // Creates navigation bar's action(s)
    private void componentsActions() {

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    outline.fileInput(stateTextField.getText());
                    outline.fileToDrawing();

                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
            }
        });

        viewCountry.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    outline.fileInput("USA");
                    outline.fileToDrawing();
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }

            }
        });

        isCounty.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                countyTextField.setEnabled(isCounty.isSelected());
            }
        });
    }

    // adds navigation bar components to the panel
    private void addComponents() {
        add(viewCountry);
        add(stateLabel);
        add(stateTextField);
        add(isCounty);
        add(countyLabel);
        add(countyTextField);
        add(submitButton);
    }

    public boolean getClearBoard() {
        if (clearBoard) {
            clearBoard = false;
            return true;
        }
        return false;
    }

}
