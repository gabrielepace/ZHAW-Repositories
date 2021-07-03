
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.InputStream;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

public class Client {
    
    private static Logger logger = Logger.getLogger("client");
    /*
     * Program initialization and start
     * gets the server address and port number as command line parameters
     */
    public static void main(String[] args) throws IOException {
        String serverAddress = "127.0.0.1";
        int serverPort = 22243;
        // parse arguments
        switch (args.length) {
        case 2:
            serverPort = Integer.parseInt(args[1]);
        case 1:
            serverAddress = args[0];
        case 0:
            break;
        default:
            throw new IllegalArgumentException("Parameters: <Server> <Port>");
        }
        // initialize Logger
        InputStream config = Client.class.getResourceAsStream("log.properties");
        LogManager.getLogManager().readConfiguration(config);
        logger.setUseParentHandlers(false);
        logger.setLevel(Level.ALL);
        Handler consoleHandler = new ConsoleHandler();
        consoleHandler.setFormatter(new SimpleFormatter());
        consoleHandler.setLevel(Level.FINE);
        logger.addHandler(consoleHandler);
        FileHandler fileHandler = new FileHandler("client-%u-%g.log", true);
        fileHandler.setFormatter(new SimpleFormatter());
        fileHandler.setLevel(Level.ALL);
        logger.addHandler(fileHandler);
        
        // init & start Network Handler Thread and open connection
        ClientNetworkHandler networkHandler = 
                new ClientNetworkHandler(serverAddress, serverPort);
        new Thread(networkHandler).start();
        
        // init GUI
        new ClientView(networkHandler);
    }
}


@SuppressWarnings("serial")
class ClientView extends JFrame implements Observer {

    private ClientNetworkHandler networkHandler;
    private JTextPane textPane;
    private Document doc;
    private JTextArea textArea;

    public ClientView(ClientNetworkHandler networkHandler) {
        super("Chat Client");
        this.networkHandler = networkHandler;
        Container pane = getContentPane();
        pane.setLayout(new BorderLayout());

        textPane = new JTextPane();
        textPane.setBorder(new TitledBorder("Messages"));
        textPane.setEditable(false);
        doc = textPane.getDocument();
        try {
            doc.insertString(doc.getLength(), "Waiting for Messages\n", null);
        } catch (BadLocationException e) {;}
        JScrollPane scrollPane = new JScrollPane(textPane);
        scrollPane.setPreferredSize(new Dimension(300, 500));
        pane.add(scrollPane, BorderLayout.CENTER);

        textArea = new JTextArea();
        textArea.setBorder(new TitledBorder("Your Message"));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                    // Enter/Return Key pressed-> send message
                    String text = ClientView.this.textArea.getText();
                    if (text !=null & text.endsWith("\n")) {
                        text = text.substring(0, text.length()-1);
                    }
                    ClientView.this.networkHandler.sendMessage(text);
                    ClientView.this.textArea.setText("");  // clear TextField
                }
            }
        });
        scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(300, 100));
        pane.add(scrollPane, BorderLayout.SOUTH);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                ClientView.this.networkHandler.stop();
                ClientView.this.dispose();
                //System.exit(0);
            }
        });
        pack();
        setVisible(true);
        networkHandler.addObserver(this);
        update(networkHandler, null);
    }



    /*
     * This method is called when notified by the Observable
     * and will get the message from NetworkHandler and append it to 
     * the messages area. 
     * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
     */
    public void update(Observable model, Object arg) {
        final String message = networkHandler.getMessage();
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    ClientView.this.doc.insertString(
                            ClientView.this.doc.getLength(),
                            message + "\n",
                            null);
                } catch (BadLocationException e) {;}
            }
        });
    }
}