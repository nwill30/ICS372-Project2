import javax.swing.event.EventListenerList;
import java.util.EventListener;

public class FreezerDoorCloseManager {
    private EventListenerList listenerList = new EventListenerList();
    private static FreezerDoorCloseManager instance;

    /**
     * Private to make it a singleton
     * */
    private FreezerDoorCloseManager(){}

    /**
     * Returns the only instance of the class
     * @return the only instance of the class
     * */
    public static FreezerDoorCloseManager instance(){
        if(instance == null){
            instance = new FreezerDoorCloseManager();
        }
        return instance;
    }

    /**
     * Adds a listner
     * @param  listener an objec that wants to listen to the event
     * */
    public void addFreezerDoorCloseistener(FreezerDoorCloseListner listener){
        listenerList.add(FreezerDoorCloseListner.class,listener);
    }

    /**
     * Removes a listener
     *
     * @param listener the object to be removed
     * */
    public void removeFreezerCloseListener(FreezerDoorCloseListner listener) {
        listenerList.remove(FreezerDoorCloseListner.class, listener);
    }

    /**
     * Handles the request to open the door.
     *
     * #param event the sometheing object
     * */
    public void processEvent(FreezerDoorCloseEvent event){
        EventListener[] listeners = listenerList.getListeners(FreezerDoorCloseListner.class);
        for(int index = 0; index < listeners.length;index++){
            ((FreezerDoorCloseListner) listeners[index]).freezerDoorClosed(event);
        }
    }
}
