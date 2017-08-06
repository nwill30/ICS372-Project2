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

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * The context is an obserer for the clock and stores the context info for
 * states
 *
 */
public class Context {
    //these are all the variables to be imported from the config file.
    private int fridgeLow;
    private int fridgeHigh;
    private int freezerLow;
    private int freezerHigh;
    private int roomLow;
    private int roomHigh;
    private int fridgeRateLossDoorClosed;
    private int fridgeRateLossDoorOpened;
    private int freezerRateLossDoorOpen;
    private int freezerRateLossDoorClosed;
    private int fridgeCompressorStartDiff;
    private int freezerCompressorStartDiff;
    private int fridgeCoolRate;
    private int freezerCoolRate;
    private int currentFreezerTemp;
    private int currentFridgeTemp;
    private int currentRoomTemp;
    private int desiredFridgeTemp = fridgeLow;
    private int desiredFreezerTemp = freezerLow;
    private static RefrigeratorDisplay refrigeratorDisplay;
    private FreezerState freezerCurrentState;
    private FridgeState fridgeCurrentState;
    private static Context instance;


    /**

     * Make it a singleton
     */
    private Context() {
        instance = this;
        refrigeratorDisplay = RefrigeratorDisplay.instance();
        freezerCurrentState = FreezerClosedState.instance();
        fridgeCurrentState = FridgeClosedState.instance();
    }

    /**
     * Return the instance
     *
     * @return the object
     */
    public static Context instance() {
        if (instance == null) {
            instance = new Context();
        }
        return instance;
    }

    /**
     * lets door closed state be the starting state adds the object as an
     * observable for clock
     */
    public void initialize() {
        String fileName = chooseFile();
        System.out.println(fileName);
        processFile(fileName);
        getDisplay().randomizeStartingTemps();
        instance.changeFreezerCurrentState(FreezerClosedState.instance());
        instance.changeFridgeCurrentState(FridgeClosedState.instance());

    }

    /**
     * Called from the states to change the current state
     *
     * @param nextState
     *            the next state
     */
    public void changeFreezerCurrentState(FreezerState nextState) {
        freezerCurrentState.leave();
        freezerCurrentState = nextState;
        nextState.run();
    }

    /**
     * Called from the states to change the current state
     *
     * @param nextState
     *            the next state
     */
    public void changeFridgeCurrentState(FridgeState nextState) {
        fridgeCurrentState.leave();
        fridgeCurrentState = nextState;
        nextState.run();
    }
    /**
     * Gets the display
     *
     * @return the display
     */
    public RefrigeratorDisplay getDisplay() {
        return refrigeratorDisplay;
    }

    /**
     * Opens a J-File chooser to implement reading in to a config file. I know this isn't in the main method but
     * I consider it more flexible.
     * @return
     */
    private static String chooseFile() {

        JFileChooser chooser;
        String fileName;
        FileNameExtensionFilter filter;
        int r;

        fileName = null;
        chooser = new JFileChooser();
        filter = new FileNameExtensionFilter("Text Files", "dat", "txt");
        chooser.setFileFilter(filter);
        chooser.setCurrentDirectory(new File("."));
        r = chooser.showOpenDialog(null);
        if (r == JFileChooser.APPROVE_OPTION)
            fileName = chooser.getSelectedFile().getAbsolutePath();

        return (fileName);

    }

    /**
     * This processes the file and assigns them properly.
     * @param iFile
     */
    private void processFile(String iFile){
        int i = 0;
        BufferedReader iBuffer;
        String iLine;
        FileReader iReader;
        int lineNumber;
        int value;
        int[] values = new int[14];
        lineNumber = 0;
        try{
            iReader = new FileReader(iFile);
            iBuffer = new BufferedReader(iReader);

            for(lineNumber = 1; ; lineNumber++){
                iLine = iBuffer.readLine();
                if(iLine == null)
                    break;
                iLine = extractNumberFromString(iLine);
                iLine = iLine.trim();
                value = Integer.parseInt(iLine);
                values[i] = value;
                i++;
            }

            fridgeLow = values[0];
            fridgeHigh = values[1];
            freezerLow = values[2];
            freezerHigh = values[3];
            roomLow = values[4];
            roomHigh = values[5];
            fridgeRateLossDoorClosed = values[6];
            fridgeRateLossDoorOpened = values[7];
            freezerRateLossDoorOpen = values[9];
            freezerRateLossDoorClosed = values[8];
            fridgeCompressorStartDiff = values[10];
            freezerCompressorStartDiff = values[11];
            fridgeCoolRate = values[12];
            freezerCoolRate = values[13];

            System.out.println(Arrays.toString(values));
        }
        catch (Exception e){
            System.err.println("Reading failed at line" + lineNumber);
            e.printStackTrace(System.err);
        }

    }

    /**
     * Extract everything besides numbers from a source string this makes it easier to read the file
     *
     * @param source
     * @return
     */
    private String extractNumberFromString(String source) {
        StringBuilder endString = new StringBuilder(100);
        for (char ch : source.toCharArray()) {
            if(ch == '.'){

                endString.append('0');
            }
            if (ch >= '0' && ch <= '9') {
                endString.append(ch);
            }
            else if(ch == '-'){
                endString.append('-');
            }
            else
                endString.append(" ");
        }

        return endString.toString();
    }


    public int getFridgeLow() {
        return fridgeLow;
    }

    public void setFridgeLow(int fridgeLow) {
        this.fridgeLow = fridgeLow;
    }

    public int getFridgeHigh() {
        return fridgeHigh;
    }

    public void setFridgeHigh(int fridgeHigh) {
        this.fridgeHigh = fridgeHigh;
    }

    public int getFreezerLow() {
        return freezerLow;
    }

    public void setFreezerLow(int freezerLow) {
        this.freezerLow = freezerLow;
    }

    public int getFreezerHigh() {
        return freezerHigh;
    }

    public void setFreezerHigh(int freezerHigh) {
        this.freezerHigh = freezerHigh;
    }

    public int getRoomLow() {
        return roomLow;
    }

    public void setRoomLow(int roomLow) {
        this.roomLow = roomLow;
    }

    public int getRoomHigh() {
        return roomHigh;
    }

    public void setRoomHigh(int roomHigh) {
        this.roomHigh = roomHigh;
    }

    public int getFridgeRateLossDoorClosed() {
        return fridgeRateLossDoorClosed;
    }

    public void setFridgeRateLossDoorClosed(int fridgeRateLossDoorClosed) {
        this.fridgeRateLossDoorClosed = fridgeRateLossDoorClosed;
    }

    public int getFridgeRateLossDoorOpened() {
        return fridgeRateLossDoorOpened;
    }

    public void setFridgeRateLossDoorOpened(int fridgeRateLossDoorOpened) {
        this.fridgeRateLossDoorOpened = fridgeRateLossDoorOpened;
    }

    public int getFreezerRateLossDoorOpen() {
        return freezerRateLossDoorOpen;
    }

    public void setFreezerRateLossDoorOpen(int freezerRateLossDoorOpen) {
        this.freezerRateLossDoorOpen = freezerRateLossDoorOpen;
    }

    public int getFreezerRateLossDoorClosed() {
        return freezerRateLossDoorClosed;
    }

    public void setFreezerRateLossDoorClosed(int freezerRateLossDoorClosed) {
        this.freezerRateLossDoorClosed = freezerRateLossDoorClosed;
    }

    public int getFridgeCompressorStartDiff() {
        return fridgeCompressorStartDiff;
    }

    public void setFridgeCompressorStartDiff(int fridgeCompressorStartDiff) {
        this.fridgeCompressorStartDiff = fridgeCompressorStartDiff;
    }

    public int getFreezerCompressorStartDiff() {
        return freezerCompressorStartDiff;
    }

    public void setFreezerCompressorStartDiff(int freezerCompressorStartDiff) {
        this.freezerCompressorStartDiff = freezerCompressorStartDiff;
    }

    public int getFridgeCoolRate() {
        return fridgeCoolRate;
    }

    public void setFridgeCoolRate(int fridgeCoolRate) {
        this.fridgeCoolRate = fridgeCoolRate;
    }

    public int getFreezerCoolRate() {
        return freezerCoolRate;
    }

    public void setFreezerCoolRate(int freezerCoolRate) {
        this.freezerCoolRate = freezerCoolRate;
    }

    public int getCurrentFreezerTemp() {
        return currentFreezerTemp;
    }

    public void setCurrentFreezerTemp(int currentFreezerTemp) {
        this.currentFreezerTemp = currentFreezerTemp;
    }

    public int getCurrentFridgeTemp() {
        return currentFridgeTemp;
    }

    public void setCurrentFridgeTemp(int currentFridgeTemp) {
        this.currentFridgeTemp = currentFridgeTemp;
    }

    public int getCurrentRoomTemp() {
        return currentRoomTemp;
    }

    public void setCurrentRoomTemp(int currentRoomTemp) {
        this.currentRoomTemp = currentRoomTemp;
    }

    public FreezerState getFreezerCurrentState(){
        return freezerCurrentState;
    }

    public FridgeState getFridgeCurrentState(){
        return fridgeCurrentState;
    }

    public int getDesiredFridgeTemp(){
        return desiredFridgeTemp;
    }

    public void setDesiredFridgeTemp(int value){
        this.desiredFridgeTemp = value;
    }

    public int getDesiredFreezerTemp(){
        return desiredFreezerTemp;
    }

    public void setDesiredFreezerTemp(int value){
        this.desiredFreezerTemp = value;
    }


}