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
import java.util.Observable;

/**
 * Specifies what the display system should do. Note that the implementation has
 * a lot of freedom to choose its display.
 */
public abstract class RefrigeratorDisplay extends Observable {
    protected static Context context;
    protected static RefrigeratorDisplay instance;

    /**
     * Initializes the context and instance
     */
    protected RefrigeratorDisplay() {
        instance = this;
        context = Context.instance();
    }

    /**
     * For singleton
     *
     * @return the object
     */
    public static RefrigeratorDisplay instance() {
        return instance;
    }

    /**
     * Do the initializations to make the context an observer
     */
    public void initialize() {
        context.initialize();
    }


    /**
     * Display the current room temerature.
     * */
    public abstract void displayRoomTemp();

    /**
     * Display the current fridge temerature.
     * */
    public abstract void displayFridgeTemp();

    /**
     * Display the current freezer temerature.
     * */
    public abstract void displayFreezerTemp();

    /**
     * Indicate that the Freezer light is on
     */
    public abstract void turnFreezerLightOn();

    /**
     * Indicate that the Freezer light is off
     */
    public abstract void turnFreezerLightOff();

    /**
     * Indicate that the Fridge light is on
     */
    public abstract void turnFridgeLightOn();

    /**
     * Indicate that the Fridge light is off
     */
    public abstract void turnFridgeLightOff();


    /**
     * Indicate that the  Freezer door is now closed
     */
    public abstract void freezerDoorClosed();

    /**
     * Indicate that the door is now open
     */
    public abstract void freezerDoorOpened();

    /**
     * Indicate that the door is now closed
     */
    public abstract void fridgeDoorOpened();

    /**
     * Indicate that the door is now open
     */
    public abstract void fridgeDoorClosed();

    /**
     * Indicating the freezer is in a cooling state.
     * */


    public abstract boolean isFreezerCooling();

    public abstract boolean isFridgeCooling();

    /**
     * Indicating the freezer is in an idle state.
     * */
    public abstract void freezerIdle();

    /**
     * Indicating the fridge is in a cooling state
     * */
    public abstract void fridgeCooling();

    /**
     * Indicating the fridge is in an idle state.
     * */
    public abstract void fridgeIdle();

    public void randomizeStartingTemps() {
    }

    public void freezerDisplayCurrentTime() {
    }

    public abstract void fridgeDisplayCurrentTime();
}