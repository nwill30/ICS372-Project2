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
        private JLabel fridgeLight = new JLabel("Light Off");
        private JLabel freezerLight = new JLabel("Light Off");
        private JLabel fridgeTemp = new JLabel("            ");
        private JLabel freezerTemp = new JLabel("            ");
        private JLabel roomTemp = new JLabel("            ");
        private JLabel fridgeActivity = new JLabel("Idle");
        private JLabel freezerActivity = new JLabel("Idle");

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
            getContentPane().add(roomTemp);
            getContentPane().add(fridgeActivity);
            getContentPane().add(freezerActivity);
            fridgeDoorOpenBtn.addActionListener(GUIDisplay.this);
            fridgeDoorCloseBtn.addActionListener(GUIDisplay.this);
            freezerDoorOpenBtn.addActionListener(GUIDisplay.this);
            freezerDoorCloseBtn.addActionListener(GUIDisplay.this);
            setRoomTempBtn.addActionListener(GUIDisplay.this);
            setFreezerTempBtn.addActionListener(GUIDisplay.this);
            setFreezerTempBtn.addActionListener(GUIDisplay.this);

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

    /***/
    @Override
    public void displayRoomTemp(int value){ frame.roomTemp.setText(" " + value);}

    /***/
    @Override
    public void displayFridgeTemp(int value){ frame.fridgeTemp.setText(" " + value);};

    /***/
    @Override
    public void displayFreezerTemp(int value){ frame.freezerTemp.setText(" " + value);};

    /**
     * Display a text indicating that the freezer door is closed
     */
    @Override
    public void freezerDoorClosed() {
        frame.freezerActivity.setText("Door Closed");
    }

    /**
     * Display a text indicating that the freezer door is open
     */
    @Override
    public void freezerDoorOpened() {
        frame.freezerActivity.setText("Door Opened");
    }

    /**
     * Display a text indicating that the fridge door is open
     */
    @Override
    public void fridgeDoorClosed() {
        frame.fridgeActivity.setText("Door Closed");
    }

    /**
     * Display a text indicating that the fridge door is open
     */
    @Override
    public void fridgeDoorOpened() {
        frame.fridgeActivity.setText("Door Opened");
    }

    /**
     * Display a text indicating that the freezer light is on
     */
    @Override
    public void turnFreezerLightOn() {
        frame.freezerLight.setText("Light On");
    }

    /**
     * Display a text indicating that the freezer light is off
     */
    @Override
    public void turnFreezerLightOff() {
        frame.freezerLight.setText("Light Off");
    }

    /**
     * Display a text indicating that the fridge light is on
     */
    @Override
    public void turnFridgeLightOn() {
        frame.fridgeLight.setText("Light On");
    }

    /**
     * Display a text indicating that the fridge light is off
     */
    @Override
    public void turnFridgeLightOff() {
        frame.fridgeLight.setText("Light Off");
    }

    /***/
    @Override
    public void freezerCooling(){frame.freezerActivity.setText("Freezer Cooling");};

    /***/
    @Override
    public void freezerIdle(){frame.freezerActivity.setText("Freezer Idle");};

    /***/
    @Override
    public void fridgeCooling(){frame.fridgeActivity.setText("Fridge Cooling");};

    /***/
    @Override
    public void fridgeIdle(){frame.fridgeActivity.setText("Fridge Idle");};

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