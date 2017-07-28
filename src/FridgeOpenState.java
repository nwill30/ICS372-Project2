public class FridgeOpenState extends FridgeState {
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
