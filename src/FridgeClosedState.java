public class FridgeClosedState extends RefrigeratorState {
    private static FridgeClosedState instance;
    static{
        instance = new FridgeClosedState();
    }

    public static FridgeClosedState instance(){
        return instance;
    }

    public void processFridgeOpen(){
        context.changeCurrentState(FridgeOpenState.instance());
    }
    @Override
    public void run() {
        display.fridgeDoorClosed();
        display.turnFridgeLightOff();

    }

    @Override
    public void handle(Object event) {
        if(event.equals(RefrigeratorContext.Events.FRIDGE_OPEN_EVENT)){
            processFridgeOpen();
        }
    }
}

