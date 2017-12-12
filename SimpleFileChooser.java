
/*
 Java Swing, 2nd Edition
 By Marc Loy, Robert Eckstein, Dave Wood, James Elliott, Brian Cole
 ISBN: 0-596-00408-7
 Publisher: O'Reilly 
 */
//SimpleFileChooser.java
//A simple file chooser to see what it takes to make one of these work.
//
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

public class SimpleFileChooser extends JFrame {

	public SimpleFileChooser() {
		super("Smart Home Configuration");
		setSize(350, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		Container c = getContentPane();
		c.setLayout(new FlowLayout());

		JButton openButton = new JButton("Open");
		JButton saveButton = new JButton("Save");
		JButton dirButton = new JButton("Pick Dir");
		JButton configButton = new JButton("Find optimum Configurations");
		final JLabel headerLabel = new JLabel("", JLabel.CENTER);
		// headerLabel.setText("Control in action: JList");

		// List of available EV
		final JLabel evLabel = new JLabel("Electric Vehicle");
		final DefaultListModel evName = new DefaultListModel();
		evName.addElement("BMWi8");
		evName.addElement("BMWi3");
		evName.addElement("BMW 530e");
		evName.addElement("BMW");
		evName.addElement("none");
		final JList evList = new JList(evName);
		evList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		evList.setSelectedIndex(0);
		evList.setVisibleRowCount(3);
		JScrollPane evListScrollPane = new JScrollPane(evList);

		// list of Location
		final JLabel lacLabel = new JLabel("Location");
		final DefaultListModel locName = new DefaultListModel();
		locName.addElement("Dresden");
		locName.addElement("Munich");
		locName.addElement("Berlin");
		locName.addElement("Hamburg");
		final JList locList = new JList(locName);
		locList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		locList.setSelectedIndex(0);
		locList.setVisibleRowCount(3);
		JScrollPane locListScrollPane = new JScrollPane(locList);

		// list of Battery
		final JLabel batLabel = new JLabel("Battery: ");
		final JLabel capLabel = new JLabel("capacity");
		JTextField capField = new JTextField(2);
		// final JLabel cap = new JLabel("(kw)");
		final JLabel outLabel = new JLabel("Output Power");
		// final JLabel cap2 = new JLabel("(kw/h)");
		JTextField outField = new JTextField(2);

		// list of Battery
		final JLabel photovoltaicSystemLabel = new JLabel("Photovoltaic system: ");
		final JLabel outLabel2 = new JLabel("Output Power");
		JTextField outField2 = new JTextField(2);
		// final JLabel cap22 = new JLabel("(kw/h)");

		// Base Load
		final JLabel basedLoadLabel = new JLabel("Base Load");
		final JLabel plugInLabel = new JLabel("Plug in intervall");
		JButton openplugInButton = new JButton("Open");
		JButton saveplugInButton = new JButton("Save");

		// Create a file chooser that opens up as an Open dialog
		openButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				JFileChooser chooser = new JFileChooser();
				chooser.setMultiSelectionEnabled(true);
				int option = chooser.showOpenDialog(SimpleFileChooser.this);
				if (option == JFileChooser.APPROVE_OPTION) {
					File[] sf = chooser.getSelectedFiles();
					String filelist = "nothing";
					if (sf.length > 0)
						filelist = sf[0].getName();
					for (int i = 1; i < sf.length; i++) {
						filelist += ", " + sf[i].getName();
					}
					evLabel.setText("You chose " + filelist);
				} else {
					evLabel.setText("You canceled.");
				}
			}
		});

		// Create a file chooser that opens up as a Save dialog
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				JFileChooser chooser = new JFileChooser();
				int option = chooser.showSaveDialog(SimpleFileChooser.this);
				if (option == JFileChooser.APPROVE_OPTION) {
					evLabel.setText("You saved "
							+ ((chooser.getSelectedFile() != null) ? chooser.getSelectedFile().getName() : "nothing"));
				} else {
					evLabel.setText("You canceled.");
				}
			}
		});

		// Create a file chooser that allows you to pick a directory
		// rather than a file
		dirButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				JFileChooser chooser = new JFileChooser();
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int option = chooser.showOpenDialog(SimpleFileChooser.this);
				if (option == JFileChooser.APPROVE_OPTION) {
					evLabel.setText("You opened "
							+ ((chooser.getSelectedFile() != null) ? chooser.getSelectedFile().getName() : "nothing"));
				} else {
					evLabel.setText("You canceled.");
				}
			}
		});

		// // list of Battery
		// final JLabel batLabel = new JLabel("Battery");
		// final JLabel capLabel = new JLabel("capacity");
		// JTextField capField = new JTextField(5);
		// final JLabel cap = new JLabel("kw/h");
		// final JLabel outLabel = new JLabel("Output Power");
		// JTextField outField = new JTextField(5);
		c.add(evLabel);
		c.add(evListScrollPane);
		c.add(lacLabel);
		c.add(locListScrollPane);
		c.add(batLabel);
		c.add(capLabel);
		c.add(capField);
		// c.add(cap);
		c.add(outLabel);
		c.add(outField);
		// c.add(cap2);
		c.add(photovoltaicSystemLabel);
		c.add(outLabel2);
		c.add(outField2);
		c.add(basedLoadLabel);
		c.add(openButton);
		c.add(saveButton);
		c.add(plugInLabel);
		c.add(openplugInButton);
		c.add(saveplugInButton);
		c.add(configButton);
		

	}

	public static void main(String args[]) {
		SimpleFileChooser sfc = new SimpleFileChooser();
		sfc.setVisible(true);
	}
}
