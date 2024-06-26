package view;

import javax.swing.*;
import javax.swing.plaf.metal.MetalButtonUI;
import controller.GameController;
import extras.Messages;
import extras.ShipStatus;
import model.GameModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
@SuppressWarnings("deprecation")

// @borowiak-filip

/**
 * View Object of the MVC pattern. JView is a graphical representation of the data using Swing framework.
 * It it implements Observer pattern, allowing for comunication with the model via
 *  Observer - Observable relation.
 */
public class JView extends JFrame implements Observer {

    private JButton[][] board; // store board game as an 2D array of buttons.
    private JTextArea displayTop; // information field
    private JTextArea displayBottom; // information field


    public JView(GameController controller) {

        board = new JButton[11][11]; // initiate the board with fixed 11x11 dims
        
        JPanel panel = new JPanel(); // create master JPanel to contain all graphic components
        displayTop = new JTextArea(1, 2);
        displayTop.setEditable(false);
        displayBottom = new JTextArea(1, 2);
        displayBottom.setEditable(false);
        setTitle(Messages.TITLE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.setLayout(new GridLayout(11, 11)); // give the panel Grid Layout of 11x11 dims


        // Fixed layout of the board
        String[][] Layout = {{"/", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J"},
                             {"1", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-"},
                             {"2", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-"},
                             {"3", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-"},
                             {"4", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-"},
                             {"5", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-"},
                             {"6", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-"},
                             {"7", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-"},
                             {"8", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-"},
                             {"9", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-"},
                             {"10","-", "-", "-", "-", "-", "-", "-", "-", "-", "-"}};
        
        
        // Iteratively create button for each field button field "-"
        for (int i = 0; i < Layout.length; i++) {
            for (int j = 0; j < Layout[1].length; j++) {
                if(Layout[i][j] == "-") {
                    JButton btn = new JButton();
                    int row = i;
                    int col = j;
                    btn.addActionListener(new ActionListener() { // Add action Listener 
                        
                        @Override
                        // Invoke Model change via Controller. Technically, it could invoke model directly since
                        // JView adds itself to the model, hence has an instance of the model. Nonetheless following MVC pattern
                        // It uses controller to do so  
                        public void actionPerformed(ActionEvent e) {controller.change(row, col);} 
                       
                    });
                    panel.add(btn); // add each button to the panel
                    board[i][j] = btn; // add button to board button array

                }
                else { // if its not a button, then it is a label
                    JLabel lbl = new JLabel(Layout[i][j]); // create label with correct text 
                    lbl.setHorizontalAlignment(JLabel.CENTER);
                    panel.add(lbl); // add each lable to the panel
                }

            }
     
        }
    
        add(panel); // add panel to the container
        setSize(700, 700); // set fixed dim 
        setLocationRelativeTo(null); // place it in the center of the window
        setVisible(true); // display 
        
        // Wrap notifications in a  seperate JPanel
        JPanel notificationPanel = new JPanel(new GridLayout(2, 1));
        notificationPanel.add(new JScrollPane(displayTop));
        notificationPanel.add(new JScrollPane(displayBottom));

        add(notificationPanel, BorderLayout.NORTH); // add to the main container and place it in the upper bound 

        controller.addObserver(this); // Add View (itself) to the model as an Observer
        controller.setView(this);
            
    }


    public void displayGameOver() {
        displayTop.setText(Messages.ALL_SHIPS_DOWN);
        for (JButton[] btn : board) {
            for (JButton button : btn) {
                if (button != null) {
                    button.setEnabled(false); // turn off all other fields
                } 
            }

        }
    }

    /**
     * allows to display Messages to the user in the Top Display
    */
    public void displayUserMessage(String message) {displayTop.setText(message);}

    /**
     * paints button text to red
    */
    public void paintButtonRed(int coordX, int coordY) {this.board[coordX][coordY].setUI(new MetalButtonUI() {protected Color getDisabledTextColor() {return Color.red;}});}

    /**
     * allow to display user tries in the Bottom Display
    */
    public void displayTries(int count) {displayBottom.setText(Messages.TRIES_SUM + String.valueOf(count));}

    /**
     * allows to disable button
    */
    public void disableButton(int coordX, int coordY) { this.board[coordX][coordY].setEnabled(false);}

    /**
     * allows to set ship "Status" to Hit
    */
    public void setShipMarkerToHit(int coordX, int coordY) {this.board[coordX][coordY].setText(ShipStatus.H.toString());}

    /**
     * allows to set field "Status" to Miss
    */
    public void setShipMarkerToMiss(int coordX, int coordY) {this.board[coordX][coordY].setText(ShipStatus.M.toString());}

    /**
     * allows to set ship "Status" to Sunk
    */
    public void setShipMarkerToSunk(int coordX, int coordY) {this.board[coordX][coordY].setText(ShipStatus.X.toString());}
    
    /**
     * allows to set all ship parts to sunk and to color red upon sinking last ship part
    */
    public void setShipSunk(int[][] coordinates) {

        for (int[] part : coordinates) { // iterate through ship parts 
            // get ShipPart coordinates
            int shipPartX = part[0];
            int shipPartY = part[1];

            setShipMarkerToHit(shipPartX, shipPartY);
            paintButtonRed(shipPartX, shipPartY);
            
        }

    }


    @Override
    // On each change (model.notifyObservers) this function is invoked to update our view accordingly to the model object
    // In this approach to comply with requirements, updated is done via controller (enable, disable buttons), but could be just done here
    // Since, notifyObserver() allows to past arg. 
    public void update(Observable o, Object arg) {

        GameModel model = (GameModel) o; // cast Observable to GameModel 
        String message = (String) arg; // cast results to Triple

        displayUserMessage(message); // this is the first thing that will change after model processed the request, so update displays
        displayTries(model.getCountVal());

    }
}
