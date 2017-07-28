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
public class Context implements Observer {
    private static RefrigeratorDisplay refrigeratorDisplay;
    private FreezerState currentState;
    private static Context instance;


    public static enum Events {
        FREEZER_CLOSED_EVENT,FREEZER_OPEN_EVENT, FRIDGE_CLOSED_EVENT, FRIDGE_OPEN_EVENT,
        SET_FREEZER_TEMP_EVENT, SET_FRIDGE_TEMP_EVENT,SET_ROOM_TEMP_EVENT
    };

    /**
     * Make it a singleton
     */
    private Context() {
        instance = this;
        refrigeratorDisplay = RefrigeratorDisplay.instance();
        currentState = FreezerClosedState.instance();
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
        instance.changeCurrentState(FreezerClosedState.instance());
    }

    /**
     * Called from the states to change the current state
     *
     * @param nextState
     *            the next state
     */
    public void changeCurrentState(FreezerState nextState) {
        currentState = nextState;
        nextState.run();
    }

    public void processEvent(Object arg){
        currentState.handle(arg);
    }

    @Override
    public void update(Observable o, Object arg) {
        currentState.handle(arg);
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