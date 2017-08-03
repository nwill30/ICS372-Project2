public class FridgeClosedState extends FridgeState implements
        FridgeDoorOpenListner{
    private static FridgeClosedState instance;

    private FridgeClosedState(){}

    public void leave(){
        FridgeDoorOpenManager.instance().removeFridgeDoorOpenListner(instance);
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
        display.fridgeDoorClosed();
        display.turnFridgeLightOff();
    }

    @Override
    public void fridgeDoorOpened(FridgeDoorOpenEvent even) {
        context.changeFridgeCurrentState(FridgeOpenState.instance());
    }

}

