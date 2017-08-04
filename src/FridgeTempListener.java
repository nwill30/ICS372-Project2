import java.util.EventListener;
public interface FridgeTempListener extends EventListener {
    void temperatureSet(FridgeTempEvent event);

}
