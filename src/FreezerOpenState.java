public class FreezerOpenState extends FreezerState {
    private static FreezerOpenState instance;
    static{
        instance = new FreezerOpenState();
    }

    public static FreezerOpenState instance(){
        return instance;
    }

    public void processDoorClose(){
        context.changeCurrentState(FreezerClosedState.instance());
    }

    @Override
    public void run() {
        display.turnFreezerLightOn();
        display.freezerDoorOpened();

    }

    @Override
    public void handle(Object event) {
        if(event.equals(FreezerContext.Events.FREEZER_CLOSED_EVENT)){
            processDoorClose();
        }
    }
}
