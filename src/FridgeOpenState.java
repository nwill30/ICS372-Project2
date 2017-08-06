/**
 * Represents the state when the fridge is open and the states it can move to.
 */
public class FridgeOpenState extends FridgeState implements FridgeDoorCloseListener, FridgeTempListener,
        RoomTempListener,FridgeTimerTickedListener{
    /**
     * private singleton style constructors and access
     */
    private static FridgeOpenState instance;
    static{
        instance = new FridgeOpenState();
    }

    public static FridgeOpenState instance(){
        return instance;
    }


    /**
     * This method adds the proper listeners and completes the necessary actions on the display when
     * the FridgeOpenState is transitioned to.
     */
    @Override
    public void run() {
        FridgeDoorCloseManager.instance().addFridgeDoorCloseListener(instance);
        FridgeTempManager.instance().addFridgeTempListener(instance);
        RoomTempManager.instance().addRoomTempListener(instance);
        FridgeTimerTickedManager.instance().addTimerTickedListener(instance);
        display.turnFridgeLightOn();
        display.fridgeDoorOpened();

    }

    /**
     * This method removes the proper listeners and completes necessary actions to leave the FridgeOpenState properly.
     */
    @Override
    public void leave() {
        FridgeDoorCloseManager.instance().removeFridgeCloseListener(instance);
        FridgeTempManager.instance().removeFridgeTempListener(instance);
        RoomTempManager.instance().removeRoomTempListener(instance);
        FridgeTimerTickedManager.instance().removeTimerTickedListener(instance);
    }


    /**
     * Facilitates the transition to the close state.
     *
     */
    @Override
    public void fridgeDoorClosed(FridgeDoorCloseEvent event) {
        context.changeFridgeCurrentState(FridgeClosedState.instance());
    }

    /**
     * Runs the proper function for the desired temperature setting state.
     */
    @Override
    public void temperatureSet(FridgeTempEvent event) {
        FridgeTempState.instance().run();
    }

    /**
     *Runs the proper function for the room temp setting state.
     */
    @Override
    public void temperatureSet(RoomTempEvent event) {
        RoomTempState.instance().run();
    }

    /**
     * Records and handles details of whenever a TimeTickedEventOccurs
     */
    @Override
    public void timerTicked(FridgeTimerTickedEvent event) {
        display.fridgeDisplayCurrentTime();
    }
}
