/**
 * Created by teche on 7/17/2017.
 * Represents the button for closing the freezer door.
 */
public class CloseFreezerDoorButton extends GUIButton {
    public CloseFreezerDoorButton(String s) {
        super(s);
    }

    /**
     * Creates a FreezerDoorCloseEvent so that it can be received by other interested states.
     *
     */
    @Override
    public void inform(RefrigeratorDisplay display) {
        FreezerDoorCloseManager.instance().processEvent(new FreezerDoorCloseEvent(display));
    }
}
