/**
 * Created by teche on 7/17/2017.
 */
public class CloseFreezerDoorButton extends GUIButton {
    public CloseFreezerDoorButton(String s) {
        super(s);
    }

    @Override
    public void inform(RefrigeratorDisplay display) {
        FreezerDoorCloseManager.instance().processEvent(new FreezerDoorCloseEvent(display));
    }
}
