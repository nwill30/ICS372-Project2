import javax.swing.event.EventListenerList;
import java.util.EventListener;

public class FreezerDoorOpenManager {
    private EventListenerList listnerList = new EventListenerList();
    private static FreezerDoorOpenManager instance;

    /**
     * Private to make it a singleton
     * */
    private FreezerDoorOpenManager(){}

    /**
     * Returns the only instance of the class
     * @return the only instance of the class
     * */
    public static FreezerDoorOpenManager instance(){
        if(instance == null){
            instance = new FreezerDoorOpenManager();
        }
        return instance;
    }

    /**
     * Adds a listner
     *
     * @param listner and objec tthat wants to listend to the event
     * */
    public void addFreezerDoorOpenListner(FreezerDoorOpenListner listner){
        listnerList.add(FreezerDoorOpenListner.class, listner);
    }

    /**
     * Removes a listner
     *
     * @param listner
     * the object to be remove
     * */
    public void removeFreezerDoorOpenListner(FreezerDoorOpenListner listner){
        listnerList.remove(FreezerDoorOpenListner.class, listner);
    }

    /**
     * Handles the request to open the door
     *
     * @param event the something event object
     * */
    public void processEvent(FreezerDoorOpenEvent event){
        EventListener[] listeners = listnerList.getListeners(FreezerDoorOpenListner.class);
        for(int index = 0; index <listeners.length;index++){
            ((FreezerDoorOpenListner) listeners[index]).freezerDoorOpened(event);
        }
    }
}
