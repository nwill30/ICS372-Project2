import java.util.EventListener;

public interface FridgeDoorCloseListner extends EventListener{
    public void fridgeDoorClosed(FridgeDoorCloseEvent event);
}
