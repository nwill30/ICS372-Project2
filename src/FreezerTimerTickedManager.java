/**
 *
 * @author Brahma Dathan and Sarnath Ramnath
 * @Copyright (c) 2010

 * Redistribution and use with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - the use is for academic purpose only
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *   - Neither the name of Brahma Dathan or Sarnath Ramnath
 *     may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * The authors do not make any claims regarding the correctness of the code in this module
 * and are not responsible for any loss or damage resulting from its use.
 */
import java.util.EventListener;

import javax.swing.event.EventListenerList;

/**
 * This class manages the listeners associated with states that must act when
 * the timer ticks.
 *
 * @author Brahma Dathan
 *
 */
public class FreezerTimerTickedManager {
    private EventListenerList listenerList = new EventListenerList();
    private static FreezerTimerTickedManager instance;

    /**
     * The constructor is private to ensure that it is a singleton
     */
    private FreezerTimerTickedManager() {
    }

    /**
     * Returns the only instance of the class
     *
     * @return the only instance of the class
     */
    public static FreezerTimerTickedManager instance() {
        if (instance == null) {
            instance = new FreezerTimerTickedManager();
        }
        return instance;
    }

    /**
     * Adds a listener
     *
     * @param listener
     *            the listener to be added
     */
    public void addTimerTickedListener(FreezerTimerTickedListener listener) {
        listenerList.add(FreezerTimerTickedListener.class, listener);
    }

    /**
     * Removes a listener
     *
     * @param listener
     *            the listener to be removed
     */
    public void removeTimerTickedListener(FreezerTimerTickedListener listener) {
        listenerList.remove(FreezerTimerTickedListener.class, listener);
    }

    /**
     * Processes the event by calling the timerTicked method of each listener
     *
     * @param event
     */
    public void processEvent(FreezerTimerTickedEvent event) {
        EventListener[] listeners = listenerList
                .getListeners(FreezerTimerTickedListener.class);
        for (int index = 0; index < listeners.length; index++) {
            ((FreezerTimerTickedListener) listeners[index]).timerTicked(event);
        }
    }
}
