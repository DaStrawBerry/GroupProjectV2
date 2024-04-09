package com.group.projectv2.helper;

import com.group.projectv2.entity.Question;
import org.apache.poi.ss.usermodel.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelHelper {
    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static String[] HEADERs = {"id", "Test ID", "N_th", "Title", "Option1", "Option2", "Option3", "Option4", "Correct Answer"};

    //check if the file is an excel file
    public static boolean hasExcelFormat(MultipartFile file) {
        if (!TYPE.equals(file.getContentType())) {
            return false;
        }
        return true;
    }

    public static List<Question> excelToQuestions(InputStream is) {
        try{
            Workbook workbook = WorkbookFactory.create(is);

            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rows = sheet.iterator();

            List<Question> questions = new ArrayList<Question>();

            int rowNumber = 0;
            while(rows.hasNext()){
                Row currentRow = rows.next();

                if(rowNumber == 0){
                    rowNumber++;
                    continue;
                }
                Iterator<Cell> cellsInRow = currentRow.iterator();

                Question question = new Question();
                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();
                    switch (cellIdx) {
                        case 0:
                            question.setId(currentCell.getStringCellValue());
                            break;
                        case 1:
                            question.setTestid(currentCell.getStringCellValue());
                            break;
                        case 2:
                            question.setN_th((int) currentCell.getNumericCellValue());
                            break;
                        case 3:
                            question.setTitle(currentCell.getStringCellValue());
                            break;
                        case 4:
                            question.setOption1(currentCell.getStringCellValue());
                            break;
                        case 5:
                            question.setOption2(currentCell.getStringCellValue());
                            break;
                        case 6:
                            question.setOption3(currentCell.getStringCellValue());
                            break;
                        case 7:
                            question.setOption4(currentCell.getStringCellValue());
                            break;
                        case 8:
                            question.setAnswer((int) currentCell.getNumericCellValue());
                            break;
                        default:
                            break;
                    }
                    cellIdx++;
                }
                questions.add(question);
            }

            workbook.close();
            return questions;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }
}
