public class FreezerOpenState extends FreezerState implements FreezerDoorCloseListener, FreezerTempListener, TimerTickedListener{
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
        TimerTickedManager.instance().addTimerTickedListener(instance);
        display.turnFreezerLightOn();
        display.freezerDoorOpened();


    }

    @Override
    public void leave() {
        FreezerDoorCloseManager.instance().removeFreezerCloseListener(instance);
        FreezerTempManager.instance().removeFreezerTempListener(instance);
        TimerTickedManager.instance().removeTimerTickedListener(instance);
    }

    @Override
    public void freezerDoorClosed(FreezerDoorCloseEvent event) {
          context.changeFreezerCurrentState(FreezerClosedState.instance());
    }

    @Override
    public void temperatureSet(FreezerTempEvent event) {
         FreezerTempState.instance().run();
    }

    @Override
    public void timerTicked(TimerTickedEvent event) {
        display.displayCurrentTime();
    }
}
