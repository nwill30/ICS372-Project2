/**
 * Represents the state when the fridge is closed and the states it can move to.
 */
public class FridgeClosedState extends FridgeState implements
        FridgeDoorOpenListener, FridgeTempListener, RoomTempListener,FridgeTimerTickedListener {
    private static FridgeClosedState instance;

    private FridgeClosedState(){}

    public void leave(){
        FridgeDoorOpenManager.instance().removeFridgeDoorOpenListener(instance);
        FridgeTempManager.instance().removeFridgeTempListener(instance);
        RoomTempManager.instance().removeRoomTempListener(instance);
        FridgeTimerTickedManager.instance().removeTimerTickedListener(instance);
    }

    public static FridgeClosedState instance(){
        if(instance == null){
            instance = new FridgeClosedState();
        }
        return instance;
    }


    @Override
    public void run() {
        FridgeDoorOpenManager.instance().addFridgeDoorOpenListener(instance);
        FridgeTempManager.instance().addFridgeTempListener(instance);
        RoomTempManager.instance().addRoomTempListener(instance);
        FridgeTimerTickedManager.instance().addTimerTickedListener(instance);
        display.fridgeDoorClosed();
        display.turnFridgeLightOff();
    }

    @Override
    public void fridgeDoorOpened(FridgeDoorOpenEvent event) {
        context.changeFridgeCurrentState(FridgeOpenState.instance());
    }

    @Override
    public void temperatureSet(FridgeTempEvent event) {
        FridgeTempState.instance().run();
    }

    @Override
    public void temperatureSet(RoomTempEvent event) {
        RoomTempState.instance().run();
    }

    @Override
    public void timerTicked(FridgeTimerTickedEvent event) {
        display.fridgeDisplayCurrentTime();
    }
}

