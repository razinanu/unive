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
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Systems {

	private String sysName;
	private double performprovide;
	private double performConsume;
	private double tEnd;
	private double tStart;

	public String getSysName() {
		return sysName;
	}

	public void setSysName(String sysName) {
		this.sysName = sysName;
	}

	public double getPerformprovide() {
		return performprovide;
	}

	public void setPerformprovide(double performprovide) {
		this.performprovide = performprovide;
	}

	public double getPerformConsume() {
		return performConsume;
	}

	public void setPerformConsume(double performConsume) {
		this.performConsume = performConsume;
	}

	// public double getPerformSave() {
	// return performSave;
	// }

	// public void setPerformSave(double performSave) {
	// this.performSave = performSave;
	// }

	
	// private double performSave;


	public double gettStart() {
		return tStart;
	}

	public void settStart(double d) {
		this.tStart = d;
	}

	public double gettEnd() {
		return tEnd;
	}

	public void settEnd(double tEnd) {
		this.tEnd = tEnd;
	}



	public ArrayList<Systems> getValue() throws FileNotFoundException, IOException {

		// super("Capabilities");
		String excelFilePath = "SystemsTest.xlsx";
		FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
		ArrayList<Systems> sysArray = new ArrayList<Systems>();
		Workbook workbook = new XSSFWorkbook(inputStream);
		Sheet firstSheet = workbook.getSheetAt(0);
		Iterator<Row> iterator = firstSheet.iterator();
		ArrayList<String> systems = new ArrayList<String>();

		// setDefaultCloseOperation(EXIT_ON_CLOSE);
		// getContentPane().setLayout(new FlowLayout());
		while (iterator.hasNext()) {
			Row nextRow = iterator.next();
			Iterator<Cell> cellIterator = nextRow.cellIterator();

			int counter = 0;

			while (cellIterator.hasNext()) {

				Cell cell = cellIterator.next();
				switch (cell.getCellType()) {
				case Cell.CELL_TYPE_STRING:
					// JLabel label = new JLabel(cell.getStringCellValue().toString());
					// getContentPane().add(label);
					if (cell.getRow().getRowNum() != 0) {
						// System.out.println("Row is: "+cell.getRow().getRowNum());
						Systems sys = new Systems();
						int clmIndex = cell.getColumnIndex();
						int rowIndex = cell.getRow().getRowNum();
						sys.setSysName(cell.getStringCellValue().toString());
						// System.out.println("System: " + sys.sysName);
						sys.setPerformprovide(
								workbook.getSheetAt(0).getRow(rowIndex).getCell(clmIndex + 1).getNumericCellValue());
						sys.setPerformConsume(
								workbook.getSheetAt(0).getRow(rowIndex).getCell(clmIndex + 2).getNumericCellValue());
						sys.settStart(
								workbook.getSheetAt(0).getRow(rowIndex).getCell(clmIndex + 3).getNumericCellValue());
						sys.settEnd(
								workbook.getSheetAt(0).getRow(rowIndex).getCell(clmIndex + 4).getNumericCellValue());
						sysArray.add(sys);

					}
					break;
				}
			}
		}

		// while (iterator.hasNext()) {
		// Row nextRow = iterator.next();
		// Iterator<Cell> cellIterator = nextRow.cellIterator();
		// while (cellIterator.hasNext()) {
		// Cell cell = cellIterator.next();
		// if (cell.getRow().getRowNum() != 0) {
		// int clmIndex = cell.getColumnIndex();
		// int rowIndex = cell.getRow().getRowNum();
		// sys.sysName = cell.getStringCellValue().toString();
		//
		// sys.performprovide = workbook.getSheetAt(0).getRow(rowIndex).getCell(clmIndex
		// + 1)
		// .getNumericCellValue();
		// sys.performConsume = workbook.getSheetAt(0).getRow(rowIndex).getCell(clmIndex
		// + 2)
		// .getNumericCellValue();
		// sys.performSave = workbook.getSheetAt(0).getRow(rowIndex).getCell(clmIndex +
		// 3)
		// .getNumericCellValue();
		// sysArray.add(sys);
		// }
		//
		// }
		//
		// }

		workbook.close();
		inputStream.close();
		// setLocation(250, 190);
		// setSize(300, 90);
		// setVisible(true);
		return sysArray;
	}

	public static void main(String[] args) throws IOException {
		// new Fenster();
		// new Fenster();
		ArrayList<Systems> systems = new ArrayList<Systems>();
		Systems system = new Systems();
		systems = system.getValue();
		/// System.out.println("System 2: " + systems.get(3).sysName);
		 for (int i = 0; i < systems.size(); i++) {
		 System.out.print("System: " + systems.get(i).sysName);
		 System.out.print(" Provide: " + systems.get(i).performprovide);
		 System.out.print(" consume: " + systems.get(i).performConsume);
		 System.out.println(" TStart: " + systems.get(i).tStart);
		 System.out.println(" TEnd: " + systems.get(i).tEnd);

		 }

	}

}
