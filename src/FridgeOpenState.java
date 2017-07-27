public class FridgeOpenState extends RefrigeratorState {
    private static FridgeOpenState instance;
    static{
        instance = new FridgeOpenState();
    }

    public static FridgeOpenState instance(){
        return instance;
    }

    public void processDoorClose(){
        context.changeCurrentState(FridgeClosedState.instance());
    }

    @Override
    public void run() {
        display.turnFridgeLightOn();
        display.fridgeDoorOpened();

    }

    @Override
    public void handle(Object event) {
        if(event.equals(RefrigeratorContext.Events.FRIDGE_CLOSED_EVENT)){
            processDoorClose();
        }
    }
}
