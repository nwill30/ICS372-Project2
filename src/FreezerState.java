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
/**
 * Super class for all states
 *
 *
 */
public abstract class FreezerState {
    protected static FreezerContext context;
    protected static RefrigeratorDisplay display;

    /**
     * Initialzies the context and display
     */
    protected FreezerState() {
        context = FreezerContext.instance();
        display = context.getDisplay();
    }

    /**
     * Initializes the state
     */
    public abstract void run();

    /**
     * When the Refrigerator leaves from this state, this method is called to
     * remove the state as a listener for the appropriate events.
     */
    public abstract void handle(Object event);
}
