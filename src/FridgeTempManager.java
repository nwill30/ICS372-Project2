/**
 * @author Sam Kemp
 *
 */

import java.util.EventListener;

import javax.swing.event.EventListenerList;

public class FridgeTempManager {
    private EventListenerList listenerList = new EventListenerList();
    private static FridgeTempManager instance;

    /**
     * Private to make it a singleton
     */
    private FridgeTempManager() {
    }

    /**
     * Returns the only instance of the class
     *
     * @return the only instance of the class
     */
    public static FridgeTempManager instance() {
        if (instance == null) {
            instance = new FridgeTempManager();
        }
        return instance;
    }

    /**
     * Adds a listener
     *
     * @param listener
     *            an object that wants to listen to the event
     */
    public void addFridgeTempListener(FridgeTempListener listener) {
        listenerList.add(FridgeTempListener.class, listener);
    }

    /**
     * Removes a listener
     *
     * @param listener
     *            the object to be removed
     */
    public void removeFridgeTempListener(FridgeTempListener listener) {
        listenerList.remove(FridgeTempListener.class, listener);
    }


    public void processEvent(FridgeTempEvent event) {
        EventListener[] listeners = listenerList
                .getListeners(FridgeTempListener.class);
        for (int index = 0; index < listeners.length; index++) {
            ((FridgeTempListener) listeners[index]).temperatureSet(event);
        }
    }
}
