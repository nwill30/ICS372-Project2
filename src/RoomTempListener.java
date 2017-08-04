import java.util.EventListener;
public interface RoomTempListener extends EventListener {
    void temperatureSet(RoomTempEvent event);

}
