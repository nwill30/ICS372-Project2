public class FreezerTempState extends FreezerState implements FreezerDoorCloseListener, FreezerDoorOpenListener{

    private static FreezerTempState instance;
    private FreezerTempState(){

    }

    @Override
    public void run() {
        FreezerDoorCloseManager.instance().addFreezerDoorCloseListener(instance);
        FreezerDoorOpenManager.instance().addFreezerDoorOpenListner(instance);
        display.displayFreezerTemp();
    }

    @Override
    public void leave() {
        FreezerDoorCloseManager.instance().removeFreezerCloseListener(instance);
        FreezerDoorOpenManager.instance().removeFreezerDoorOpenListener(instance);
    }

    public static FreezerTempState instance(){
        if(instance == null){
            instance = new FreezerTempState();
        }
        return instance;
    }

    @Override
    public void freezerDoorClosed(FreezerDoorCloseEvent event) {
        context.changeFreezerCurrentState(FreezerClosedState.instance());
    }

    @Override
    public void freezerDoorOpened(FreezerDoorOpenEvent even) {
        context.changeFreezerCurrentState(FreezerOpenState.instance());
    }
}
