/**
 * @author Sam Kemp
 *
 */

import java.util.EventListener;

import javax.swing.event.EventListenerList;

public class FreezerTempManager {
    private EventListenerList listenerList = new EventListenerList();
    private static FreezerTempManager instance;

    /**
     * Private to make it a singleton
     */
    private FreezerTempManager() {
    }

    /**
     * Returns the only instance of the class
     *
     * @return the only instance of the class
     */
    public static FreezerTempManager instance() {
        if (instance == null) {
            instance = new FreezerTempManager();
        }
        return instance;
    }

    /**
     * Adds a listener
     *
     * @param listener
     *            an object that wants to listen to the event
     */
    public void addFreezerTempListener(FreezerTempListener listener) {
        listenerList.add(FreezerTempListener.class, listener);
    }

    /**
     * Removes a listener
     *
     * @param listener
     *            the object to be removed
     */
    public void removeFreezerTempListener(FreezerTempListener listener) {
        listenerList.remove(FreezerTempListener.class, listener);
    }


    public void processEvent(FreezerTempEvent event) {
        EventListener[] listeners = listenerList
                .getListeners(FreezerTempListener.class);
        for (int index = 0; index < listeners.length; index++) {
            ((FreezerTempListener) listeners[index]).temperatureSet(event);
        }
    }
}
