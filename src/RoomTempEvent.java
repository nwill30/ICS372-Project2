import java.util.EventObject;

/**
 * Event representing change in room temp
 */
public class RoomTempEvent extends EventObject {
    public RoomTempEvent(Object source) {
        super(source);
    }
}
