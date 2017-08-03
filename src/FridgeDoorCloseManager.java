import javax.swing.event.EventListenerList;
import java.util.EventListener;

public class FridgeDoorCloseManager {
    private EventListenerList listenerList = new EventListenerList();
    private static FridgeDoorCloseManager instance;

    /**
     * Private to make it a singleton
     * */
    private FridgeDoorCloseManager(){}

    /**
     * Returns the only instance of the class
     * @return the only instance of the class
     * */
    public static FridgeDoorCloseManager instance(){
        if(instance == null){
            instance = new FridgeDoorCloseManager();
        }
        return instance;
    }

    /**
     * Adds a listner
     * @param  listener an objec that wants to listen to the event
     * */
    public void addFridgeDoorCloseistener(FridgeDoorCloseListner listener){
        listenerList.add(FridgeDoorCloseListner.class,listener);
    }

    /**
     * Removes a listener
     *
     * @param listener the object to be removed
     * */
    public void removeFridgeCloseListener(FridgeDoorCloseListner listener) {
        listenerList.remove(FridgeDoorCloseListner.class, listener);
    }

    /**
     * Handles the request to open the door.
     *
     * #param event the sometheing object
     * */
    public void processEvent(FridgeDoorCloseEvent event){
        EventListener[] listeners = listenerList.getListeners(FridgeDoorCloseListner.class);
        for(int index = 0; index < listeners.length;index++){
            ((FridgeDoorCloseListner) listeners[index]).fridgeDoorClosed(event);
        }
    }
}
