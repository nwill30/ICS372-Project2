import javax.swing.event.EventListenerList;
import java.util.EventListener;

public class FridgeDoorOpenManager {
    private EventListenerList listnerList = new EventListenerList();
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
    public void addFridgeDoorOpenListner(FridgeDoorOpenListner listner){
        listnerList.add(FridgeDoorOpenListner.class, listner);
    }

    /**
     * Removes a listner
     *
     * @param listner
     * the object to be remove
     * */
    public void removeFridgeDoorOpenListner(FridgeDoorOpenListner listner){
        listnerList.remove(FridgeDoorOpenListner.class, listner);
    }

    /**
     * Handles the request to open the door
     *
     * @param event the something event object
     * */
    public void processEvent(FridgeDoorOpenEvent event){
        EventListener[] listeners = listnerList.getListeners(FridgeDoorOpenListner.class);
        for(int index = 0; index <listeners.length;index++){
            ((FridgeDoorOpenListner) listeners[index]).fridgeDoorOpened(event);
        }
    }
}
