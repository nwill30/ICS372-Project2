import java.util.EventListener;

public interface FreezerDoorOpenListner extends EventListener{
    public void freezerDoorOpened(FreezerDoorOpenEvent even);
}
