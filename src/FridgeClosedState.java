public class FridgeClosedState extends FridgeState implements
        FridgeDoorOpenListener {
    private static FridgeClosedState instance;

    private FridgeClosedState(){}

    public void leave(){
        FridgeDoorOpenManager.instance().removeFridgeDoorOpenListener(instance);
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
        FridgeDoorOpenManager.instance().addFridgeDoorOpenListner(instance);
        display.fridgeDoorClosed();
        display.turnFridgeLightOff();
    }

    @Override
    public void fridgeDoorOpened(FridgeDoorOpenEvent even) {
        context.changeFridgeCurrentState(FridgeOpenState.instance());
    }

}

