import javax.swing.*;
import java.awt.*;
import java.awt.List;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import org.apache.poi.ss.formula.functions.Column;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.validator.ValidateWith;

public class Capabilities {
	// extends JFrame {
	private String capName;
	private double performance;
	private double deadline;
	private double funding;

	public static void main(String[] args) throws IOException {

		Capabilities cap = new Capabilities();
		ArrayList<Capabilities> capArray = new ArrayList<Capabilities>();
		String excelFilePath = "capabilitiesTest.xlsx";
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
				switch (cell.getCellType()) {
				case Cell.CELL_TYPE_STRING:
					// JLabel label = new JLabel(cell.getStringCellValue().toString());
					// getContentPane().add(label);
					if (cell.getRow().getRowNum() != 0) {
						// System.out.println("Row is: "+cell.getRow().getRowNum());

						System.out.println("Column " + cell.getColumnIndex());
						int clmIndex = cell.getColumnIndex();
						int rowIndex = cell.getRow().getRowNum();
						cap.capName = cell.getStringCellValue().toString();
						// cap.performance = workbook.getSheetAt(0).getRow(rowIndex).getCell(clmIndex +
						// 1)
						Cell cellNext = cellIterator.next();
						cap.performance = cellNext.getNumericCellValue();
						cap.deadline = workbook.getSheetAt(0).getRow(rowIndex).getCell(clmIndex + 2)
								.getNumericCellValue();
						capArray.add(cap);
					}
					break;
				}

			}
			capArray.add(cap);
			System.out.println();
		}

		for (int i = 0; i < capArray.size(); i++) {
			System.out.println("Name" + capArray.get(i).capName);
			System.out.println("Performance" + capArray.get(i).performance);
			System.out.println("Deadline" + capArray.get(i).deadline);

		}

		workbook.close();
		inputStream.close();

	}

	// public ArrayList<Capabilities> getCapabilities() throws
	// FileNotFoundException, IOException {
	//
	// String excelFilePath = "capabilitiesTest.xlsx";
	// FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
	// ArrayList<Capabilities> capArray = new ArrayList<Capabilities>();
	// Workbook workbook = new XSSFWorkbook(inputStream);
	// Sheet firstSheet = workbook.getSheetAt(0);
	// Iterator<Row> iterator = firstSheet.iterator();
	// // super("Capabilities");
	// // setDefaultCloseOperation(EXIT_ON_CLOSE);
	// // getContentPane().setLayout(new FlowLayout());
	// // value.add(0, 23.1);
	//
	// while (iterator.hasNext()) {
	// Row nextRow = iterator.next();
	// Iterator<Cell> cellIterator = nextRow.cellIterator();
	//
	// int counter = 0;
	//
	// while (cellIterator.hasNext()) {
	//
	// Cell cell = cellIterator.next();
	// switch (cell.getCellType()) {
	// case Cell.CELL_TYPE_STRING:
	// JLabel label = new JLabel(cell.getStringCellValue().toString());
	// // getContentPane().add(label);
	// capabilities.add(cell.getStringCellValue().toString());
	// System.out.print(cell.getStringCellValue());
	// System.out.print(" ");
	// if (cell.getRow().getRowNum() != 0) {
	// // System.out.println("Row is: "+cell.getRow().getRowNum());
	// cap.add(cell.getStringCellValue().toString());
	// }
	// break;
	// case Cell.CELL_TYPE_BOOLEAN:
	// System.out.print(cell.getBooleanCellValue());
	// break;
	// case Cell.CELL_TYPE_NUMERIC:
	// // JLabel labelInt = new JLabel(cell.getNumericCellValue());
	// // getContentPane().add(labelInt);
	// System.out.print(cell.getNumericCellValue());
	// value.add(cell.getNumericCellValue());
	//
	// break;
	//
	// }
	//
	// }
	// capArray.add(cap);
	// System.out.println();
	// }
	// System.out.println(value.get(0).toString());
	// for (int i = 0; i < value.size(); i++) {
	// System.out.println(value.get(i).toString());
	//
	// }
	//
	// workbook.close();
	// inputStream.close();
	// // setLocation(250, 190);
	// // setSize(300, 90);
	// // setVisible(true);
	// return capArray;
	// }

}

// class MyListener implements ActionListener {
// private JTextField textField;
// private JLabel label;
//
// public MyListener(JTextField tf, JLabel l) {
// textField = tf;
// label = l;
// }
//
// public void actionPerformed(ActionEvent ae) {
// String text = textField.getText();
//
// String ergebnisText = "";
// try {
// double zahl = Double.parseDouble(text);
// double quadrat = zahl * zahl;
// ergebnisText = "" + quadrat;
// } catch (NumberFormatException ex) {
// ergebnisText = "keine Zahl";
// }
//
// label.setText(ergebnisText);
// textField.setText("Zahl eingeben");
// }
// }
