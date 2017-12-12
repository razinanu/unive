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
	private ArrayList<String> versions = new ArrayList<String>();

	public String getSysName() {
		return sysName;
	}

	public void setSysName(String sysName) {
		this.sysName = sysName;
	}

	public ArrayList<String> getVersions() {
		return versions;
	}

	public void setVersions(ArrayList<String> versions) {
		this.versions = versions;
	}

	public Systems(String sysName, ArrayList<String> version) {
		this.sysName = sysName;
		this.versions = version;

	}

	public Systems() {
	}

	public ArrayList<Systems> getValue() throws FileNotFoundException, IOException {

		String excelFilePath = "systemsTest.xlsx";
		FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
		ArrayList<Systems> sysArray = new ArrayList<Systems>();
		Workbook workbook = new XSSFWorkbook(inputStream);
		Sheet firstSheet = workbook.getSheetAt(0);
		Iterator<Row> rowIterator = firstSheet.iterator();
		ArrayList<Systems> systems = new ArrayList<Systems>();

		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();

			// For each row iterate through each columns
			Iterator<Cell> cellIterator = row.cellIterator();
			Systems sys = new Systems();
			ArrayList<String> versions = new ArrayList<String>();
			int i = 0;

			while (cellIterator.hasNext()) {

				Cell cell = cellIterator.next();

				if (cell.getColumnIndex() == 0) {

					sys.setSysName(cell.getStringCellValue());

				} else
					switch (cell.getCellTypeEnum()) {
					case NUMERIC:
						versions.add(Double.toString(cell.getNumericCellValue()));
						// System.out.print(Double.toString(cell.getNumericCellValue()) +
						// "(Integer)\t");
						break;
					case STRING:
						versions.add(cell.getStringCellValue());
						// System.out.print(cell.getStringCellValue() + "(String)\t");
						break;
					}

			}
			sys.versions = versions;
			systems.add(sys);
			System.out.println();
		}

		workbook.close();
		inputStream.close();
		return systems;

	}

	public static void main(String[] args) throws IOException {

		Systems sys = new Systems();
		ArrayList<Systems> systems = new ArrayList<Systems>();
		systems = sys.getValue();
		for (int i = 0; i < systems.size(); i++) {

			sys = systems.get(i);
			System.out.print(sys.sysName);
			for (int y = 0; y < sys.versions.size(); y++) {
				System.out.print(" " + sys.versions.get(y));
			}
			System.out.println();
		}

	}

}
