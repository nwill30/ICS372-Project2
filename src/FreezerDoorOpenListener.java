import java.util.EventListener;

public interface FreezerDoorOpenListener extends EventListener{
    public void freezerDoorOpened(FreezerDoorOpenEvent even);
}
