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

public class TimeToHours {

	public void getValue() throws FileNotFoundException, IOException {

		String excelFilePath = "Baseload.xlsx";
		FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
		Workbook workbook = new XSSFWorkbook(inputStream);
		Sheet firstSheet = workbook.getSheetAt(0);
		Iterator<Row> iterator = firstSheet.iterator();

		while (iterator.hasNext()) {
			Row nextRow = iterator.next();
			Iterator<Cell> cellIterator = nextRow.cellIterator();

			int counter = 0;

			while (cellIterator.hasNext()) {

				Cell cell = cellIterator.next();
				int clmIndex = cell.getColumnIndex();
				int rowIndex = cell.getRow().getRowNum();

			}
		}
		workbook.close();
		inputStream.close();

	}

	public void add() throws IOException {
		String excelFilePath = "test2.xlsx";
		FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
		Workbook workbook = new XSSFWorkbook(inputStream);
		Sheet firstSheet = workbook.getSheetAt(1);

		Iterator<Row> iterator = firstSheet.iterator();
		int c = 0;
		while (iterator.hasNext()) {
			Row nextRow = iterator.next();
			Iterator<Cell> cellIterator = nextRow.cellIterator();
			// System.out.println("C is:"+c);

			int counter = 0;

			while (cellIterator.hasNext()) {

				if (c < 536) {
					Cell cell = cellIterator.next();

					Double num = cell.getNumericCellValue();
					int rowIndex = cell.getRow().getRowNum();
					int clmIndex = cell.getColumnIndex();
					// System.out.println("clm"+ rowIndex);
					if (rowIndex % 2 == 0) {
						// System.out.println("Hallo ");
						int test = cell.getRow().getRowNum() + 1;
						Double num2 = workbook.getSheetAt(1).getRow(rowIndex + 1).getCell(clmIndex)
								.getNumericCellValue();
						Double sum = num + num2;
						System.out.println(sum);
						// cellIterator = nextRow.cellIterator();
						// cell = cellIterator.next();
						// System.out.println(clmIndex);

					} else {
						System.out.println("");
					}

				}

				// }

				// int clmIndex = cell.getColumnIndex();
				//

			}
			c++;
		}
		workbook.close();
		inputStream.close();

	}

	public static void main(String[] args) throws FileNotFoundException, IOException {
		TimeToHours x = new TimeToHours();
		x.add();

	}

}
