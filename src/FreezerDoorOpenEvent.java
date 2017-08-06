import java.util.EventObject;

/**
 * simulates the event of a freezer door opening
 */
public class FreezerDoorOpenEvent extends EventObject{
    public FreezerDoorOpenEvent(Object source){super(source);}
}
