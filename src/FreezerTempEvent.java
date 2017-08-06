import java.util.EventObject;

/**
 * Event representing change in desired freezer temp
 */
public class FreezerTempEvent extends EventObject {
    public FreezerTempEvent(Object source) {
        super(source);
    }
}
