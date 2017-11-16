package com.ppi.impl;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReaderIMPL {

	@SuppressWarnings({ "unchecked", "deprecation", "rawtypes" })
	public static List<List<String>> readData(String filename) {
		try {
			InputStream inputStream = new ByteArrayInputStream(IOUtils.toByteArray(new FileInputStream(filename)));
			Workbook wb = WorkbookFactory.create(inputStream);
			Sheet sheet = wb.getSheetAt(0);
			Iterator rowIter = sheet.iterator();
			List<List<String>> record = new ArrayList<>();
			while (rowIter.hasNext()) {
				List cellData = new ArrayList<>();
				Row row = (Row) rowIter.next();
				Iterator cellIter = row.cellIterator();

				while (cellIter.hasNext()) {
					Cell cell = (Cell) cellIter.next();
					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_STRING:
						cellData.add(cell.getStringCellValue().trim().replaceAll("\n", " "));
						break;
					case Cell.CELL_TYPE_NUMERIC:
						double d = cell.getNumericCellValue();
						Double toBeTruncated = new Double(d);
						Double truncatedDouble = BigDecimal.valueOf(toBeTruncated).setScale(2, RoundingMode.HALF_UP)
								.doubleValue();
						cellData.add(Double.toString(truncatedDouble));
						break;
					case Cell.CELL_TYPE_BLANK:
						cellData.add(null);
						break;
					}
				}
				record.add(cellData);
			}

			if (record != null) {
				return record;
			}
		} catch (Exception e) {
			// e.printStackTrace();
			System.out.println(e);
		}
		return null;
	}
}
