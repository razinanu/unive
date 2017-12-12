import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.moeaframework.core.Population;
import org.moeaframework.core.Solution;
import org.moeaframework.core.variable.EncodingUtils;
import org.moeaframework.problem.AbstractProblem;

public class EvolutionArchitect {

	private Solution initialChromosomes() throws FileNotFoundException,
			IOException {
		Systems system = new Systems();
		ArrayList<Systems> systemsArray = new ArrayList<Systems>();
		ArrayList<String> activeSys = new ArrayList<String>();

		Chromosom chrom = new Chromosom();
		Population p = new Population();

		try {

			systemsArray = system.getValue();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

		// random set of version of systems in initial Chromosome
		for (int c = 0; c < 24; c++) {
			String activeSystem = "";
			for (int i = 0; i < systemsArray.size(); i++) {

				int randomNum = ThreadLocalRandom.current().nextInt(0,
						systemsArray.get(i).getVersions().size());
				activeSys.add(systemsArray.get(i).getVersions().get(randomNum));
				activeSystem += systemsArray.get(i).getVersions()
						.get(randomNum);
				activeSystem += "-";

			}
			activeSystem += ".xlsx";

			// System.out.println(activeSystem);
			chrom.setActivSystem(activeSystem);
			Solution sol = evaluateChromosom(chrom);
			// for (int i = 0; i < sol.getNumberOfObjectives(); i++) {
			//
			// System.out.print(sol.getObjective(i)+",");
			//
			//
			// }
			System.out.println();
			p.add(sol);

		}
		FastNondominatedSorting sort = new FastNondominatedSorting();
		List<Solution> best = new ArrayList<Solution>();
		best = sort.evaluate(p);
//		for (int i = 0; i < p.size(); i++) {
//
//			System.out.println(p.get(i).getAttribute("rank"));
//
//		}
		// for (int i = 0; i < best.size(); i++) {
		// System.out.print(p.get(i).getObjective(0) + ",");
		// System.out.print(p.get(i).getObjective(1) + ",");
		// System.out.print(p.get(i).getObjective(2));
		// System.out.println();
		//
		// }

		return null;

	}

	private void childProduction(List<Solution> best, int i) {
		// best.get(i)

	}

	public Solution evaluateChromosom(Chromosom chrom)
			throws FileNotFoundException, IOException {

		String excelFilePath = chrom.getActivSystem();
		FileInputStream inputStream = new FileInputStream(new File(
				excelFilePath));
		Workbook workbook = new XSSFWorkbook(inputStream);
		Sheet firstSheet = workbook.getSheetAt(0);
		Iterator<Row> rowIterator = firstSheet.iterator();
		HashMap<String, Double> critics = new HashMap<String, Double>();
		String key = "";
		double value = 0;
		Solution sol = new Solution(1, 3);

		int i = 0;
		int counter = 0;
		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();
			// For each row iterate through each columns
			Iterator<Cell> cellIterator = row.cellIterator();

			while (cellIterator.hasNext()) {

				Cell cell = cellIterator.next();
				counter++;

				switch (cell.getCellTypeEnum()) {
				case STRING:
					key = cell.getStringCellValue();
					break;
				case NUMERIC:
					counter++;
					value = cell.getNumericCellValue();
					// System.out.println(chrom.getActivSystem());
					// System.out.println(cell.getNumericCellValue());
					sol.setObjective(i, value);
					// System.out.println(i);
					i++;
					break;

				}

				// critics.put(key, value);

			}

		}

		chrom.setEvaluateCritic(critics);
		System.out.println();
		workbook.close();
		inputStream.close();

		return sol;

	}

	public static void main(String[] args) throws FileNotFoundException,
			IOException {
		EvolutionArchitect evm = new EvolutionArchitect();
		evm.initialChromosomes();

	}

}
