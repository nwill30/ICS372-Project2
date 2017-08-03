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
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

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
        private JButton fridgeDoorOpenBtn = new OpenFridgeDoorButton("Open Fridge door");
        private JButton fridgeDoorCloseBtn = new CloseFridgeDoorButton("Close Fridge door");
        private JButton freezerDoorOpenBtn = new OpenFreezerDoorButton("Open Freezer door");
        private JButton freezerDoorCloseBtn = new CloseFreezerDoorButton("Close Freezer door");
        private JButton setRoomTempBtn = new SetRoomTempButton("Set room temp");
        private JButton setFridgeTempBtn = new SetFridgeTempButton("Set desired fridge temp");
        private JButton setFreezerTempBtn = new SetFreezerTempButton("Set desired freezer temp");
        private JLabel setRoomTempLbl = new JLabel("Room temp");
        private JLabel setFridgeTempLbl = new JLabel("Desired fridge temp");
        private JLabel setFreezerTempLbl = new JLabel("Desired freezer temp");
        private JLabel statusLbl = new JLabel("Status");
        private JLabel fridgeLight = new JLabel("Fridge ligtht off");
        private JLabel freezerLight = new JLabel("Freezer light off");
        private JLabel fridgeTemp = new JLabel("            ");
        private JLabel freezerTemp = new JLabel("            ");
        private JLabel roomTemp = new JLabel("            ");
        private JLabel fridgeStatus = new JLabel("Idle");
        private JLabel freezerStatus = new JLabel("Idle");
        private JTextField roomTempTextField = new JTextField("INPUT ROOM TEMP HERE");
        private JTextField fridgeTempTextField = new JTextField("INPUT FRIDGE TEMP BETWEEN 37 and 41 F");
        private JTextField freezerTempTextField = new JTextField("INPUT FREEZER TEMP BETWEEN -9 and 0 F");
        private JLabel spaceFiller = new JLabel("                 ");

        /**
         * Set up the JFrame
         */
        private SimpleDisplay() {
            super("Refrigerator");
            getContentPane().setLayout(new GridLayout(9,3,30,30));
            getContentPane().add(setRoomTempLbl);
            getContentPane().add(roomTempTextField);
            getContentPane().add(setRoomTempBtn);
            getContentPane().add(setFridgeTempLbl);
            getContentPane().add(fridgeTempTextField);
            getContentPane().add(setFridgeTempBtn);
            getContentPane().add(setFreezerTempLbl);
            getContentPane().add(freezerTempTextField);
            getContentPane().add(setFreezerTempBtn);
            getContentPane().add(fridgeDoorOpenBtn);
            getContentPane().add(fridgeDoorCloseBtn);
//            getContentPane().add(spaceFiller);
            getContentPane().add(freezerDoorOpenBtn);
            getContentPane().add(freezerDoorCloseBtn);
//            getContentPane().add(spaceFiller);
            getContentPane().add(statusLbl);
//            getContentPane().add(spaceFiller);
//            getContentPane().add(spaceFiller);
            getContentPane().add(fridgeLight);
            getContentPane().add(freezerLight);
//            getContentPane().add(spaceFiller);
            getContentPane().add(fridgeTemp);
            getContentPane().add(freezerTemp);
            getContentPane().add(roomTemp);
//            getContentPane().add(spaceFiller);
            getContentPane().add(fridgeStatus);
            getContentPane().add(freezerStatus);
            freezerDoorCloseBtn.addActionListener(GUIDisplay.this);
            freezerDoorOpenBtn.addActionListener(GUIDisplay.this);
            fridgeDoorCloseBtn.addActionListener(GUIDisplay.this);
            fridgeDoorOpenBtn.addActionListener(GUIDisplay.this);
            setFreezerTempBtn.addActionListener(GUIDisplay.this);
            setFridgeTempBtn.addActionListener(GUIDisplay.this);

            pack();
            setVisible(true);
        }
    }

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
     * Display a text indicating that the freezer light is on
     */
    @Override
    public void turnFreezerLightOn() {
        frame.freezerLight.setText("Freezer Light On");
    }

    /**
     * Display a text indicating that the freezer light is off
     */
    @Override
    public void turnFreezerLightOff() {
        frame.freezerLight.setText("Freezer Light Off");
    }

    /**
     * Display a text indicating that the fridge light is on
     */
    @Override
    public void turnFridgeLightOn() {
        frame.fridgeLight.setText("Fridge Light On");
    }

    /**
     * Display a text indicating that the freezer door is open
     */
    @Override
    public void turnFridgeLightOff() {
        frame.fridgeLight.setText("Fridge Light Off");
    }

    /**
     * Display a text indicating that the freezer door is closed
     */
    @Override
    public void freezerDoorClosed() {
        frame.freezerStatus.setText("Freezer Door Closed");
    }

    /**
     * Display a text indicating that the door is open
     */
    @Override
    public void freezerDoorOpened() {
        frame.freezerStatus.setText("Freezer Door Opened");
    }

    /**
     * Display a text indicating that the fridge door is open
     */
    @Override
    public void fridgeDoorOpened(){
        frame.fridgeStatus.setText("Fridge Door Opened");
    }

    /**
     * Display a text indicating that the fridge door is closed
     */
    @Override
    public void fridgeDoorClosed(){
        frame.fridgeStatus.setText("Fridge Door Closed");
    }

    /***/
    @Override
    public void freezerCooling(){frame.freezerStatus.setText("Freezer Cooling");};

    /***/
    @Override
    public void freezerIdle(){frame.freezerStatus.setText("Freezer Idle");};

    /***/
    @Override
    public void fridgeCooling(){frame.fridgeStatus.setText("Fridge Cooling");};

    /***/
    @Override
    public void fridgeIdle(){frame.fridgeStatus.setText("Fridge Idle");};

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