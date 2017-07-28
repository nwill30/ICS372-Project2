import java.util.EventListener;

public interface FridgeDoorOpenListner extends EventListener{
    public void fridgeDoorOpened(FridgeDoorOpenEvent event);
}
