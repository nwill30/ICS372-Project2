import java.util.EventObject;

/**
 * Event representing a fridge door opening.
 */
public class FridgeDoorOpenEvent extends EventObject{
    public FridgeDoorOpenEvent(Object source){super(source);}
}
