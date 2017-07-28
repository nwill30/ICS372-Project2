
public class FreezerClosedState extends FreezerState implements
        FreezerDoorOpenListner{
    private static FreezerClosedState instance;

    private FreezerClosedState(){}

    @Override
    public void leave(){
        FreezerDoorOpenManager.instance().removeFreezerDoorOpenListner(instance);

    }

    public static FreezerClosedState instance(){
        if(instance == null){
            instance = new FreezerClosedState();
        }
        return instance;
    }


    public void processFreezerOpen(){
        context.changeFreezerCurrentState(FreezerOpenState.instance());
    }
    @Override
    public void run() {
        display.freezerDoorClosed();
        display.turnFreezerLightOff();

    }

    @Override
    public void freezerDoorOpened(FreezerDoorOpenEvent even) {
        context.changeFreezerCurrentState(FreezerOpenState.instance());
    }
}
