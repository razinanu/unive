import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Period;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.Random;
import java.util.spi.CalendarNameProvider;

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
				ArrayList<Chromosom> chromArray = new ArrayList<Chromosom>();
				Chromosom chrom = new Chromosom();

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
				ArrayList<Interface> interfaceArray = new ArrayList<Interface>(lengthChromosome);
				//for (int j = 0; j < 3; j++) {

					// just the top Triangular matrix

					int c = 1;
					int l = 0;
					for (int i = 0; i < systemsArray.size(); i++) {
						Interface inf = new Interface();
						inf.setFirstSystem(systemsArray.get(l));
						inf.setSecondSystem(systemsArray.get(c));
						interfaceArray.add(inf);
						if (c == systemsArray.size() - 1) {

							l++;
							c = l + 1;
						} else {
							c++;
						}

					}

					randomValue(interfaceArray);
					for (int i = 0; i < interfaceArray.size(); i++) {
						System.out.print("interface " + interfaceArray.get(i).getFirstSystem().getSysName());
						System.out.print(" " + interfaceArray.get(i).getSecondSystem().getSysName());
						System.out.print(" " + interfaceArray.get(i).getProvideEnergy());
						System.out.println(" " + interfaceArray.get(i).getConsumeEnergy());

					}

					double tariff = 0.25;
					chrom.inf = interfaceArray;
					chrom.arraySys = systemsArray;
					chrom = evaluate(chrom, tariff);
					chromArray.add(chrom);

					final Lives lives = new Lives(systemsArray, interfaceArray);
					frame.add(lives);
					frame.pack();
					frame.setVisible(true);
				//}
			}
		});
	}

	private static void randomValue(ArrayList<Interface> array) {
		Random random = new Random();
		Interface inf = new Interface();
		for (int index = 0; index < array.size(); index++) {
			if (random.nextDouble() < 0.5) {

				inf = array.get(index);
				inf.setProvideEnergy(1);

			} else {
				inf.setProvideEnergy(0);
			}
			if (random.nextDouble() < 0.5) {

				inf = array.get(index);
				inf.setConsumeEnergy(1);

			} else {
				inf.setConsumeEnergy(0);
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

	public static Chromosom evaluate(Chromosom chorom, double tariff) {
		double cost = 0;
		double penantly = 0;
		double overallConsume = 0;
		double timeConsume = 0;
		double remainConsume = 0;
		double overallCost = 0;
		double green = 0;
		double comfort = 0;
		ArrayList<Interface> wonChromosom = new ArrayList<Interface>();
		ArrayList<Double> listCons = new ArrayList<Double>();
		for (int i = 0; i < chorom.getInf().size(); i++) {

			penantly += checkPenaltyProvide(chorom.getInf(), i) + checkPenaltyConsume(chorom.getInf(), i);
			System.out.println("Penantly: " + penantly);

			if (chorom.getInf().get(i).getProvideEnergy() == 1 && checkPenaltyProvide(chorom.getInf(), i) == 0
					&& chorom.getInf().get(i).secondSystem.getSaveCap() != 0) {
				listCons = checkProvideEnergy(chorom.getInf().get(i).firstSystem, chorom.getInf().get(i).secondSystem);
				remainConsume = listCons.get(0);
				overallConsume += listCons.get(1);
				overallCost += listCons.get(2);
				green += listCons.get(3);
				System.out.println("Consume if"
						+ checkProvideEnergy(chorom.getInf().get(i).firstSystem, chorom.getInf().get(i).secondSystem)
								.get(1));
				chorom.getInf().get(i).secondSystem.setSaveCap(remainConsume);

			}
			if (chorom.getInf().get(i).getConsumeEnergy() == 1 && checkPenaltyConsume(chorom.getInf(), i) == 0) {

				listCons = checkProvideEnergy(chorom.getInf().get(i).secondSystem, chorom.getInf().get(i).firstSystem);
				remainConsume = listCons.get(0);
				overallConsume += listCons.get(1);
				overallCost += listCons.get(2);
				green += listCons.get(3);
				chorom.getInf().get(i).firstSystem.setSaveCap(remainConsume);
				if (Objects.equals("Vehicle", chorom.getInf().get(i).firstSystem.getSysName())) {
					comfort = remainConsume;
				}

			}

		}

		chorom.setCost(overallCost);
		chorom.setComfort(comfort);
		chorom.setGreen(green);

		System.out.println("Overall Consume is: " + overallConsume);
		System.out.println("Overall Cost is: " + overallCost);
		System.out.println("comfort is: " + comfort);
		System.out.println("Penantly is: " + penantly);
		System.out.println("Green is: " + green);

		return chorom;

	}

	private static Calendar getCalendar(String time) throws ParseException {

		String startT = time;
		Date date = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").parse(startT);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		return calendar;
	}

	private static ArrayList<Double> checkProvideEnergy(Systems fisrtSystem, Systems secondSystems) {

		double consume = 0;
		double tariff = 0.25;
		double cost = 0;
		double green = 0;
		double remainConsume = secondSystems.getSaveCap();

		ArrayList<Double> list = new ArrayList<Double>();

		try {
			Calendar consumStart = Calendar.getInstance();
			consumStart = getCalendar(secondSystems.gettStart());
			Calendar consumEnd = Calendar.getInstance();
			consumEnd = getCalendar(secondSystems.gettEnd());
			Calendar provideStart = Calendar.getInstance();
			provideStart = getCalendar(fisrtSystem.gettStart());
			Calendar provideEnd = Calendar.getInstance();
			provideEnd = getCalendar(fisrtSystem.gettEnd());

			if (consumStart.getTime().after(provideStart.getTime())
					&& consumStart.getTime().before(provideEnd.getTime()) && secondSystems.getSaveCap() != 0) {
				if (consumEnd.getTime().before(provideEnd.getTime())) {

					long seconds = (consumEnd.getTimeInMillis() - consumStart.getTimeInMillis()) / 1000;
					int hours = (int) (seconds / 3600);
					consume = hours * fisrtSystem.getPowerProvide();
					System.out.println("Grid Consume " + consume);
					if (consume > secondSystems.getSaveCap()) {
						remainConsume = 0;
						consume = secondSystems.getSaveCap();
					} else {

						remainConsume = secondSystems.getSaveCap() - consume;
						// System.out.println("Hour Grid " + hours + " Consume " + remainConsume);
						// System.out.println("Remain Consume Grid " + remainConsume);
					}

				} else {
					long seconds = (provideEnd.getTimeInMillis() - consumStart.getTimeInMillis()) / 1000;
					int hours = (int) (seconds / 3600);
					consume = hours * fisrtSystem.getPowerProvide();
					// System.out.println("PV Consume " + consume);

					if (consume > secondSystems.getSaveCap()) {

						consume = secondSystems.getSaveCap();
						remainConsume = 0;
					} else {
						// System.out.println("Hours PV " + hours);
						remainConsume = secondSystems.getSaveCap() - consume;
						// System.out.println("Remain Consume PV " + remainConsume);
					}

				}

			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

		if (Objects.equals("Grid", fisrtSystem.getSysName())) {
			cost = consume * tariff;

		} else {
			green = consume;
		}
		list.add(remainConsume);
		list.add(consume);
		list.add(cost);
		list.add(green);

		return list;

	}

	private static int checkPenaltyProvide(ArrayList<Interface> chromosomArray, int i) {

		int penantly = 0;

		boolean second = chromosomArray.get(i).getProvideEnergy() == 1
				&& (chromosomArray.get(i).firstSystem.getPowerProvide() == -1
						|| chromosomArray.get(i).secondSystem.getPowerConsume() == -1);

		if (second) {
			penantly += 100;
		}
		return penantly;
	}

	private static int checkPenaltyConsume(ArrayList<Interface> chromosomArray, int i) {

		int penantly = 0;
		boolean first = chromosomArray.get(i).getConsumeEnergy() == 1
				&& (chromosomArray.get(i).secondSystem.getPowerProvide() == -1
						|| chromosomArray.get(i).firstSystem.getPowerConsume() == -1);
		if (first) {
			penantly += 100;
		}

		return penantly;
	}

}
