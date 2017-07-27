
public class FreezerClosedState extends FreezerState {
    private static FreezerClosedState instance;
    static{
        instance = new FreezerClosedState();
    }

    public static FreezerClosedState instance(){
        return instance;
    }

    public void processFreezerOpen(){
        context.changeCurrentState(FreezerOpenState.instance());
    }
    @Override
    public void run() {
        display.freezerDoorClosed();
        display.turnFreezerLightOff();

    }

    @Override
    public void handle(Object event) {
        if(event.equals(FreezerContext.Events.FREEZER_OPEN_EVENT)){
            processFreezerOpen();
        }
    }
}
