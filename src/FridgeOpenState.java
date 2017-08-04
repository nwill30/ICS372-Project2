public class FridgeOpenState extends FridgeState implements FridgeDoorCloseListener, FridgeTempListener,RoomTempListener{
    private static FridgeOpenState instance;
    static{
        instance = new FridgeOpenState();
    }

    public static FridgeOpenState instance(){
        return instance;
    }

    public void processDoorClose(){
        context.changeFridgeCurrentState(FridgeClosedState.instance());
    }

    @Override
    public void run() {
        FridgeDoorCloseManager.instance().addFridgeDoorCloseListener(instance);
        FridgeTempManager.instance().addFridgeTempListener(instance);
        RoomTempManager.instance().addRoomTempListener(instance);
        display.turnFridgeLightOn();
        display.fridgeDoorOpened();

    }

    @Override
    public void leave() {
        FridgeDoorCloseManager.instance().removeFridgeCloseListener(instance);
        FridgeTempManager.instance().removeFridgeTempListener(instance);
        RoomTempManager.instance().removeRoomTempListener(instance);
    }


    @Override
    public void fridgeDoorClosed(FridgeDoorCloseEvent event) {
        context.changeFridgeCurrentState(FridgeClosedState.instance());
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
