
public class FreezerClosedState extends FreezerState implements
        FreezerDoorOpenListener {
    private static FreezerClosedState instance;

    private FreezerClosedState(){}

    @Override
    public void leave(){
        FreezerDoorOpenManager.instance().removeFreezerDoorOpenListener(instance);

    }

    public static FreezerClosedState instance(){
        if(instance == null){
            instance = new FreezerClosedState();
        }
        return instance;
    }


//    public void processFreezerOpen(){
//        context.changeFreezerCurrentState(FreezerOpenState.instance());
//    }
    @Override
    public void run() {
        FreezerDoorOpenManager.instance().addFreezerDoorOpenListner(instance);
        display.freezerDoorClosed();
        display.turnFreezerLightOff();

    }

    @Override
    public void freezerDoorOpened(FreezerDoorOpenEvent event) {
        context.changeFreezerCurrentState(FreezerOpenState.instance());
    }
}
