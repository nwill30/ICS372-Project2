import java.util.EventListener;

public interface FreezerDoorCloseListener extends EventListener{
    public void freezerDoorClosed(FreezerDoorCloseEvent event);
}
