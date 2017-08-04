import java.util.EventListener;
public interface FreezerTempListener extends EventListener {
    void temperatureSet(FreezerTempEvent event);

}
