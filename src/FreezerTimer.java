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
import java.util.Observable;
import java.util.Observer;

/**
 * The Timer for the microwave
 *
 */
public class FreezerTimer implements Observer {
    private static FreezerTimer instance;
    private int timeValue = 0;

    /**
     * For singleton
     */
    private FreezerTimer() {
        instance = this;
        Clock.instance().addObserver(instance);
    }

    /**
     * For singleton pattern
     *
     * @return the instance
     */
    public static FreezerTimer instance() {
        if (instance == null) {
            instance = new FreezerTimer();
        }
        return instance;
    }

    /**
     * Set the time for the timer
     *
     * @param value
     *            timer initial value
     */
    public void setTimeValue(int value) {
        this.timeValue = value;
    }

//    /**
////     * Add to the time value
////     *
////     * @param value
////     *            extra time for the time value
////     */
//    public void addTimeValue(int value) {
//        timeValue += value;
//    }
//
//    /**
//     * Get the remaining time
//     *
//     * @return
//     */
    public int getTimeValue() {
        return timeValue;
    }

    /**
     * Get the clock tick and process it
     */
    @Override
    public void update(Observable clock, Object value) {
        FreezerTimerTickedManager.instance().processEvent(new FreezerTimerTickedEvent(instance));
    }
}