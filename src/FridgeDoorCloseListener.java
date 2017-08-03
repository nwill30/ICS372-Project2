import java.util.EventListener;

public interface FridgeDoorCloseListener extends EventListener{
    public void fridgeDoorClosed(FridgeDoorCloseEvent event);
}
