import javax.swing.event.EventListenerList;
import java.util.EventListener;

public class FridgeDoorOpenManager {
    private EventListenerList listenerList = new EventListenerList();
    private static FridgeDoorOpenManager instance;

    /**
     * Private to make it a singleton
     * */
    private FridgeDoorOpenManager(){}

    /**
     * Returns the only instance of the class
     * @return the only instance of the class
     * */
    public static FridgeDoorOpenManager instance(){
        if(instance == null){
            instance = new FridgeDoorOpenManager();
        }
        return instance;
    }

    /**
     * Adds a listner
     *
     * @param listner and objec tthat wants to listend to the event
     * */
    public void addFridgeDoorOpenListner(FridgeDoorOpenListener listner){
        listenerList.add(FridgeDoorOpenListener.class, listner);
    }

    /**
     * Removes a listner
     *
     * @param listener
     * the object to be remove
     * */
    public void removeFridgeDoorOpenListener(FridgeDoorOpenListener listener){
        listenerList.remove(FridgeDoorOpenListener.class, listener);
    }

    /**
     * Handles the request to open the door
     *
     * @param event the something event object
     * */
    public void processEvent(FridgeDoorOpenEvent event){
        EventListener[] listeners = listenerList.getListeners(FridgeDoorOpenListener.class);
        for(int index = 0; index <listeners.length;index++){
            ((FridgeDoorOpenListener) listeners[index]).fridgeDoorOpened(event);
        }
    }
}
