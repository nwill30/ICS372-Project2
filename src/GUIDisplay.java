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
import java.util.Random;
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
        private JLabel fridgeLight = new JLabel("Fridge light off");
        private JLabel freezerLight = new JLabel("Freezer light off");
        private JLabel fridgeTemp = new JLabel("Fridge Temp:              " + context.getCurrentFridgeTemp());
        private JLabel freezerTemp = new JLabel("Freezer Temp:            " + context.getCurrentFreezerTemp());
        private JLabel roomTemp = new JLabel("Room Temp:           " + context.getCurrentRoomTemp());
        private JLabel time = new JLabel("Current Time:            " + FreezerTimer.instance().getTimeValue());
        private JLabel time2 = new JLabel("Current Time:            " + FridgeTimer.instance().getTimeValue());
        private JLabel fridgeStatus = new JLabel("Door Closed");
        private JLabel freezerStatus = new JLabel("Door Closed");
        private JLabel freezerCompressorStatus = new JLabel("Freezer Idle");
        private JLabel fridgeCompressorStatus = new JLabel("Fridge Idle");
        private JTextField roomTempTextField = new JTextField("INPUT ROOM TEMP HERE");
        private JTextField fridgeTempTextField = new JTextField("INPUT FRIDGE TEMP");

        private JTextField freezerTempTextField = new JTextField("INPUT FREEZER TEMP");
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
            getContentPane().add(freezerDoorOpenBtn);
            getContentPane().add(freezerDoorCloseBtn);
            getContentPane().add(statusLbl);
            getContentPane().add(fridgeLight);
            getContentPane().add(freezerLight);
            getContentPane().add(fridgeTemp);
            getContentPane().add(freezerTemp);
            getContentPane().add(roomTemp);
            getContentPane().add(fridgeStatus);
            getContentPane().add(freezerStatus);
            getContentPane().add(time);
            getContentPane().add(fridgeCompressorStatus);
            getContentPane().add(freezerCompressorStatus);
            getContentPane().add(time2);
            freezerDoorCloseBtn.addActionListener(GUIDisplay.this);
            freezerDoorOpenBtn.addActionListener(GUIDisplay.this);
            fridgeDoorCloseBtn.addActionListener(GUIDisplay.this);
            fridgeDoorOpenBtn.addActionListener(GUIDisplay.this);
            setFreezerTempBtn.addActionListener(GUIDisplay.this);
            setFridgeTempBtn.addActionListener(GUIDisplay.this);
            setRoomTempBtn.addActionListener(GUIDisplay.this);

            pack();
            setVisible(true);
        }

    }

    @Override
    public void actionPerformed(ActionEvent event) {
        ((GUIButton) event.getSource()).inform(this);
    }

    @Override
    public void randomizeStartingTemps(){
        Random random = new Random();
        context.setCurrentRoomTemp(random.nextInt(((context.getRoomHigh() - context.getRoomLow()) + 1))
                + context.getRoomLow());
        context.setCurrentFreezerTemp(context.getCurrentRoomTemp());
        context.setCurrentFridgeTemp(context.getCurrentRoomTemp());
        context.setDesiredFridgeTemp(context.getFridgeLow());
        context.setDesiredFreezerTemp(context.getFreezerLow());
        frame.fridgeTemp.setText(("Fridge Temp:              " + context.getCurrentFridgeTemp()));
        frame.roomTemp.setText(("Room Temp:              " + context.getCurrentRoomTemp()));
        frame.freezerTemp.setText(("Freezer Temp:              " + context.getCurrentFreezerTemp()));
    }

    /***/
    @Override
    public void displayRoomTemp(){
        try{
            int value = Integer.parseInt(frame.roomTempTextField.getText());
            if(value >= context.getRoomLow() && value <= context.getRoomHigh()){
                frame.roomTemp.setText("Room Temp:              " + value);
                context.setCurrentRoomTemp(value);
            }else
                frame.roomTemp.setText("Invalid Temp must be between " + context.getRoomLow()
                        + " and " + context.getRoomHigh());
        }
        catch (NumberFormatException nfe){
            frame.roomTemp.setText("Please Enter Numbers");
        }
    }

    /***/
    @Override
    public void displayFridgeTemp(){
        try {
            int value = Integer.parseInt(frame.fridgeTempTextField.getText());
            if (value >= context.getFridgeLow() && value <= context.getFridgeHigh()) {
                frame.setFridgeTempLbl.setText("Desired Fridge Temp:              " + value);
                context.setDesiredFridgeTemp(value);
            } else
                frame.fridgeTemp.setText("Invalid Temp must be between " + context.getFridgeLow()
                        + " and " + context.getFridgeHigh());
        }
        catch (NumberFormatException nfe){
            frame.fridgeTemp.setText("Please Enter Numbers");
        }
    }

    /***/
    @Override
    public void displayFreezerTemp(){
        try {
            int value = Integer.parseInt(frame.freezerTempTextField.getText());
            if (value <= context.getFreezerLow() && value >= context.getFreezerHigh()) {
                frame.setFreezerTempLbl.setText("Desired Freezer Temp:            " + value);
                context.setDesiredFreezerTemp(value);
            } else
                frame.freezerTemp.setText("Invalid Temp must be between " + context.getFreezerHigh()
                        + " and " + context.getFreezerLow());
        }

        catch (NumberFormatException nfe){
            frame.freezerTemp.setText("Please Enter Numbers");
        }
    }

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

    /**
     * Boolean to determine if compressor is running in freezer
     * @return
     */
    @Override
    public boolean isFreezerCooling(){
        int currentFreezerTemp = context.getCurrentFreezerTemp();
        int freezerCompressorStartDiff = context.getFreezerCompressorStartDiff();
        if((currentFreezerTemp - context.getDesiredFreezerTemp()) >= freezerCompressorStartDiff){
            frame.freezerCompressorStatus.setText("Freezer Cooling");
            return true;
        }
        else{
            frame.freezerCompressorStatus.setText("Freezer Idle");
            return false;
        }

    }

    /**
     * Boolean to determine if compressor is running in fridge
     * @return
     */
    @Override
    public boolean isFridgeCooling(){
        int currentFridgeTemp = context.getCurrentFridgeTemp();
        int fridgeCompressorStartDiff = context.getFridgeCompressorStartDiff();
        if((currentFridgeTemp - context.getDesiredFridgeTemp()) >= fridgeCompressorStartDiff - 1){
            frame.fridgeCompressorStatus.setText("Fridge Cooling");
            return true;
        }
        else{
            frame.fridgeCompressorStatus.setText("Fridge Idle");
            return false;
        }

    }

    /***/
    @Override
    public void freezerIdle(){frame.freezerCompressorStatus.setText("Freezer Idle");}

    /***/
    @Override
    public void fridgeCooling(){frame.fridgeCompressorStatus.setText("Fridge Cooling");}

    /***/
    @Override
    public void fridgeIdle(){frame.fridgeCompressorStatus.setText("Fridge Idle");}

    /**
     * This function is a conditional to properly handle how the temperature changes throughout the simulation for the
     * freezer.I know this is a cluttered method and should be moved
     * but with the current time constraint it fulfills the functionality.
     */
    @Override
    public void freezerDisplayCurrentTime(){
        int currentTime = FreezerTimer.instance().getTimeValue();
        FreezerTimer.instance().setTimeValue(currentTime + 1);
        frame.time.setText("Current Time:            " + FreezerTimer.instance().getTimeValue());
        int currentFreezerTemp = context.getCurrentFreezerTemp();
        if(context.getFreezerCurrentState().getClass() == FreezerOpenState.instance().getClass()){
            //if(!isFreezerCooling()){
                if(FreezerTimer.instance().getTimeValue() % context.getFreezerRateLossDoorOpen() == 0
                        && currentFreezerTemp < context.getCurrentRoomTemp()){
                    currentFreezerTemp = currentFreezerTemp + 1;
                    context.setCurrentFreezerTemp(currentFreezerTemp);
                    frame.freezerTemp.setText(("Freezer Temp:              " + context.getCurrentFreezerTemp()));
                }
           // }

            else{
                if(FreezerTimer.instance().getTimeValue() % context.getFreezerCoolRate() == 0
                        && currentFreezerTemp < context.getCurrentRoomTemp()){
                    currentFreezerTemp = currentFreezerTemp - 1;
                    context.setCurrentFreezerTemp(currentFreezerTemp);
                    frame.freezerTemp.setText(("Freezer Temp:              " + context.getCurrentFreezerTemp()));
                }
            }
        }

        else{
            //if(!isFreezerCooling()){
                if(FreezerTimer.instance().getTimeValue() % context.getFreezerRateLossDoorClosed() == 0){
                    currentFreezerTemp = currentFreezerTemp + 1;
                    context.setCurrentFreezerTemp(currentFreezerTemp);
                    frame.freezerTemp.setText(("Freezer Temp:              " + context.getCurrentFreezerTemp()));
                }
            //}
            else{
                if(FreezerTimer.instance().getTimeValue() % context.getFreezerCoolRate() == 0){
                    currentFreezerTemp = currentFreezerTemp - 1;
                    context.setCurrentFreezerTemp(currentFreezerTemp);
                    frame.freezerTemp.setText(("Freezer Temp:              " + context.getCurrentFreezerTemp()));
                }
            }
        }
    }

    /**
     * This function is a conditional to properly handle how the temperature
     * within the fridge changes throughout the simulation. I know this is a cluttered method and should be moved
     * but with the current time constraint it fulfills the functionality.
     */
    @Override
    public void fridgeDisplayCurrentTime(){
        int currentTime = FridgeTimer.instance().getTimeValue();
        FridgeTimer.instance().setTimeValue(currentTime + 1);
        int currentFridgeTemp = context.getCurrentFridgeTemp();
        if(context.getFridgeCurrentState().getClass() == FridgeOpenState.instance().getClass()){
            //if(!isFridgeCooling()){
                if(FridgeTimer.instance().getTimeValue() % context.getFridgeRateLossDoorOpened() == 0
                        && currentFridgeTemp < context.getCurrentRoomTemp()){
                    currentFridgeTemp = currentFridgeTemp + 1;
                    context.setCurrentFridgeTemp(currentFridgeTemp);
                    frame.fridgeTemp.setText(("Fridge Temp:              " + context.getCurrentFridgeTemp()));
                }
            //}

            else{
                if(FridgeTimer.instance().getTimeValue() % context.getFridgeCoolRate() == 0){
                    currentFridgeTemp = currentFridgeTemp - 1;
                    context.setCurrentFridgeTemp(currentFridgeTemp);
                    frame.fridgeTemp.setText(("Fridge Temp:              " + context.getCurrentFridgeTemp()));
                }
            }
        }

        else{
            //if(!isFridgeCooling()){
                if(FridgeTimer.instance().getTimeValue() % context.getFridgeRateLossDoorClosed() == 0
                        && currentFridgeTemp < context.getCurrentRoomTemp()){
                    currentFridgeTemp = currentFridgeTemp + 1;
                    context.setCurrentFridgeTemp(currentFridgeTemp);
                    frame.fridgeTemp.setText(("Fridge Temp:              " + context.getCurrentFridgeTemp()));
                }
            //}
            else{
                if(FridgeTimer.instance().getTimeValue() % context.getFridgeCoolRate() == 0){
                    currentFridgeTemp = currentFridgeTemp - 1;
                    context.setCurrentFridgeTemp(currentFridgeTemp);
                    frame.fridgeTemp.setText(("Fridge Temp:              " + context.getCurrentFridgeTemp()));
                }
            }
        }
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