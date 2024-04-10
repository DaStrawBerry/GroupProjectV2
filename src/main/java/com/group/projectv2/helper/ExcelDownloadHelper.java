package com.group.projectv2.helper;

import com.group.projectv2.entity.Result;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelDownloadHelper {
    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static String[] HEADERs = { "Id", "Start time", "Used time", "Mark", "User ID" };
    static String SHEET = "Results";

    public static ByteArrayInputStream resultsToExcel(List<Result> results) {

        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
            Sheet sheet = workbook.createSheet(SHEET);

            // Header
            Row headerRow = sheet.createRow(0);

            for (int col = 0; col < HEADERs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(HEADERs[col]);
            }

            int rowIdx = 1;
            for (Result result : results) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(result.getId());
                row.createCell(1).setCellValue(result.getStart());
                row.createCell(2).setCellValue(result.getUsedtime());
                row.createCell(3).setCellValue(result.getMark());
                row.createCell(4).setCellValue(result.getUserid());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
        }
    }
}
