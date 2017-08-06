/**
 * Simulates the freezer closed state
 */
public class FreezerClosedState extends FreezerState implements
        FreezerDoorOpenListener, FreezerTempListener, FreezerTimerTickedListener{
    /**
     * private constructor and instance method for singleton.
     */
    private static FreezerClosedState instance;

    private FreezerClosedState(){}

    /**
     * Things that need to be handled before leaving this state.
     */
    @Override
    public void leave(){
        FreezerDoorOpenManager.instance().removeFreezerDoorOpenListener(instance);
        FreezerTempManager.instance().removeFreezerTempListener(instance);
        FreezerTimerTickedManager.instance().removeTimerTickedListener(instance);

    }

    /**
     * Singleton instance method
     * @return
     */
    public static FreezerClosedState instance(){
        if(instance == null){
            instance = new FreezerClosedState();
        }
        return instance;
    }

    /**
     * Things that need to be handled when this state is being transitioned to.
     */
    @Override
    public void run() {
        FreezerDoorOpenManager.instance().addFreezerDoorOpenListner(instance);
        FreezerTempManager.instance().addFreezerTempListener(instance);
        FreezerTimerTickedManager.instance().addTimerTickedListener(instance);
        display.freezerDoorClosed();
        display.turnFreezerLightOff();



    }

    /**
     * Event handler for opening the freezer door.
     * @param event
     */
    @Override
    public void freezerDoorOpened(FreezerDoorOpenEvent event) {
        context.changeFreezerCurrentState(FreezerOpenState.instance());
    }

    /**
     * Event handler for temperature settings.
     * @param event
     */
    @Override
    public void temperatureSet(FreezerTempEvent event) {
        FreezerTempState.instance().run();
    }

    @Override
    public void timerTicked(FreezerTimerTickedEvent event) {
        display.freezerDisplayCurrentTime();
    }
}
