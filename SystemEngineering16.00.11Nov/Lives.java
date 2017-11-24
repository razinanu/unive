import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
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
import java.util.Random;

public class Lives extends JPanel {
	int sysNum;
	ArrayList<Systems> sysArray = new ArrayList<Systems>();
	ArrayList<Interface> connectArray = new ArrayList<Interface>();

	public Lives(ArrayList<Systems> systemArray, ArrayList<Interface> wonChromosom) {
		sysNum = systemArray.size();
		sysArray = systemArray;
		connectArray = wonChromosom;

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.setColor(Color.BLUE);
		Dimension d = this.getSize();
		int x = 0;
		int y = 0;
		ArrayList<Position> posArray = new ArrayList<Position>();
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		// g.fillRect(5 * 20, 25 * 20, 100, 30);
		// g.fill3DRect(5 * 20, 25 * 20, 100, 30, true);
		// g.fill3DRect(d.width/2, d.height/2, 10, 30, true);
		int w = getWidth();
		int h = getHeight();

		for (int i = 0; i < sysNum; i++) {
			Position pos = new Position();
			String name = sysArray.get(i).getSysName();
			g.setColor(Color.BLUE);
			Random r = new Random();
			int Low = 100;
			int High = 400;
			int randomInt = r.nextInt(High - Low) + Low;

			x = 200;
			y = (getWidth() / sysNum) * i;
			g.fill3DRect(x, y, 150, 30, true);
			pos.setX(x);
			pos.setY(y);
			pos.setName(name);
			g.setColor(Color.BLACK);
			g.drawString(name, (x + 30), y + 20);
			// g.drawLine(lastx + 100, lasty + 30, 100 + (i * 40) + 60, 100 + (i * 70));
			posArray.add(pos);
		}

		Position posFirst = new Position();
		Position posSecond = new Position();

		for (int i = 0; i < connectArray.size(); i++) {
			if (connectArray.get(i).provideEnergy == 1) {
				posFirst = findName(connectArray.get(i).getFirstSystem().getSysName(), posArray);
				posSecond = findName(connectArray.get(i).getSecondSystem().getSysName(), posArray);
				Point sw = new Point(posFirst.x + 40, posFirst.y + 30);
				Point ne = new Point(posSecond.x + 60 + 10 * i, posSecond.y);
				g.setColor(Color.RED);
				g2.draw(new Line2D.Double(sw, ne));
				drawArrowHead(g2, ne, sw, Color.DARK_GRAY);
				// drawArrowHead(g2, sw, ne, Color.red);

			}
			if (connectArray.get(i).consumeEnergy == 1) {
				posFirst = findName(connectArray.get(i).getSecondSystem().getSysName(), posArray);
				posSecond = findName(connectArray.get(i).getFirstSystem().getSysName(), posArray);
				Point sw = new Point(posFirst.x + 40, posFirst.y + 30);
				Point ne = new Point(posSecond.x + 60 + 10 * i, posSecond.y);
				g.setColor(Color.RED);
				g2.draw(new Line2D.Double(sw, ne));
				drawArrowHead(g2, ne, sw, Color.GREEN);

			}
		}
		int c = 0;

	}

	private Position findName(String name, ArrayList<Position> posArray) {
		Position pos = new Position();
		pos.setName("Null");
		pos.setX(0);
		pos.setY(0);
		for (int i = 0; i < posArray.size(); i++) {

			if (posArray.get(i).name == name) {

				return posArray.get(i);

			}

		}
		return pos;

	}

	private void drawArrowHead(Graphics2D g2, Point tip, Point tail, Color color) {

		double phi = Math.toRadians(40);
		int barb = 20;
		g2.setPaint(color);
		double dy = tip.y - tail.y;
		double dx = tip.x - tail.x;
		double theta = Math.atan2(dy, dx);
		// System.out.println("theta = " + Math.toDegrees(theta));
		double x, y, rho = theta + phi;
		for (int j = 0; j < 2; j++) {
			x = tip.x - barb * Math.cos(rho);
			y = tip.y - barb * Math.sin(rho);
			g2.draw(new Line2D.Double(tip.x, tip.y, x, y));
			rho = theta - phi;
		}
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

			}
		});
	}
}