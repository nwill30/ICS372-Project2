public class FreezerOpenState extends FreezerState implements FreezerDoorCloseListener{
    private static FreezerOpenState instance;
    static{
        instance = new FreezerOpenState();
    }

    public static FreezerOpenState instance(){
        return instance;
    }

//    public void processDoorClose(){
//        context.changeFreezerCurrentState(FreezerClosedState.instance());
//    }

    @Override
    public void run() {
        FreezerDoorCloseManager.instance().addFreezerDoorCloseListener(this);
        display.turnFreezerLightOn();
        display.freezerDoorOpened();

    }

    @Override
    public void leave() {
        FreezerDoorCloseManager.instance().removeFreezerCloseListener(instance);
    }

    @Override
    public void freezerDoorClosed(FreezerDoorCloseEvent event) {
          context.changeFreezerCurrentState(FreezerClosedState.instance());
    }
}
