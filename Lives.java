import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.plaf.synth.SynthSpinnerUI;

public class Lives extends JPanel {
	int sysNum;
	ArrayList<Systems> sysArray = new ArrayList<Systems>();
	 ArrayList<Integer> connectArray = new ArrayList<Integer>();

	public Lives(ArrayList<Systems> systemArray, ArrayList<Integer> wonChromosom) {
		sysNum = systemArray.size();
		sysArray = systemArray;
		connectArray = wonChromosom;

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.setColor(Color.BLUE);
		Dimension d = this.getSize();
		int lastx = 0;
		int lasty = 0;
		ArrayList<Position> posArray = new ArrayList<Position>();

		System.out.println(d.height);
		// g.fillRect(5 * 20, 25 * 20, 100, 30);
		// g.fill3DRect(5 * 20, 25 * 20, 100, 30, true);
		// g.fill3DRect(d.width/2, d.height/2, 10, 30, true);

		for (int i = 0; i < sysNum; i++) {
			Position pos = new Position();
			String name = sysArray.get(i).getSysName();
			g.setColor(Color.BLUE);
			g.fill3DRect(100 + (i * 40), 100 + (i * 70), 200, 30, true);
			pos.setX(100 + (i * 40));
			pos.setY(100 + (i * 70));
			g.setColor(Color.BLACK);
			g.drawString(name, (100 + (i * 40) + 30), 100 + (i * 70) + 20);
			// g.drawLine(lastx + 100, lasty + 30, 100 + (i * 40) + 60, 100 + (i * 70));
			posArray.add(pos);
		}
		
		 g.drawLine(posArray.get(1).x+ 100, posArray.get(1).y+ 30, posArray.get(2).x+ 60, posArray.get(2).y);
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(800, 600);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame(Lives.class.getSimpleName());
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				Systems system = new Systems();
				ArrayList<Systems> systemsArray = new ArrayList<Systems>();
				try {
					systemsArray = system.getValue();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				final Lives lives = new Lives(systemsArray);
				frame.add(lives);
				frame.pack();
				frame.setVisible(true);

			}
		});
	}
}