import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Gui extends JFrame {
	int height;
	int width;
	public static void main(String[] args) throws FileNotFoundException, IOException {
		Systems sys = new Systems();
		ArrayList<Systems> sysArray = new ArrayList<Systems>();
		sysArray = sys.getValue();
		new Gui(sysArray);
		JLabel label = new JLabel("Hallo");
		JTextField tf = new JTextField("Hallo World");
		MyListener l = new MyListener(tf, label);
	}
	public Gui(ArrayList<Systems> sysArray) {
		super("System");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(new FlowLayout());
		ArrayList<JLabel> arrayLabel = new ArrayList<JLabel>();
		Graphics g;
		
		

		// JTextField textField = new JTextField("Zahl eingeben");
		// getContentPane().add(textField);
		//

		for (int i = 0; i < sysArray.size(); i++) {

			JLabel label = new JLabel(sysArray.get(i).getSysName());
			arrayLabel.add(label);
			
			JButton button = new JButton(sysArray.get(i).getSysName());
			getContentPane().add(button);
			// button.addActionListener(new MyListener(textField, label));

		}

		// JLabel label1 = arrayLabel.get(1);
		// label1.setVerticalTextPosition(JLabel.BOTTOM);
		// JLabel label2 = arrayLabel.get(2);
		// label1.setVerticalTextPosition(JLabel.CENTER);
		// JLabel label1 = new JLabel("Image and Text", JLabel.BOTTOM);
		// label1.setHorizontalTextPosition(JLabel.CENTER);
		// getContentPane().add(label1);
		// getContentPane().add(label2);
		height = arrayLabel.get(1).getHeight();
		width = arrayLabel.get(1).getWidth();
		int height2 = arrayLabel.get(2).getHeight();
		int width2 = arrayLabel.get(2).getWidth();
		//g.drawLine(height, width, height2, width2);

		setLocation(250, 190);
		setSize(300, 90);
		setVisible(true);
	}

	@Override
	public void paint(Graphics g) {
		
		
		// Get the current size of this component
		
		
		Dimension d = this.getSize();

		// draw in black
		g.setColor(Color.BLACK);
		// draw a centered horizontal line
		g.drawLine(0, height , width, d.height / 2);
	}

	
}

class MyListener implements ActionListener {
	private JTextField textField;
	private JLabel label;

	public MyListener(JTextField tf, JLabel l) {
		textField = tf;
		label = l;
	}

	public void actionPerformed(ActionEvent ae) {
		String text = textField.getText();

		String ergebnisText = "";
		try {
			double zahl = Double.parseDouble(text);
			double quadrat = zahl * zahl;
			ergebnisText = "" + quadrat;
		} catch (NumberFormatException ex) {
			ergebnisText = "keine Zahl";
		}

		label.setText(ergebnisText);
		textField.setText("Zahl eingeben");

	}
}