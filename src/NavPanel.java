import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * NavPanel.java
 * Assignment: Final Project
 * Purpose: Operates the navigation panel at the top of the screen
 *
 * @version 6/19/2017
 */
public class NavPanel extends JPanel implements KeyListener {
    private JLabel stateLabel, sizeLabel;
    private JComboBox <String> stateTextField;
    private JComboBox <Integer> sizeBox;
    private JButton viewCountry, submitButton;
    private Outline outline;
    private boolean clearBoard = false;
    private int size = 0;
    private String[] states;
    private String[] sizes;


    public NavPanel(Outline outline) throws FileNotFoundException {
        this.outline = outline;
        states = createList(true);
        sizes = createList(false);
        createComponents();
        addComponents();
        componentsActions();
    }

    // Creates navigation bar components
    private void createComponents() {
        viewCountry = new JButton("View Country");
        stateLabel = new JLabel("State: ");
        stateTextField = new JComboBox(states);
        stateTextField.setEditable(false);
        sizeLabel = new JLabel("Set the sizeBox: ");
        sizeBox = new JComboBox(sizes);
        submitButton = new JButton("Submit");
    }

    // Creates navigation bar's action(s)
    private void componentsActions() {

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    outline.fileInput(states[stateTextField.getSelectedIndex()]);
                    operationStarted();
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
                    operationStarted();
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }

    // adds navigation bar components to the panel
    private void addComponents() {
        add(viewCountry);
        add(stateLabel);
        add(stateTextField);
        add(sizeLabel);
        add(sizeBox);
        add(submitButton);
        addKeyListener(this); // doesn't work
    }

    // returns a boolean for whether or not we clear the board
    public boolean getClearBoard() {
        if (clearBoard) {
            clearBoard = false;
            return true;
        }
        return false;
    }

    // returns the size of the map
    public int getMapSize() {
        return size;
    }

    private int changeToInt(String string) {
        int changedToInt = 0;
        for (int i = 0; i < string.length(); i++) {
            int charX = string.charAt(i) - 48;
            if (charX == 10) {
                charX = 0;
            } else {
            }
            changedToInt += charX * (string.length() - i + 1) * 10;
        }
        return changedToInt;
    }

    // updates the map data so
    private void operationStarted() throws FileNotFoundException {
        clearBoard = true;
        outline.fileToDrawing();
        size = changeToInt(sizes[sizeBox.getSelectedIndex()]);
    }


    @Override
    public void keyTyped(KeyEvent e) { // doesn't work
        switch (e.getKeyChar()) {
            case (KeyEvent.VK_ENTER):
                try {
                    outline.fileInput(states[stateTextField.getSelectedIndex()]);
                    operationStarted();
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
        }
    }


    private String[] createList(boolean isStates) throws FileNotFoundException {
        int numberOfElements;
        String[] listFromFile;
        if (isStates) {
            Scanner fileData = new Scanner(new File("src/Data/States.txt"));
            numberOfElements = 50;
            listFromFile = new String[numberOfElements];
            for (int i = 0; i < numberOfElements && fileData.hasNextLine(); i++) {
                listFromFile[i] = fileData.nextLine();
            }
        } else {
            listFromFile = new String[]{"1", "2", "3", "4", "5", "6", "7"};
        }
        return listFromFile;
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
