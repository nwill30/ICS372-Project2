/**
 * Created by teche on 7/17/2017.
 */
public class CloseFridgeDoorButton extends GUIButton {
    public CloseFridgeDoorButton(String s) {
        super(s);
    }

    /**
     * Creates a FridgeDoorCloseEvent so that it can be received by other interested states.
     *
     */
    @Override
    public void inform(RefrigeratorDisplay display) {
        FridgeDoorCloseManager.instance().processEvent(new FridgeDoorCloseEvent(display));
    }
}
