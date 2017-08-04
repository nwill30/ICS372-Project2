/**
 * @author Sam Kemp
 *
 */

import java.util.EventListener;

import javax.swing.event.EventListenerList;

public class RoomTempManager {
    private EventListenerList listenerList = new EventListenerList();
    private static RoomTempManager instance;

    /**
     * Private to make it a singleton
     */
    private RoomTempManager() {
    }

    /**
     * Returns the only instance of the class
     *
     * @return the only instance of the class
     */
    public static RoomTempManager instance() {
        if (instance == null) {
            instance = new RoomTempManager();
        }
        return instance;
    }

    /**
     * Adds a listener
     *
     * @param listener
     *            an object that wants to listen to the event
     */
    public void addRoomTempListener(RoomTempListener listener) {
        listenerList.add(RoomTempListener.class, listener);
    }

    /**
     * Removes a listener
     *
     * @param listener
     *            the object to be removed
     */
    public void removeRoomTempListener(RoomTempListener listener) {
        listenerList.remove(RoomTempListener.class, listener);
    }


    public void processEvent(RoomTempEvent event) {
        EventListener[] listeners = listenerList
                .getListeners(RoomTempListener.class);
        for (int index = 0; index < listeners.length; index++) {
            ((RoomTempListener) listeners[index]).temperatureSet(event);
        }
    }
}
