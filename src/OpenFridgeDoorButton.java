/**
 * Created by teche on 7/17/2017.
 */
public class OpenFridgeDoorButton extends GUIButton {
    /**
     * Crates the button with the required label
     *
     * @param string
     *            the label
     */
    public OpenFridgeDoorButton(String string) {
        super(string);
    }

    /**
     * Tell the context to send it to the right listener
     */
    @Override
    public void inform(RefrigeratorDisplay source) {
        FridgeDoorOpenManager.instance().processEvent(new FridgeDoorOpenEvent(source));
    }
}
