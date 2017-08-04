public class RoomTempState extends FridgeState {

    private static RoomTempState instance;
    private RoomTempState(){

    }
    public static RoomTempState instance(){
        if(instance == null){
            instance = new RoomTempState();
        }
        return instance;
    }

    @Override
    public void run() {
        display.displayRoomTemp();

    }

    @Override
    public void leave() {

    }
}
