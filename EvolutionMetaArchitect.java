import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class EvolutionMetaArchitect {

	public static void main(String[] args) throws IOException {
		SwingUtilities.invokeLater(new Runnable() {

			public void run() {
				JFrame frame = new JFrame(Lives.class.getSimpleName());
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				Systems system = new Systems();
				ArrayList<Systems> systemsArray = new ArrayList<Systems>();
				ArrayList<Integer> wonChromosom = new ArrayList<Integer>();
				try {
					systemsArray = system.getValue();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				int lengthChromosome = (((systemsArray.size() * systemsArray.size()) - systemsArray.size()) / 2);
				ArrayList<Integer> topInterfaceChromosom = new ArrayList<Integer>(lengthChromosome);
				ArrayList<Integer> downInterfaceChromosom = new ArrayList<Integer>(lengthChromosome);
				for (int i = 0; i < lengthChromosome; i++) {
					topInterfaceChromosom.add(0);
					downInterfaceChromosom.add(0);
					// System.out.println(interfaceChromosom.size());
					// System.out.println("lenght " + interfaceChromosom.size());
				}
				randomValue(topInterfaceChromosom);
				randomValue(downInterfaceChromosom);
				for (int i = 0; i < topInterfaceChromosom.size(); i++) {
					System.out.println("Interface " + topInterfaceChromosom.get(i).toString());
				}
				double tariff = 0.25;
				wonChromosom= evaluate(topInterfaceChromosom, systemsArray, tariff);

				final Lives lives = new Lives(systemsArray, wonChromosom);
				frame.add(lives);
				frame.pack();
				frame.setVisible(true);

			}
		});
	}

	private static void randomValue(ArrayList<Integer> array) {
		Random random = new Random();
		for (int index = 0; index < array.size(); index++) {
			if (random.nextDouble() < 0.5) {
				array.set(index, 1);

			} else {
				array.set(index, 0);
			}
		}
	}

	public static int factorial(int n) {
		if (n == 0) {
			return 1;
		}
		int fact = 1; // this will be the result
		for (int i = 1; i <= n; i++) {
			fact *= i;
		}
		return fact;
	}

	public static ArrayList<Integer> evaluate(ArrayList<Integer> chromosomArray, ArrayList<Systems> sysArray, double tariff) {
		double cost = 0;
		// TODO double comfort = 0;
		double penantly = 0;
		double consume = 0;
		double timeConsume = 0;
		ArrayList<Integer> wonChromosom = new ArrayList<Integer>();
		wonChromosom = chromosomArray;

		for (int i = 0; i < sysArray.size() - 1; i++) {

			System.out.println("Consume: " + sysArray.get((i + 1)).getPerformConsume() + " Provide: "
					+ sysArray.get(i).getPerformprovide());
			if (sysArray.get(i + 1).getPerformConsume() == -1 || sysArray.get(i).getPerformprovide() == -1) {
				penantly += 100;

			} else {
				if (sysArray.get(i).gettStart() < sysArray.get(i + 1).gettStart()) {
					if (sysArray.get(i).gettEnd() > sysArray.get(i + 1).gettStart()) {
						if (sysArray.get(i).gettEnd() > sysArray.get(i + 1).gettEnd())
							timeConsume = sysArray.get(i).gettEnd() - sysArray.get(i + 1).gettEnd();
						System.out.print("System: " + sysArray.get(i).getSysName());
						System.out.print(" Provide: " + sysArray.get(i).getPerformprovide());
						System.out.println(" STime i: " + sysArray.get(i).gettStart());
						System.out.println(" ETime i: " + sysArray.get(i).gettStart());
						System.out.print("System: " + sysArray.get(i).getSysName());
						System.out.print(" Provide: " + sysArray.get(i).getPerformprovide());
						System.out.println(" STime i+1: " + sysArray.get(i).gettStart());
						System.out.println(" ETime i+1: " + sysArray.get(i).gettStart());

					}
				}
				// double diff = sysArray.get(i).getPerformprovide() - sysArray.get((i +
				// 1)).getPerformConsume();
				// if (diff < 0) {
				// consume += sysArray.get(i).getPerformprovide();
				// sysArray.get(i).setPerformprovide(0);
				// sysArray.get((i + 1)).setPerformConsume(Math.abs(diff));
				// System.out.println("Consume: " + sysArray.get((i + 1)).getPerformConsume() +
				// " Provide: "
				// + sysArray.get(i).getPerformprovide() + " Consumption: " + consume);
				//
				// } else {
				// consume += sysArray.get((i + 1)).getPerformConsume();
				//
				// sysArray.get(i).setPerformprovide(diff);
				// sysArray.get((i + 1)).setPerformConsume(0);
				// System.out.println("Consume: " + sysArray.get((i + 1)).getPerformConsume() +
				// " Provide: "
				// + sysArray.get(i).getPerformprovide() + "Consumption: " + consume);
				// }
			}
		}
		cost = consume * tariff;
		System.out.println("Cost: " + cost);
		return wonChromosom;

	}

}
