/**
 * @(#)ExBoxFrame.java
 *
 * JFC ExBox application
 *
 * @author K.Rege
 * @version	1.00 2014/2/3
 * @version	1.01 2016/8/2 
 * @version	2.00 2017/8/30 Test
 * @version	2.01 2018/2/5 AutoScale  
 * @version	2.02 2018/3/12 Reconnect (inspired by S. Kunz)
 */

package ch.zhaw.ads;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;
import org.junit.runner.Description;


public class ExBoxFrame extends JFrame implements ActionListener, ItemListener {
	private static double SCALE = 1;
	private JMenuItem connect, open, test, textView, graphicView;
	private JButton enter;
	private JTextField arguments;
	private JCheckBox reconnect;
	private JComboBox history;
	private JTextArea output;
	private JScrollPane scrollPane;	
	private CommandExecutor command;
	private boolean graphicOn;
	private GraphicPanel graphic;
	private String lastServer;

	public static void setFontSize(int size) {
		Set<Object> keySet = UIManager.getLookAndFeelDefaults().keySet();
		Object[] keys = keySet.toArray(new Object[keySet.size()]);
		for (Object key : keys) {
			if (key != null && key.toString().toLowerCase().contains("font")) {
				Font font = UIManager.getDefaults().getFont(key);
				if (font != null) {
					font = font.deriveFont((float) size);
					UIManager.put(key, font);
				}
			}
		}
	}
	
	private void initMenu() {
		JMenuBar menuBar = new JMenuBar();
		JMenu menuFile = new JMenu();
		JMenuItem menuFileExit = new JMenuItem();
		menuFile.setText("File");
		menuFileExit.setText("Exit");

		// Add action listener.for the menu	button
		menuFileExit.addActionListener(new	ActionListener() {
			public void	actionPerformed(ActionEvent	e) {
				ExBoxFrame.this.windowClosed();
			}
		});

		menuFile.add(menuFileExit);
		menuBar.add(menuFile);
		
		JMenu menuServer = new JMenu("Server");
		menuBar.add(menuServer);
		connect = new JMenuItem("Connect");
		connect.addActionListener(this);
		menuServer.add(connect);
		test = new JMenuItem("Test");
		test.addActionListener(this);
		menuServer.add(test);
		
		JMenu menuView = new JMenu("View");
		menuBar.add(menuView);
		textView = new JMenuItem("Text");
		textView.addActionListener(this);
		menuView.add(textView);
		graphicView = new JMenuItem("Graphic");
		graphicView.addActionListener(this);
		menuView.add(graphicView);
		
		open = new JMenuItem("Open...");
		open.addActionListener(this);
		menuFile.insert(open, 0);	
		setJMenuBar(menuBar);				
	}

	private void initComponents() {
		setLayout(new BorderLayout());
		output = new JTextArea();
		scrollPane = new JScrollPane(output);
		add(BorderLayout.CENTER, scrollPane);

		Panel panel = new Panel(new BorderLayout());
		arguments = new JTextField();
		arguments.addActionListener(this);
		panel.add(BorderLayout.CENTER, arguments);
		enter = new JButton("enter");
		enter.addActionListener(this);
		panel.add(BorderLayout.EAST, enter);
		reconnect = new JCheckBox("reconnect",true);
		panel.add(BorderLayout.WEST,reconnect);
		history = new JComboBox();
		history.addItemListener(this);
		panel.add(BorderLayout.SOUTH, history);
		add(BorderLayout.SOUTH, panel);		
	}
	
	/**
	 * get default path for file open dialog
	 */
	String getPathCompiled() {
		String pathtocompiled = getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
		pathtocompiled = pathtocompiled.replace("%20", " ").replace("/",
				File.separator);
		pathtocompiled += getClass().getPackage().getName().replace(".",
				File.separator);
		if (System.getProperty("os.name").toLowerCase().indexOf("win") >= 0) {
			pathtocompiled = pathtocompiled.substring(1);
		}
		return pathtocompiled;
	}

	/**
	 * The constructor
	 */
	public ExBoxFrame() throws Exception {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception ex) {
			ex.printStackTrace();
		}	
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		if (screenSize.getWidth() > 1920) {
			SCALE = 2;
		}
		setFontSize((int) (11 * SCALE));	
		setSize(new	Dimension((int) (400 * SCALE), (int) (400 * SCALE)));
		setTitle("ExBox");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initMenu();
		initComponents();
	}

	private void error(String s) {
		output.append("\nERROR:" + s + "\n");
	}

	private void interpret(String args) throws Exception {
		if (reconnect.isSelected() && lastServer != null) {
			command = ServerFactory.createServer(lastServer);
		}
		if (!arguments.getText().equals(history.getItemAt(0))
				&& !arguments.getText().equals(history.getSelectedItem())) {
			history.insertItemAt(arguments.getText(), 0);
		}
		if (command == null) {
			error("no Server connected");
		} else {
			String res = command.execute(args);
			if (graphicOn) {
				graphic.setFigure(res);
			} else {
				output.append(res);
			}
		}
	}
	
	private void setGraphicView() {
		if (graphicOn) {
			return;
		}
		remove(scrollPane);
		graphic = new GraphicPanel();
		output.removeNotify();
		add(BorderLayout.CENTER, graphic);
		graphicOn = true;
		validate();
		repaint();
	}

	private void setTextView() {
		if (!graphicOn) {
			return;
		}
		remove(graphic);
		add(BorderLayout.CENTER, scrollPane);
		graphicOn = false;
		validate();
		repaint();
	}
	
	private String openFileDialog(String startDirectory, String pattern) {
		FileDialog	fd = new FileDialog(this, "Open");
		if (pattern != null) {
			fd.setFile(pattern);
		}
		if (startDirectory != null) {
			fd.setDirectory(startDirectory);
		}
		fd.setVisible(true);
		return  fd.getDirectory() + fd.getFile();	
	}
	
	private void testCommand() throws Exception {
		final java.util.List<String> failed = new LinkedList<String>();
		final java.util.List<String> finished = new LinkedList<String>();
		
		JUnitCore runner = new JUnitCore();
		
		String name = openFileDialog(getPathCompiled(), "*Test.class");
		runner.addListener(new RunListener() {
			@Override
			public void testFinished(Description description) throws Exception {
				finished.add(description.getDisplayName());			
			}		

			@Override
			public void testFailure(Failure failure) throws Exception {
				failed.add(failure.getDescription().getDisplayName());
			}	
		});
		Class testClass = ServerFactory.loadClass(name);
		Result result = runner.run(testClass);
	    
		for (String test : finished) {
			if (!failed.contains(test)) {
				output.append(test + ": OK\n");
			}
		}
		for (Failure failure : result.getFailures()) {
			output.append(failure.toString() + " ERROR\n");
		}
		output.append(
				"TESTS PASSED: "
						+ (result.wasSuccessful()
								? "OK \u263a"
								: result.getFailures().size() + " ERRORS")
								+ "\n");
	}
	
	private void connectCommand() throws Exception {
		String name = openFileDialog(getPathCompiled(), "*Server.class");
		command = ServerFactory.createServer(name);
		lastServer = name;
		String fullClassName = command.getClass().getName();
		String simpleClassName = fullClassName.substring(
				fullClassName.lastIndexOf('.') + 1);
		setTitle("ExBox connected to " + simpleClassName);		
			
	}
	
	private void openFile()  throws Exception {
		String name = openFileDialog(null, null);

		BufferedReader br = new BufferedReader(
				new InputStreamReader(new FileInputStream(name), "ISO-8859-1"));
		StringBuffer b = new StringBuffer();
		String line;
		while ((line = br.readLine()) != null) {
			b.append(line);
			b.append('\n');
		}
		interpret(b.toString());	
	}

	public void	itemStateChanged(ItemEvent e) {
		try {
			arguments.setText((String) e.getItem());
			interpret(arguments.getText());
		} catch (Throwable ex) {
			error(ex.toString());		
		}	
	}

	public void	actionPerformed(ActionEvent	e) {
		try {
			if ((e.getSource() == arguments) || (e.getSource() == enter)) {
				interpret(arguments.getText());
			} else if (e.getSource() == connect) {
				connectCommand();
			} else if (e.getSource() == test) {
				testCommand();
			} else if (e.getSource() == open) {
				openFile();
			} else if (e.getSource() == textView) {
				setTextView();
			} else if (e.getSource() == graphicView) {
				setGraphicView();
			}
		} catch (Throwable ex) {
			ex.printStackTrace();
			error(ex.toString());
		}
	}

	/**
	 * Shutdown	procedure when run as an application.
	 */
	protected void windowClosed() {
		System.exit(0);
	}
}
