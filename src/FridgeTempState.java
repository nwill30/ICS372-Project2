public class FridgeTempState extends FridgeState implements FridgeDoorCloseListener, FridgeDoorOpenListener{

    private static FridgeTempState instance;
    private FridgeTempState(){

    }
    public static FridgeTempState instance(){
        if(instance == null){
            instance = new FridgeTempState();
        }
        return instance;
    }

    @Override
    public void run() {
        FridgeDoorCloseManager.instance().addFridgeDoorCloseListener(instance);
        FridgeDoorOpenManager.instance().addFridgeDoorOpenListener(instance);
        display.displayFridgeTemp();

    }

    @Override
    public void leave() {
        FridgeDoorCloseManager.instance().removeFridgeCloseListener(instance);
        FridgeDoorOpenManager.instance().removeFridgeDoorOpenListener(instance);
    }


    @Override
    public void fridgeDoorClosed(FridgeDoorCloseEvent event) {
        context.changeFridgeCurrentState(FridgeClosedState.instance());
    }

    @Override
    public void fridgeDoorOpened(FridgeDoorOpenEvent event) {
        context.changeFridgeCurrentState(FridgeOpenState.instance());
    }
}
