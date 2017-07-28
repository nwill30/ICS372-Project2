import java.util.EventListener;

public interface FreezerDoorCloseListner extends EventListener{
    public void freezerDoorClosed(FreezerDoorCloseEvent event);
}
