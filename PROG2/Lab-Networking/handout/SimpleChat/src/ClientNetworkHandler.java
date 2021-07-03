
import java.util.Observable;

public class ClientNetworkHandler extends Observable implements Runnable {

    private boolean running = true; // flag to stop thread
    private String message = "";    // Never modify yourself! use printMessage
    @SuppressWarnings("unused")
    private String serverAddress;
    @SuppressWarnings("unused")
    private int serverPort;

    // TODO create and configure Logger and replace all System.out.println
    
    public ClientNetworkHandler(String serverAddress, int serverPort) {
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
    }

    public void run() {
        System.out.println("Client Network Handler started");
        // TODO connect to server
        while (running) {
            // TODO handle chat protocol
        }
        // TODO disconnect from server
        System.out.println("Client Network Handler stopped");
    }

    public void stop() {
        running = false;
    }

    /*
     * This method is called by the GUI to send a message
     */
    public synchronized void sendMessage(String message) {
        // TODO Send message to server, if connection is open.
    }

    /* 
     * Call this to print a received message in the GUI text pane
     */
    @SuppressWarnings("unused")
    private synchronized void printMessage(String message) {
        this.message = message;
        setChanged();
        notifyObservers();
    }

    /* 
     * This method is called by the GUI-Thread
     */
    public synchronized String getMessage() {
        return message;
    }
}
