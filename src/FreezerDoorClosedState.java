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
/**
 * Represents the state of the microwave when the door is closed. When the
 * microwave has its door closed, the run method of this class is called. After
 * that, when an event occurs, the handle method is invoked.
 */
public class FreezerDoorClosedState extends FridgeState implements
        CookRequestListener, FridgeDoorOpenListner {
    private static FreezerDoorClosedState instance;

    /**
     * Private to make it a singleton
     */
    private FreezerDoorClosedState() {
    }

    /**
     * When the Microwave leaves from this state, this method is called to
     * remove the state as a listener for the appropriate events.
     */
    @Override
    public void leave() {

        DoorOpenManager.instance().removeDoorOpenListener(instance);
    }

    /**
     * returns the instance
     *
     * @return this object
     */
    public static FreezerDoorClosedState instance() {
        if (instance == null) {
            instance = new FreezerDoorClosedState();
        }
        return instance;
    }

    /**
     * handle door open event
     *
     */

    @Override
    public void fridgeDoorOpened(FridgeDoorOpenEvent event) {
        context.changeCurrentState(FreezerDoorClosedState.instance());
    }

    /**
     * handle cook request
     *
     */

    @Override
    public void cookRequested(CookRequestEvent event) {
        context.changeCurrentState(CookingState.instance());
    }

    /**
     * initialize the state
     *
     */
    @Override
    public void run() {
        CookRequestManager.instance().addCookRequestListener(instance);
        DoorOpenManager.instance().addDoorOpenListener(instance);
        display.doorClosed();
        display.turnLightOff();
        display.notCooking();
        display.displayTimeRemaining(0);
        Timer.instance().setTimeValue(0);
    }
}