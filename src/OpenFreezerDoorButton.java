/**
 * Created by teche on 7/17/2017.
 */
public class OpenFreezerDoorButton extends GUIButton {
    public OpenFreezerDoorButton(String s) {
        super(s);
    }

    @Override
    public void inform(RefrigeratorDisplay display) {
        FreezerDoorOpenManager.instance().processEvent(new FreezerDoorOpenEvent(display));
    }
}
