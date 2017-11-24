import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFRow.CellIterator;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Systems {

	private String sysName;
	private double powerProvide;
	private double powerConsume;
	private String tEnd;
	private String tStart;
	private double saveCap;

	public double getSaveCap() {
		return saveCap;
	}

	public void setSaveCap(double saveCap) {
		this.saveCap = saveCap;
	}

	public String getSysName() {
		return sysName;
	}

	public void setSysName(String sysName) {
		this.sysName = sysName;
	}

	public double getPowerProvide() {
		return powerProvide;
	}

	public void setPowerProvide(double powerProvide) {
		this.powerProvide = powerProvide;
	}

	public double getPowerConsume() {
		return powerConsume;
	}

	public void setPowerConsume(double powerConsume) {
		this.powerConsume = powerConsume;
	}

	public String gettStart() {
		return tStart;
	}

	public void settStart(String d) {
		this.tStart = d;
	}

	public String gettEnd() {
		return tEnd;
	}

	public void settEnd(String tEnd) {
		this.tEnd = tEnd;
	}

	public ArrayList<Systems> getValue() throws FileNotFoundException, IOException {

		String excelFilePath = "SystemsTest.xlsx";
		FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
		ArrayList<Systems> sysArray = new ArrayList<Systems>();
		Workbook workbook = new XSSFWorkbook(inputStream);
		Sheet firstSheet = workbook.getSheetAt(0);
		Iterator<Row> iterator = firstSheet.iterator();
		ArrayList<String> systems = new ArrayList<String>();

		while (iterator.hasNext()) {
			Row nextRow = iterator.next();
			Iterator<Cell> cellIterator = nextRow.cellIterator();

			int counter = 0;

			while (cellIterator.hasNext()) {

				Cell cell = cellIterator.next();

				if (cell.getRow().getRowNum() != 0) {

					Systems sys = new Systems();
					int clmIndex = cell.getColumnIndex();
					int rowIndex = cell.getRow().getRowNum();
					// start from the first column and set the whole properties of systems
					if (clmIndex == 0) {

						sys.setSysName(cell.getStringCellValue().toString());
						sys.setPowerProvide(
								workbook.getSheetAt(0).getRow(rowIndex).getCell(clmIndex + 1).getNumericCellValue());
						sys.setPowerConsume(
								workbook.getSheetAt(0).getRow(rowIndex).getCell(clmIndex + 2).getNumericCellValue());
						sys.setSaveCap(
								workbook.getSheetAt(0).getRow(rowIndex).getCell(clmIndex + 3).getNumericCellValue());
						sys.settStart(
								workbook.getSheetAt(0).getRow(rowIndex).getCell(clmIndex + 4).getStringCellValue());
						sys.settEnd(workbook.getSheetAt(0).getRow(rowIndex).getCell(clmIndex + 5).getStringCellValue());
						sysArray.add(sys);
					}
				}
			}
		}
		workbook.close();
		inputStream.close();
		return sysArray;
	}

	public static void main(String[] args) throws IOException {

		ArrayList<Systems> systems = new ArrayList<Systems>();
		Systems system = new Systems();
		systems = system.getValue();
		/// System.out.println("System 2: " + systems.get(3).sysName);
		// for (int i = 0; i < systems.size(); i++) {
		// System.out.print("System: " + systems.get(i).sysName);
		// System.out.print(" Provide: " + systems.get(i).performprovide);
		// System.out.print(" consume: " + systems.get(i).performConsume);
		// System.out.println(" TStart: " + systems.get(i).tStart);
		// System.out.println(" TEnd: " + systems.get(i).tEnd);
		//
		// }

	}

}
