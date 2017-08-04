public class FridgeClosedState extends FridgeState implements
        FridgeDoorOpenListener, FridgeTempListener, RoomTempListener {
    private static FridgeClosedState instance;

    private FridgeClosedState(){}

    public void leave(){
        FridgeDoorOpenManager.instance().removeFridgeDoorOpenListener(instance);
        FridgeTempManager.instance().removeFridgeTempListener(instance);
        RoomTempManager.instance().removeRoomTempListener(instance);
    }

    public static FridgeClosedState instance(){
        if(instance == null){
            instance = new FridgeClosedState();
        }
        return instance;
    }


    public void processFridgeOpen(){
        context.changeFridgeCurrentState(FridgeOpenState.instance());
    }
    @Override
    public void run() {
        FridgeDoorOpenManager.instance().addFridgeDoorOpenListener(instance);
        FridgeTempManager.instance().addFridgeTempListener(instance);
        RoomTempManager.instance().addRoomTempListener(instance);
        display.fridgeDoorClosed();
        display.turnFridgeLightOff();
    }

    @Override
    public void fridgeDoorOpened(FridgeDoorOpenEvent even) {
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
}

