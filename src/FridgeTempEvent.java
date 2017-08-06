import java.util.EventObject;

/**
 * Event representing change in the desired temp for the fridge.
 */
public class FridgeTempEvent extends EventObject {
    public FridgeTempEvent(Object source) {
        super(source);
    }
}
