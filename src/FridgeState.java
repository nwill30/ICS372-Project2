

public abstract class FridgeState {
    protected static Context context;
    protected static RefrigeratorDisplay display;

    /**
     * Initialzies the context and display
     */
    protected FridgeState() {
        context = Context.instance();
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
    public abstract void leave();
}
