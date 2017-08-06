/**
 * represents the state of the freezer door being open.
 */
public class FreezerOpenState extends FreezerState implements FreezerDoorCloseListener, FreezerTempListener, FreezerTimerTickedListener{
    /**
     * private constructor and instance of method provide a singleton structure.
     */
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

    /**
     * All the operations that need to be completed as the freezer open state is being transitioned into
     */
    @Override
    public void run() {
        FreezerDoorCloseManager.instance().addFreezerDoorCloseListener(instance);
        FreezerTempManager.instance().addFreezerTempListener(instance);
        FreezerTimerTickedManager.instance().addTimerTickedListener(instance);
        display.turnFreezerLightOn();
        display.freezerDoorOpened();


    }

    /**
     * All the operations that need to be completed as the freezer open state is being left.
     */
    @Override
    public void leave() {
        FreezerDoorCloseManager.instance().removeFreezerCloseListener(instance);
        FreezerTempManager.instance().removeFreezerTempListener(instance);
        FreezerTimerTickedManager.instance().removeTimerTickedListener(instance);
    }

    /**
     * Event handler for door closing.
     *
     */
    @Override
    public void freezerDoorClosed(FreezerDoorCloseEvent event) {
          context.changeFreezerCurrentState(FreezerClosedState.instance());
    }

    /**
     * Event handler for door opening.
     * @param event
     */
    @Override
    public void temperatureSet(FreezerTempEvent event) {
         FreezerTempState.instance().run();
    }


    /**
     * Event handler for a timer ticked event.
     * @param event
     */
    @Override
    public void timerTicked(FreezerTimerTickedEvent event) {
        display.freezerDisplayCurrentTime();
    }
}
