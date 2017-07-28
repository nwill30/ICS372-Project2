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
import java.util.Observer;

/**
 * The context is an obserer for the clock and stores the context info for
 * states
 *
 */
public class Context {
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
}