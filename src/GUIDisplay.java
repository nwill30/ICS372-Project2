/**
 *
 * @author Brahma Dathan and Sarnath Ramnath
 * @Copyright (c) 2010

 * Redistribution and use with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - the use is for academic purpose only
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *   - Neither the name of Brahma Dathan or Sarnath Ramnath
 *     may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * The authors do not make any claims regarding the correctness of the code in this module
 * and are not responsible for any loss or damage resulting from its use.
 */
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Implementation of RefrigeratorDisplay. Has no conditionals.
 *
 */
public class GUIDisplay extends RefrigeratorDisplay implements ActionListener {
    private static SimpleDisplay frame;

    /**
     * Creates the frame and displays it.
     */
    private GUIDisplay() {
        frame = new SimpleDisplay();
        initialize();
    }

    /**
     * Inner class because the outer class extends RefrigeratorDisplay.
     *
     */
    private class SimpleDisplay extends JFrame {
        private GUIButton fridgeDoorOpenBtn = new OpenFridgeDoorButton("Open Fridge door");
        private GUIButton fridgeDoorCloseBtn = new CloseFridgeDoorButton("Close Fridge door");
        private GUIButton freezerDoorOpenBtn = new OpenFreezerDoorButton("Open Freezer door");
        private GUIButton freezerDoorCloseBtn = new CloseFreezerDoorButton("Close Freezer door");
        private GUIButton setRoomTempBtn = new SetRoomTempButton("Set room temp");
        private GUIButton setFridgeTempBtn = new SetFridgeTempButton("Set desired fridge temp");
        private GUIButton setFreezerTempBtn = new SetFreezerTempButton("Set desired freezer temp");
        private JLabel setRoomTempLbl = new JLabel("Room temp");
        private JLabel setFridgeTempLbl = new JLabel("Desired fridge temp");
        private JLabel setFreezerTempLbl = new JLabel("Desired freezer temp");
        private JLabel statusLbl = new JLabel("Status");
        private JLabel fridgeLight = new JLabel("            ");
        private JLabel freezerLight = new JLabel("            ");
        private JLabel fridgeTemp = new JLabel("            ");
        private JLabel freezerTemp = new JLabel("            ");
        private JLabel fridgeActivity = new JLabel("            ");
        private JLabel freezerActivity = new JLabel("            ");

        /**
         * Set up the JFrame
         */
        private SimpleDisplay() {
            super("Refrigerator");
            getContentPane().setLayout(new FlowLayout());
            getContentPane().add(fridgeDoorOpenBtn);
            getContentPane().add(fridgeDoorCloseBtn);
            getContentPane().add(freezerDoorOpenBtn);
            getContentPane().add(freezerDoorCloseBtn);
            getContentPane().add(setRoomTempBtn);
            getContentPane().add(setFridgeTempBtn);
            getContentPane().add(setFreezerTempBtn);
            getContentPane().add(setRoomTempLbl);
            getContentPane().add(setFridgeTempLbl);
            getContentPane().add(setFreezerTempLbl);
            getContentPane().add(statusLbl);
            getContentPane().add(fridgeLight);
            getContentPane().add(freezerLight);
            getContentPane().add(fridgeTemp);
            getContentPane().add(freezerTemp);
            getContentPane().add(fridgeActivity);
            getContentPane().add(freezerActivity);
            doorCloser.addActionListener(GUIDisplay.this);
            doorOpener.addActionListener(GUIDisplay.this);
            cookButton.addActionListener(GUIDisplay.this);
            pack();
            setVisible(true);
        }
    }

    /**
     * No conditionals. Let the clicked button do the hard work.
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        ((GUIButton) event.getSource()).inform(this);
    }

    /**
     * Display a text indicating that the light is on
     */
    @Override
    public void turnLightOn() {
        frame.lightStatus.setText("Light On");
    }

    /**
     * Display a text indicating that the light is off
     */
    @Override
    public void turnLightOff() {
        frame.lightStatus.setText("Light Off");
    }

    /**
     * Display a text indicating that the door is closed
     */
    @Override
    public void doorClosed() {
        frame.doorStatus.setText("Door Closed");
    }

    /**
     * Display a text indicating that the door is open
     */
    @Override
    public void doorOpened() {
        frame.doorStatus.setText("Door Opened");
    }

    /**
     * Display the remaining cook time
     */
    @Override
    public void displayTimeRemaining(int value) {
        frame.timerValue.setText(" " + value);
    }

    /**
     * Display a text indicating that cooking has started
     */
    @Override
    public void startCooking() {
        frame.cookingStatus.setText("Cooking");
    }

    /**
     * Display a text indicating that cooking has ended
     */
    @Override
    public void notCooking() {
        frame.cookingStatus.setText("Not cooking");
    }

    /**
     * Start the whole show
     *
     * @param args
     *            not used
     */
    public static void main(String[] args) {
        RefrigeratorDisplay display = new GUIDisplay();
    }
}