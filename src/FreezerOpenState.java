public class FreezerOpenState extends FreezerState implements FreezerDoorCloseListener, FreezerTempListener{
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
        FreezerDoorCloseManager.instance().addFreezerDoorCloseListener(instance);
        FreezerTempManager.instance().addFreezerTempListener(instance);
        display.turnFreezerLightOn();
        display.freezerDoorOpened();

    }

    @Override
    public void leave() {
        FreezerDoorCloseManager.instance().removeFreezerCloseListener(instance);
        FreezerTempManager.instance().removeFreezerTempListener(instance);
    }

    @Override
    public void freezerDoorClosed(FreezerDoorCloseEvent event) {
          context.changeFreezerCurrentState(FreezerClosedState.instance());
    }

    @Override
    public void temperatureSet(FreezerTempEvent event) {
         FreezerTempState.instance().run();
    }
}
