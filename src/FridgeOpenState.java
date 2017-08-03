public class FridgeOpenState extends FridgeState implements FridgeDoorCloseListener{
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
        display.turnFridgeLightOn();
        display.fridgeDoorOpened();

    }

    @Override
    public void leave() {
        FridgeDoorCloseManager.instance().removeFridgeCloseListener(instance);
    }


    @Override
    public void fridgeDoorClosed(FridgeDoorCloseEvent event) {
        context.changeFridgeCurrentState(FridgeClosedState.instance());
    }
}
